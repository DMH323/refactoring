package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * This class generates a statement for a given invoice of performances.
 */
public class StatementPrinter {

    public static final int TRAGEDY_BASE_AMOUNT = 40000;
    public static final int TRAGEDY_EXTRA_RATE = 1000;
    public static final int TRAGEDY_BASE_AUDIENCE = 30;

    public final Invoice invoice;
    public final Map<String, Play> plays;

    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public String statement() {
        StringBuilder result =
                new StringBuilder("Statement for " + invoice.getCustomer()
                        + System.lineSeparator());

        // compute totals first (split the loop)
        int totalAmount = getTotalAmount();
        int volumeCredits = getTotalVolumeCredits();

        // build the result string (keep as the main loop — do not extract)
        for (Performance performance : invoice.getPerformances()) {
            int thisAmountValue = getAmount(performance);
            result.append(
                    String.format("  %s: %s (%s seats)%n",
                            getPlay(performance).name,
                            usd(thisAmountValue),
                            performance.audience)
            );
        }

        result.append(
                String.format("Amount owed is %s%n",
                        usd(totalAmount))
        );
        result.append(
                String.format("You earned %s credits%n", volumeCredits)
        );

        return result.toString();
    }

    /**
     * Returns total volume credits for the invoice.
     */
    private int getTotalVolumeCredits() {
        int result = 0;
        for (Performance performance : invoice.getPerformances()) {
            result += getVolumeCredits(performance);
        }
        return result;
    }

    /**
     * Returns total amount for the invoice (in cents).
     */
    private int getTotalAmount() {
        int result = 0;
        for (Performance performance : invoice.getPerformances()) {
            result += getAmount(performance);
        }
        return result;
    }

    private int getVolumeCredits(Performance performance) {
        int result = 0;
        result += Math.max(performance.audience - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        if ("comedy".equals(getPlayType(performance))) {
            result += performance.audience / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }
        return result;
    }

    private Play getPlay(Performance performance) {
        return plays.get(performance.playID);
    }

    private String getPlayType(Performance performance) {
        return getPlay(performance).type;
    }

    private int getAmount(Performance performance) {
        int result;
        String type = getPlayType(performance);

        switch (type) {
            case "tragedy":
                result = TRAGEDY_BASE_AMOUNT;
                if (performance.audience > TRAGEDY_BASE_AUDIENCE) {
                    result += TRAGEDY_EXTRA_RATE
                            * (performance.audience - TRAGEDY_BASE_AUDIENCE);
                }
                break;

            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (performance.audience > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.audience
                            - Constants.COMEDY_AUDIENCE_THRESHOLD);
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE
                        * performance.audience;
                break;

            default:
                throw new RuntimeException(
                        String.format("unknown type: %s", type));
        }

        return result;
    }

    private String usd(int amountInCents) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format((double) amountInCents / Constants.PERCENT_FACTOR);
    }
}

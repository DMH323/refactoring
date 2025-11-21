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

    private final Invoice invoice;
    private final Map<String, Play> plays;

    /**
     * Constructs a StatementPrinter for the given invoice and plays.
     *
     * @param invoice the invoice containing performances
     * @param plays   the map of play IDs to Play objects
     */
    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     *
     * @return the formatted statement
     * @throws RuntimeException if one of the play types is not known
     */
    public String statement() {
        final int totalAmount = getTotalAmount();
        final int volumeCredits = getTotalVolumeCredits();

        final StringBuilder result = new StringBuilder("Statement for " + invoice.getCustomer()
                + System.lineSeparator());

        for (final Performance performance : invoice.getPerformances()) {
            final int thisAmountValue = getAmount(performance);
            result.append(String.format("  %s: %s (%s seats)%n",
                    getPlay(performance).getName(),
                    usd(thisAmountValue),
                    performance.getAudience()));
        }

        result.append(String.format("Amount owed is %s%n", usd(totalAmount)));
        result.append(String.format("You earned %s credits%n", volumeCredits));

        return result.toString();
    }

    /**
     * Returns the total volume credits for the invoice.
     *
     * @return total volume credits
     */
    private int getTotalVolumeCredits() {
        int result = 0;
        for (final Performance performance : invoice.getPerformances()) {
            result += getVolumeCredits(performance);
        }
        return result;
    }

    /**
     * Returns the total amount for the invoice.
     *
     * @return total amount in cents
     */
    private int getTotalAmount() {
        int result = 0;
        for (final Performance performance : invoice.getPerformances()) {
            result += getAmount(performance);
        }
        return result;
    }

    private int getVolumeCredits(final Performance performance) {
        int result = Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        if ("comedy".equals(getPlayType(performance))) {
            result += performance.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }
        return result;
    }

    private Play getPlay(final Performance performance) {
        return plays.get(performance.getPlayID());
    }

    private String getPlayType(final Performance performance) {
        return getPlay(performance).getType();
    }

    private int getAmount(final Performance performance) {
        final String type = getPlayType(performance);
        int result;

        switch (type) {
            case "tragedy":
                result = TRAGEDY_BASE_AMOUNT;
                if (performance.getAudience() > TRAGEDY_BASE_AUDIENCE) {
                    result += TRAGEDY_EXTRA_RATE
                            * (performance.getAudience() - TRAGEDY_BASE_AUDIENCE);
                }
                break;

            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD);
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE
                        * performance.getAudience();
                break;

            default:
                throw new RuntimeException(String.format("unknown type: %s", type));
        }

        return result;
    }

    /**
     * Formats an amount in cents to US currency string.
     *
     * @param amountInCents amount in cents
     * @return formatted USD string
     */
    private String usd(final int amountInCents) {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format((double) amountInCents / Constants.PERCENT_FACTOR);
    }

    /**
     * Returns the invoice associated with this printer.
     *
     * @return the invoice
     */
    public Invoice getInvoice() {
        return invoice;
    }

    /**
     * Returns the map of plays associated with this printer.
     *
     * @return the plays map
     */
    public Map<String, Play> getPlays() {
        return plays;
    }
}

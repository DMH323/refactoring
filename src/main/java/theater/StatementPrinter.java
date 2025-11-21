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
        int totalAmount = 0;
        int volumeCredits = 0;

        StringBuilder result =
                new StringBuilder("Statement for " + invoice.getCustomer()
                        + System.lineSeparator());

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.playID);
            int thisAmount = getAmount(performance);

            volumeCredits = getVolumeCredits(performance, volumeCredits, play);

            // print line for this order
            result.append(
                    String.format("  %s: %s (%s seats)%n",
                            play.name,
                            formatter.format(thisAmount / 100),
                            performance.audience)
            );

            totalAmount += thisAmount;
        }

        result.append(
                String.format("Amount owed is %s%n",
                        formatter.format(totalAmount / 100))
        );
        result.append(
                String.format("You earned %s credits%n", volumeCredits)
        );

        return result.toString();
    }


    /**
     * Helper to retrieve the play object for a performance.
     */
    private Play getPlay(Performance performance) {
        return plays.get(performance.playID);
    }

    /**
     * Helper to retrieve a play’s type.
     */
    private String getPlayType(Performance performance) {
        return getPlay(performance).type;
    }

    private int getAmount(Performance performance) {
        int amount;
        String type = getPlayType(performance);

        switch (type) {
            case "tragedy":
                amount = TRAGEDY_BASE_AMOUNT;
                if (performance.audience > TRAGEDY_BASE_AUDIENCE) {
                    amount += TRAGEDY_EXTRA_RATE
                            * (performance.audience - TRAGEDY_BASE_AUDIENCE);
                }
                break;

            case "comedy":
                amount = Constants.COMEDY_BASE_AMOUNT;
                if (performance.audience > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    amount += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.audience
                            - Constants.COMEDY_AUDIENCE_THRESHOLD);
                }
                amount += Constants.COMEDY_AMOUNT_PER_AUDIENCE
                        * performance.audience;
                break;

            default:
                throw new RuntimeException(
                        String.format("unknown type: %s", type));
        }

        return amount;
    }
}

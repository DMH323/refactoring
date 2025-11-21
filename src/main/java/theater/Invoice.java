package theater;

import java.util.List;

/**
 * Represents an invoice for a customer, containing multiple performances.
 */
public class Invoice {

    /** The name of the customer associated with the invoice. */
    private final String customer;

    /** The list of performances included in the invoice. */
    private final List<Performance> performances;

    /**
     * Constructs an Invoice with the given customer name and list of performances.
     *
     * @param customer      the customer name
     * @param performances  the list of performances in the invoice
     */
    public Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    /**
     * Returns the customer name.
     *
     * @return the customer name
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Returns the list of performances.
     *
     * @return the performances in the invoice
     */
    public List<Performance> getPerformances() {
        return performances;
    }
}

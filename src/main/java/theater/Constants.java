package theater;

/**
 * Defines various constant values used throughout the program.
 */
public final class Constants {

    // -------------------------------------------------------------------------
    // Volume credit constants
    // -------------------------------------------------------------------------
    /** Base audience size required before awarding volume credits. */
    public static final int BASE_VOLUME_CREDIT_THRESHOLD = 30;

    /** Extra volume credit awarded for every N comedy attendees. */
    public static final int COMEDY_EXTRA_VOLUME_FACTOR = 5;

    // -------------------------------------------------------------------------
    // Comedy amount constants
    // -------------------------------------------------------------------------
    /** Amount added per audience member for a comedy. */
    public static final int COMEDY_AMOUNT_PER_AUDIENCE = 300;

    /** Audience threshold beyond which extra comedy charges apply. */
    public static final int COMEDY_AUDIENCE_THRESHOLD = 20;

    /** Base charge for a comedy performance. */
    public static final int COMEDY_BASE_AMOUNT = 30000;

    /** Fixed additional amount added once the threshold is exceeded. */
    public static final int COMEDY_OVER_BASE_CAPACITY_AMOUNT = 10000;

    /** Additional amount added per audience member beyond the threshold. */
    public static final int COMEDY_OVER_BASE_CAPACITY_PER_PERSON = 500;

    // -------------------------------------------------------------------------
    // Tragedy amount constants
    // -------------------------------------------------------------------------
    /** Audience threshold beyond which extra tragedy charges apply. */
    public static final int TRAGEDY_AUDIENCE_THRESHOLD = 30;

    /** Base charge for a tragedy. */
    public static final int TRAGEDY_BASE_AMOUNT = 40000;

    /** Additional amount added per audience member beyond the threshold. */
    public static final int TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON = 1000;

    // -------------------------------------------------------------------------
    // Formatting constants
    // -------------------------------------------------------------------------
    /** Factor used for percentage or cents-to-dollars conversions. */
    public static final int PERCENT_FACTOR = 100;

    // -------------------------------------------------------------------------
    // History play constants
    // -------------------------------------------------------------------------
    /** Base charge for a history play. */
    public static final int HISTORY_BASE_AMOUNT = 20000;

    /** Additional amount added per audience member beyond the threshold. */
    public static final int HISTORY_OVER_BASE_CAPACITY_PER_PERSON = 1000;

    /** Audience threshold for extra history charges. */
    public static final int HISTORY_AUDIENCE_THRESHOLD = 20;

    /** Minimum audience before history volume credits begin. */
    public static final int HISTORY_VOLUME_CREDIT_THRESHOLD = 20;

    // -------------------------------------------------------------------------
    // Pastoral play constants
    // -------------------------------------------------------------------------
    /** Base charge for a pastoral play. */
    public static final int PASTORAL_BASE_AMOUNT = 40000;

    /** Additional amount added per audience member beyond the threshold. */
    public static final int PASTORAL_OVER_BASE_CAPACITY_PER_PERSON = 2500;

    /** Audience threshold for extra pastoral charges. */
    public static final int PASTORAL_AUDIENCE_THRESHOLD = 20;

    /** Minimum audience before pastoral volume credits begin. */
    public static final int PASTORAL_VOLUME_CREDIT_THRESHOLD = 20;

    /**
     * Private constructor to prevent instantiation.
     */
    private Constants() {
        // Prevent instantiation
    }
}

package theater;

/**
 * Class representing a performance of a play.
 */
public class Performance {

    /** The ID of the play being performed. */
    public final String playID;

    /** Number of audience members. */
    public final int audience;

    /**
     * Constructs a Performance with the specified play ID and audience size.
     *
     * @param playID   the identifier for the play
     * @param audience the audience count for this performance
     */
    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    /**
     * Returns the play ID.
     *
     * @return the play ID
     */
    public String getPlayID() {
        return playID;
    }

    /**
     * Returns the audience count.
     *
     * @return the audience size
     */
    public int getAudience() {
        return audience;
    }
}

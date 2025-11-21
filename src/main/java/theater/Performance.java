package theater;

/**
 * Class representing a performance of a play.
 */
public class Performance {

    private final String playID;
    private final int audience;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    /**
     * Returns the play ID associated with this performance.
     *
     * @return the play ID
     */
    public String getPlayID() {
        return playID;
    }

    /**
     * Returns the number of audience members for this performance.
     *
     * @return audience count
     */
    public int getAudience() {
        return audience;
    }
}

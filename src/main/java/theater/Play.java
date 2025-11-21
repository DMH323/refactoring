package theater;

/**
 * Class representing a play.
 */
public class Play {

    private final String name;
    private final String type;

    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Returns the name of the play.
     *
     * @return the play name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the play (e.g., tragedy, comedy).
     *
     * @return the play type
     */
    public String getType() {
        return type;
    }
}

package theater;

/**
 * Represents a play with a name and type.
 */
public class Play {

    /** The name of the play. */
    public final String name;

    /** The type of the play (e.g., comedy, tragedy). */
    public final String type;

    /**
     * Constructs a Play with the given name and type.
     *
     * @param name the name of the play
     * @param type the type of the play
     */
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
     * Returns the type of the play.
     *
     * @return the play type
     */
    public String getType() {
        return type;
    }
}

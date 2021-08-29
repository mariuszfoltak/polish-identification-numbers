package pl.foltak.polishidnumbers.pesel;

/**
 * Thrown when the given PESEL is invalid.
 */
public class InvalidPeselException extends Exception {
    private final PeselConstraint peselConstraint;

    /**
     * Construct an instance for this class.
     *
     * @param peselConstraint the information why the identifier is invalid
     * @param message the detail message
     */
    InvalidPeselException(PeselConstraint peselConstraint, String message) {
        super(message);
        this.peselConstraint = peselConstraint;
    }

    /**
     * Returns enum value which specifies why the identifier is invalid.
     *
     * @return violation type
     */
    public PeselConstraint getPeselConstraint() {
        return peselConstraint;
    }

    /**
     * A restriction that is not met by pesel
     */
    public enum PeselConstraint {
        /**
         * Incorrect length
         */
        INCORRECT_LENGTH,

        /**
         * Incorrect characters
         */
        INCORRECT_CHARACTERS,

        /**
         * Incorrect check digit
         */
        INCORRECT_CHECK_DIGIT,

        /**
         * Incorrect birth date
         */
        INCORRECT_BIRTH_DATE
    }
}

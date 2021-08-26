package pl.foltak.polishidnumbers.pesel;

public class InvalidPeselException extends Exception {
    private final PeselConstraint peselConstraint;

    public InvalidPeselException(PeselConstraint peselConstraint, String message) {
        super(message);
        this.peselConstraint = peselConstraint;
    }

    public PeselConstraint getPeselConstraint() {
        return peselConstraint;
    }

    public enum PeselConstraint {
        INCORRECT_LENGTH,
        INCORRECT_CHARACTERS,
        INCORRECT_CHECK_DIGIT,
        INCORRECT_BIRTH_DATE
    }
}

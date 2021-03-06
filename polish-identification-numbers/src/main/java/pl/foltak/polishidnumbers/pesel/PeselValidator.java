package pl.foltak.polishidnumbers.pesel;

import java.time.DateTimeException;

import static pl.foltak.polishidnumbers.pesel.InvalidPeselException.PeselConstraint.*;

/**
 * Validates given PESEL number
 */
public class PeselValidator {

    /**
     * Weights for PESEL digits
     */
    private static final int[] WEIGHTS = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};

    /**
     * PeselBirthDateDecoder class
     */
    private final PeselBirthDateDecoder peselBirthDateDecoder;

    /**
     * Default constructor of this validator
     */
    public PeselValidator() {
        this(new PeselBirthDateDecoder());
    }

    /**
     * Constructor with PeselBirthDateDecoder class argument
     *
     * @param peselBirthDateDecoder birth date decoder class
     */
    PeselValidator(PeselBirthDateDecoder peselBirthDateDecoder) {
        this.peselBirthDateDecoder = peselBirthDateDecoder;
    }

    /**
     * Checks if given PESEL number is valid.
     *
     * @param pesel PESEL number
     * @return true if PESEL is valid, otherwise false
     */
    public boolean isValid(String pesel) {
        try {
            assertIsValid(pesel);
            return true;
        } catch (InvalidPeselException e) {
            return false;
        }
    }

    /**
     * Asserts that given PESEL number is valid, otherwise throwns <code>{@link InvalidPeselException}</code>
     *
     * @param pesel PESEL number
     * @throws InvalidPeselException if PESEL number is invalid
     */
    public void assertIsValid(String pesel) throws InvalidPeselException {
        if (pesel.length() != 11) {
            throw new InvalidPeselException(INCORRECT_LENGTH, "Invalid PESEL number: incorrect length");
        }
        if (!pesel.matches("\\d+")) {
            throw new InvalidPeselException(INCORRECT_CHARACTERS, "Invalid PESEL number: incorrect characters");
        }
        if (!hasCorrectCheckDigit(pesel)) {
            throw new InvalidPeselException(INCORRECT_CHECK_DIGIT, "Invalid PESEL number: incorrect check digit");
        }
        assertHasCorrectBirthDate(pesel);
    }

    /**
     * Asserts that given PESEL number has correct birth date, otherwise throwns <code>{@link InvalidPeselException}</code>
     *
     * @param pesel PESEL number
     * @throws InvalidPeselException if PESEL number has incorrect birth date
     */
    private void assertHasCorrectBirthDate(String pesel) throws InvalidPeselException {
        try {
            peselBirthDateDecoder.decode(pesel);
        } catch (DateTimeException dateTimeException) {
            throw new InvalidPeselException(INCORRECT_BIRTH_DATE, "Invalid PESEL number: incorrect birth date");
        }
    }

    /**
     * Checks if given PESEl number has correct check digit.
     *
     * @param pesel PESEL number
     * @return true if check digit is correct, otherwise false
     */
    private boolean hasCorrectCheckDigit(String pesel) {
        int checkDigit = Character.getNumericValue(pesel.charAt(10));
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(pesel.charAt(i)) * WEIGHTS[i];
        }
        int modulo = sum % 10;
        int calculatedCheckDigit = 10 - modulo;

        return modulo == 0 || calculatedCheckDigit == checkDigit;
    }
}

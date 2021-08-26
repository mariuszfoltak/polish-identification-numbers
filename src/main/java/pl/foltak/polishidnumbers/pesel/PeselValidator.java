package pl.foltak.polishidnumbers.pesel;

import java.time.DateTimeException;

import static pl.foltak.polishidnumbers.pesel.InvalidPeselException.PeselConstraint.*;

public class PeselValidator {

    public static final int[] WEIGHTS = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};

    private final PeselBirthDateDecoder peselBirthDateDecoder;

    public PeselValidator() {
        this(new PeselBirthDateDecoder());
    }

    PeselValidator(PeselBirthDateDecoder peselBirthDateDecoder) {
        this.peselBirthDateDecoder = peselBirthDateDecoder;
    }

    public boolean isValid(String pesel) {
        try {
            assertIsValid(pesel);
            return true;
        } catch (InvalidPeselException e) {
            return false;
        }
    }

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

    private void assertHasCorrectBirthDate(String pesel) throws InvalidPeselException {
        try {
            peselBirthDateDecoder.decode(pesel);
        } catch (DateTimeException dateTimeException) {
            throw new InvalidPeselException(INCORRECT_BIRTH_DATE, "Invalid PESEL number: incorrect birth date");
        }
    }

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

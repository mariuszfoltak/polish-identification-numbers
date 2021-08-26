package pl.foltak.polishidnumbers.pesel;

import static pl.foltak.polishidnumbers.pesel.InvalidPeselException.PeselConstraint.*;

public class PeselValidator {

    public static final int[] WEIGHTS = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};

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
        if (!hasCorrectBirthDate(pesel)) {
            throw new InvalidPeselException(INCORRECT_BIRTH_DATE, "Invalid PESEL number: incorrect birth date");
        }
    }

    private boolean hasCorrectBirthDate(String pesel) {
        int month = Integer.parseInt(pesel.substring(2, 4));
        return ((month >= 1 && month <= 12) ||
                (month >= 21 && month <= 32) ||
                (month >= 41 && month <= 52) ||
                (month >= 61 && month <= 72) ||
                (month >= 81 && month <= 92));
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

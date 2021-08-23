package pl.foltak.polishidnumbers.pesel;

public class PeselValidator {
    public boolean isValid(String pesel) {
        if (pesel.length() != 11) {
            return false;
        }
        if (!pesel.matches("\\d+")) {
            return false;
        }
        if (!hasCorrectCheckDigit(pesel)) {
            return false;
        }
        if (!hasCorrectBirthDate(pesel)) {
            return false;
        }
        return true;
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
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};
        int checkDigit = Character.getNumericValue(pesel.charAt(10));
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(pesel.charAt(i)) * weights[i];
        }
        int modulo = sum % 10;
        int calculatedCheckDigit = 10 - modulo;

        if (modulo != 0 && calculatedCheckDigit != checkDigit) {
            return false;
        }
        return true;
    }
}

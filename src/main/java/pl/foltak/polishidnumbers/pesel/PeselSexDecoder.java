package pl.foltak.polishidnumbers.pesel;

/**
 * Decodes sex from pesel.
 */
class PeselSexDecoder {

    /**
     * Decodes sex from pesel.
     *
     * @param pesel pesel number
     * @return decoded sex
     */
    Pesel.Sex decode(String pesel) {
        int sexDigit = Character.getNumericValue(pesel.charAt(9));
        return sexDigit % 2 == 0 ? Pesel.Sex.FEMALE : Pesel.Sex.MALE;
    }
}

package pl.foltak.polishidnumbers.pesel;

class PeselSexDecoder {

    Pesel.Sex decode(String pesel) {
        int sexDigit = Character.getNumericValue(pesel.charAt(9));
        return sexDigit % 2 == 0 ? Pesel.Sex.FEMALE : Pesel.Sex.MALE;
    }
}

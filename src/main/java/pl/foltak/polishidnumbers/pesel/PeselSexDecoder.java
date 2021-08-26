package pl.foltak.polishidnumbers.pesel;

class PeselSexDecoder {

    public Pesel.Sex decode(String pesel) throws InvalidPeselException {
        int sexDigit = Character.getNumericValue(pesel.charAt(9));
        return sexDigit % 2 == 0 ? Pesel.Sex.FEMALE : Pesel.Sex.MALE;
    }
}

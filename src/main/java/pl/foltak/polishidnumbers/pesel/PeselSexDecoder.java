package pl.foltak.polishidnumbers.pesel;

public class PeselSexDecoder {

    private final PeselValidator peselValidator;

    public PeselSexDecoder() {
        this(new PeselValidator());
    }

    PeselSexDecoder(PeselValidator peselValidator) {
        this.peselValidator = peselValidator;
    }

    public Pesel.Sex decode(String pesel) throws InvalidPeselException {
        peselValidator.assertIsValid(pesel);
        int sexDigit = Character.getNumericValue(pesel.charAt(9));
        return sexDigit % 2 == 0 ? Pesel.Sex.FEMALE : Pesel.Sex.MALE;
    }
}

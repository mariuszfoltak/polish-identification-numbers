package pl.foltak.polishidnumbers.pesel;

import java.time.LocalDate;

public class Pesel {
    private final LocalDate birthDate;
    private final Pesel.Sex sex;

    public Pesel(String pesel) throws InvalidPeselException {
        this(new PeselValidator(), new PeselBirthDateDecoder(), new PeselSexDecoder(), pesel);
    }

    Pesel(PeselValidator peselValidator, PeselBirthDateDecoder peselBirthDateDecoder,
          PeselSexDecoder peselSexDecoder, String pesel) throws InvalidPeselException {
        peselValidator.assertIsValid(pesel);
        birthDate = peselBirthDateDecoder.decode(pesel);
        sex = peselSexDecoder.decode(pesel);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Pesel.Sex getSex() {
        return sex;
    }

    public enum Sex {
        FEMALE, MALE
    }
}

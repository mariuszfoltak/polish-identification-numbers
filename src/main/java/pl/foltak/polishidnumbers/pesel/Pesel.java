package pl.foltak.polishidnumbers.pesel;

import java.time.LocalDate;

public class Pesel {
    private final PeselValidator peselValidator;
    private final PeselBirthDateDecoder peselBirthDateDecoder;
    private final PeselSexDecoder peselSexDecoder;

    private LocalDate birthDate;
    private Pesel.Sex sex;

    public Pesel(String pesel) {
        this(new PeselValidator(), new PeselBirthDateDecoder(), new PeselSexDecoder(), pesel);
    }

    Pesel(PeselValidator peselValidator, PeselBirthDateDecoder peselBirthDateDecoder,
          PeselSexDecoder peselSexDecoder, String pesel) {
        this.peselValidator = peselValidator;
        this.peselBirthDateDecoder = peselBirthDateDecoder;
        this.peselSexDecoder = peselSexDecoder;
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

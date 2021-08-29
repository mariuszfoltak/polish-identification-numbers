package pl.foltak.polishidnumbers.pesel;

import java.time.LocalDate;

/**
 * Class that contains information about person identified by given PESEL. If pesel is invalid creation
 * of this class throwns <code>{@link InvalidPeselException}</code>.
 */
public class Pesel {

    /**
     * Decoded birth date
     */
    private final LocalDate birthDate;

    /**
     * Decoded person sex
     */
    private final Pesel.Sex sex;

    /**
     * Constructor of PESEL class. Throwns <code>{@link InvalidPeselException}</code> if pesel is invalid.
     *
     * @param pesel pesel number
     * @throws InvalidPeselException if pesel is invalid
     */
    public Pesel(String pesel) throws InvalidPeselException {
        this(new PeselValidator(), new PeselBirthDateDecoder(), new PeselSexDecoder(), pesel);
    }

    /**
     * Constructor.
     *
     * @param peselValidator pesel validator class
     * @param peselBirthDateDecoder pesel birth date decoder class
     * @param peselSexDecoder pesel sex decoder class
     * @param pesel pesel number
     * @throws InvalidPeselException if pesel is invalid
     */
    Pesel(PeselValidator peselValidator, PeselBirthDateDecoder peselBirthDateDecoder,
          PeselSexDecoder peselSexDecoder, String pesel) throws InvalidPeselException {
        peselValidator.assertIsValid(pesel);
        birthDate = peselBirthDateDecoder.decode(pesel);
        sex = peselSexDecoder.decode(pesel);
    }

    /**
     * Returns decoded birth date of person identified by given PESEL.
     *
     * @return birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Returns decoded sex of person identified by given PESEL.
     *
     * @return sex
     */
    public Pesel.Sex getSex() {
        return sex;
    }

    /**
     * A sex of person identified by PESEL
     */
    public enum Sex {
        /**
         * Female
         */
        FEMALE,

        /**
         * Male
         */
        MALE
    }
}

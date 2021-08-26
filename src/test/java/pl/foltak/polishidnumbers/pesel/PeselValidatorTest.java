package pl.foltak.polishidnumbers.pesel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.foltak.polishidnumbers.pesel.InvalidPeselException.PeselConstraint;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class PeselValidatorTest {

    private PeselValidator peselValidator;

    @BeforeEach
    void setUp() {
        peselValidator = new PeselValidator();
    }

    @Test
    public void pesel_should_not_be_null() {
        assertThrows(NullPointerException.class, () -> {
            peselValidator.assertIsValid(null);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "abc", "toooooolongstring"})
    public void pesel_should_be_11_characters_long(String string) {
        assertThat(peselValidator.isValid(string), equalTo(false));
        InvalidPeselException invalidPeselException = assertThrows(InvalidPeselException.class, () -> {
            peselValidator.assertIsValid(string);
        });
        assertThat(invalidPeselException.getPeselConstraint(), equalTo(PeselConstraint.INCORRECT_LENGTH));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaaaaaaaaaa", "1234567890a", "a1234567890"})
    public void pesel_should_contains_only_numbers(String string) {
        assertThat(peselValidator.isValid(string), equalTo(false));
        InvalidPeselException invalidPeselException = assertThrows(InvalidPeselException.class, () -> {
            peselValidator.assertIsValid(string);
        });
        assertThat(invalidPeselException.getPeselConstraint(), equalTo(PeselConstraint.INCORRECT_CHARACTERS));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "61020335564",
            "55080582722",
            "54030186184",
            "69021269721",
            "84052133373",
            "54070643896",
            "87062151182",
            "52102544331",
            "99122584564",
            "92060671492"
    })
    public void pesel_should_have_correct_check_digit(String string) {
        assertThat(peselValidator.isValid(string), equalTo(false));
        InvalidPeselException invalidPeselException = assertThrows(InvalidPeselException.class, () -> {
            peselValidator.assertIsValid(string);
        });
        assertThat(invalidPeselException.getPeselConstraint(), equalTo(PeselConstraint.INCORRECT_CHECK_DIGIT));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "53000397537",
            "48131587553",
            "85200695557",
            "91330733933",
            "49400268346",
            "92531214386",
            "47601028563",
            "64732879437",
            "53800314244",
            "52930717282"
    })
    public void pesel_should_have_correct_birth_date(String string) {
        assertThat(peselValidator.isValid(string), equalTo(false));
        InvalidPeselException invalidPeselException = assertThrows(InvalidPeselException.class, () -> {
            peselValidator.assertIsValid(string);
        });
        assertThat(invalidPeselException.getPeselConstraint(), equalTo(PeselConstraint.INCORRECT_BIRTH_DATE));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "67040275410",
            "49112185311",
            "72030586142",
            "88082595483",
            "64081859634",
            "46041031575",
            "59102843826",
            "47090271277",
            "53021662818",
            "74032698429"
    })
    public void pesel_is_valid(String string) {
        try {
            peselValidator.assertIsValid(string);
        } catch (InvalidPeselException e) {
            fail("Pesel should be valid");
        }
    }

}
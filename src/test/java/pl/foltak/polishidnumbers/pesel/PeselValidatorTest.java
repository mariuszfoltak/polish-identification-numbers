package pl.foltak.polishidnumbers.pesel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PeselValidatorTest {

    @Test
    public void pesel_should_not_be_null() {
        assertThrows(NullPointerException.class, () -> {
            new PeselValidator().isValid(null);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "abc", "toooooolongstring"})
    public void pesel_should_be_11_characters_long(String string) {
        assertThat(new PeselValidator().isValid(string), equalTo(false));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaaaaaaaaaa", "1234567890a", "a1234567890"})
    public void pesel_should_contains_only_numbers(String string) {
        assertThat(new PeselValidator().isValid(string), equalTo(false));
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
        assertThat(new PeselValidator().isValid(string), equalTo(false));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "53000397537",
            "48131587553",
            "85200695557",
            "91330733933",
            "49400268349",
            "92531214389",
            "47601028566",
            "64732879430",
            "53800314240",
            "52930717289"
    })
    public void pesel_should_have_correct_birth_date(String string) {
        assertThat(new PeselValidator().isValid(string), equalTo(false));
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
        assertThat(new PeselValidator().isValid(string), equalTo(true));
    }

}
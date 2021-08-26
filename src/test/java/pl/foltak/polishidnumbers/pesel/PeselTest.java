package pl.foltak.polishidnumbers.pesel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PeselTest {

    @Test
    void pesel_should_not_be_null() {
        assertThrows(NullPointerException.class, () -> {
            new Pesel(null);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "12",
            "123456789012345",
            "abcdefghijk",
            "740326984!9",
            "740326984!9",
            "54070643896",
            "64732879437",
    })
    void pesel_should_be_valid(String string) {
        assertThrows(InvalidPeselException.class, () -> {
            new Pesel(string);
        });
    }

    @ParameterizedTest
    @MethodSource()
    void pesel_should_return_correct_birth_date(String string, LocalDate expectedBirthDate) throws InvalidPeselException {
        Pesel pesel = new Pesel(string);
        assertThat(pesel.getBirthDate(), equalTo(expectedBirthDate));
    }

    private static Stream<Arguments> pesel_should_return_correct_birth_date() {
        return Stream.of(
                Arguments.of("00810127913", LocalDate.of(1800, 1, 1)),
                Arguments.of("23852793210", LocalDate.of(1823, 5, 27)),
                Arguments.of("89031561902", LocalDate.of(1989, 3, 15)),
                Arguments.of("34123155622", LocalDate.of(1934, 12, 31)),
                Arguments.of("37311473502", LocalDate.of(2037, 11, 14)),
                Arguments.of("99242145402", LocalDate.of(2099, 4, 21)),
                Arguments.of("02421302109", LocalDate.of(2102, 2, 13)),
                Arguments.of("84500889806", LocalDate.of(2184, 10, 8)),
                Arguments.of("22663066601", LocalDate.of(2222, 6, 30)),
                Arguments.of("99692945016", LocalDate.of(2299, 9, 29))
        );
    }

    @ParameterizedTest
    @MethodSource()
    void pesel_should_return_correct_sex(String string, Pesel.Sex expectedSex) throws InvalidPeselException {
        Pesel pesel = new Pesel(string);
        assertThat(pesel.getSex(), equalTo(expectedSex));
    }

    private static Stream<Arguments> pesel_should_return_correct_sex() {
        return Stream.of(
                Arguments.of("78021788476", Pesel.Sex.MALE),
                Arguments.of("50062431755", Pesel.Sex.MALE),
                Arguments.of("85040839357", Pesel.Sex.MALE),
                Arguments.of("49042985126", Pesel.Sex.FEMALE),
                Arguments.of("62062181646", Pesel.Sex.FEMALE),
                Arguments.of("78042636169", Pesel.Sex.FEMALE),
                Arguments.of("64011737977", Pesel.Sex.MALE),
                Arguments.of("67051141359", Pesel.Sex.MALE),
                Arguments.of("86070232167", Pesel.Sex.FEMALE),
                Arguments.of("77062575779", Pesel.Sex.MALE)
        );
    }

}
package pl.foltak.polishidnumbers.pesel;

import org.junit.jupiter.api.BeforeEach;
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

class PeselBirthDateDecoderTest {

    private PeselBirthDateDecoder peselBirthDateDecoder;

    @BeforeEach
    void setUp() {
        peselBirthDateDecoder = new PeselBirthDateDecoder();
    }

    @ParameterizedTest
    @MethodSource()
    void pesel_decode_birth_date(String pesel, LocalDate expectedBirthDate) throws InvalidPeselException {
        LocalDate decodedBirthDate = peselBirthDateDecoder.decode(pesel);
        assertThat(decodedBirthDate, equalTo(expectedBirthDate));
    }

    private static Stream<Arguments> pesel_decode_birth_date() {
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
//
//    @ParameterizedTest
//    @ValueSource(strings = {
//            "57110373928",
//            "78070815963",
//            "51031642123",
//            "70020448683",
//            "98073138685",
//    })
//    void sex_should_be_female(String string) throws InvalidPeselException {
//        Pesel.Sex decodedSex = peselBirthDateDecoder.decode(string);
//        assertThat(decodedSex, equalTo(Pesel.Sex.FEMALE));
//    }

}
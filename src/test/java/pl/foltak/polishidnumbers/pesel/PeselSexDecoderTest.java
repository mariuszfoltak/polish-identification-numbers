package pl.foltak.polishidnumbers.pesel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PeselSexDecoderTest {

    private PeselSexDecoder peselSexDecoder;

    @BeforeEach
    void setUp() {
        peselSexDecoder = new PeselSexDecoder();
    }

    @Test
    public void pesel_should_not_be_null() {
        assertThrows(NullPointerException.class, () -> {
            peselSexDecoder.decode(null);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "abc", "toooooolongstring", "92060671492", "47601028563", "84052133373"})
    public void pesel_should_be_valid(String string) {
        assertThrows(InvalidPeselException.class, () -> {
            peselSexDecoder.decode(string);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "88060396619",
            "90041464295",
            "66052199996",
            "69010818333",
            "56031113712",
    })
    public void sex_should_be_male(String string) throws InvalidPeselException {
        Pesel.Sex decodedSex = peselSexDecoder.decode(string);
        assertThat(decodedSex, equalTo(Pesel.Sex.MALE));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "57110373928",
            "78070815963",
            "51031642123",
            "70020448683",
            "98073138685",
    })
    public void sex_should_be_female(String string) throws InvalidPeselException {
        Pesel.Sex decodedSex = peselSexDecoder.decode(string);
        assertThat(decodedSex, equalTo(Pesel.Sex.FEMALE));
    }

}
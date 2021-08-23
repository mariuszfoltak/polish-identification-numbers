package pl.foltak.polishidnumbers.pesel;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class PeselValidatorTest {

    @Test
    public void peselIsValid() {
        String test = "v1.10.1-32-ga03dea36";
        Pattern pattern = Pattern.compile("v(\\d+(\\.\\d+)+).*");
        Matcher matcher = pattern.matcher(test);
        if (matcher.find()) {
            assertEquals("1_10_1", matcher.group(1).replace('.', '_'));
        } else {
            fail();
        }
    }

}
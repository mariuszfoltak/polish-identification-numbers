package pl.foltak.polishidnumbers.pesel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Decodes birth date for given PESEL.
 */
class PeselBirthDateDecoder {

    /**
     * Year and month corrections list.
     */
    private static final List<YearCorrection> yearCorrections = Arrays.asList(
            new YearCorrection(80, 1800),
            new YearCorrection(60, 2200),
            new YearCorrection(40, 2100),
            new YearCorrection(20, 2000),
            new YearCorrection(0, 1900)
    );

    /**
     * Decodes birth date for given PESEL.
     *
     * @param pesel pesel number
     * @return decoded birth date
     */
    LocalDate decode(String pesel) {
        int year = Integer.parseInt(pesel.substring(0, 2));
        final int monthNumber = Integer.parseInt(pesel.substring(2, 4));
        int day = Integer.parseInt(pesel.substring(4, 6));

        YearCorrection yearCorrection = yearCorrections.stream().filter(yc -> yc.test(monthNumber)).findFirst()
                .orElseThrow(RuntimeException::new);

        year += yearCorrection.yearOffset;
        int month = monthNumber - yearCorrection.monthOffset;
        return LocalDate.of(year, month, day);
    }

    /**
     * Class contains year correction for PESEL
     */
    static class YearCorrection {
        /**
         * Month offset
         */
        final int monthOffset;
        /**
         * Year offset
         */
        final int yearOffset;

        /**
         * Constructor
         *
         * @param monthOffset month offset
         * @param yearCorrection year offset
         */
        YearCorrection(int monthOffset, int yearCorrection) {
            this.monthOffset = monthOffset;
            this.yearOffset = yearCorrection;
        }

        /**
         * Tests if year correction should be used for pesel
         *
         * @param month month number from pesel
         * @return true if correction should be user for given month number
         */
        boolean test(int month) {
            return month >= monthOffset;
        }
    }
}

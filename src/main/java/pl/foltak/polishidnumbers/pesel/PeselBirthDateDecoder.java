package pl.foltak.polishidnumbers.pesel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class PeselBirthDateDecoder {
    private static final List<YearCorrection> yearCorrections = Arrays.asList(
            new YearCorrection(80, 1800),
            new YearCorrection(60, 2200),
            new YearCorrection(40, 2100),
            new YearCorrection(20, 2000),
            new YearCorrection(0, 1900)
    );

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

    static class YearCorrection {
        final int monthOffset;
        final int yearOffset;

        YearCorrection(int monthOffset, int yearCorrection) {
            this.monthOffset = monthOffset;
            this.yearOffset = yearCorrection;
        }

        boolean test(int month) {
            return month >= monthOffset;
        }
    }
}

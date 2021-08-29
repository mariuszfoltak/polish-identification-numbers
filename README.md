# Polish Identification Numbers for Java
[![Build Status](https://app.travis-ci.com/mariuszfoltak/polish-identification-numbers.svg?branch=main)](https://app.travis-ci.com/mariuszfoltak/polish-identification-numbers)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=mariuszfoltak_polish-identification-numbers&metric=coverage)](https://sonarcloud.io/dashboard?id=mariuszfoltak_polish-identification-numbers)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=mariuszfoltak_polish-identification-numbers&metric=alert_status)](https://sonarcloud.io/dashboard?id=mariuszfoltak_polish-identification-numbers)
[![GitHub](https://img.shields.io/github/license/mariuszfoltak/polish-identification-numbers)](https://github.com/mariuszfoltak/polish-identification-numbers/blob/master/LICENSE)

Java library for validation and decoding PESEL (Powszechny Elektroniczny System Ewidencji Ludności, Universal Electronic System for Registration of the Population).

## Usage
### Validation
To validate PESEL use PeselValidator class
```java
import pl.foltak.polishidnumbers.pesel.PeselValidator;
import pl.foltak.polishidnumbers.pesel.InvalidPeselException;

...

PeselValidator peselValidator = new PeselValidator();
boolean isPeselValid = peselValidator.isValid("79461078451");
// or
try {
    peselValidator.assertIsValid("79461078451");
} catch (InvalidPeselException e) {
    PeselConstraint violation = e.getPeselConstraint();
    // handle exception
}
```

`InvalidPeselException` has peselConstraint field which contains information about violation
of invalid pesel number like incorrect length, characters, check digit or encoded birth date.

### Decoding PESEL information

To decode birth date or person sex from PESEL use Pesel class
```java
import pl.foltak.polishidnumbers.pesel.Pesel;
import pl.foltak.polishidnumbers.pesel.InvalidPeselException;

...

try {
    Pesel pesel = new Pesel("79461078451");
    LocalDate birthDate = pesel.getBirthDate();
    Pesel.Sex sex = pesel.getSex();
} catch (InvalidPeselException e) {
    PeselConstraint violation = e.getPeselConstraint();
    // handle exception
}
``` 

## License
[MIT](https://github.com/mariuszfoltak/polish-identification-numbers/blob/master/LICENSE)

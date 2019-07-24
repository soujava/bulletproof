package org.jnosql;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.Year;
import java.util.Locale;

public class PlayerTestDataBuilder {

    public static final CurrencyUnit USD = Monetary.getCurrency(Locale.US);

    public static final MonetaryAmount SALARY = Money.of(1_000_000, USD);

    public static final Email EMAIL = Email.of("marta@marta.com");

    public static final Year START = Year.now();

    public static final String NAME = "Marta";

    public static final Position POSITION = Position.FORWARD;

    public static Player martaPlayer() {
        return Player.builder().withName(NAME)
                .withEmail(EMAIL)
                .withSalary(SALARY)
                .withStart(START)
                .withPosition(POSITION)
                .build();
    }
}

package org.jnosql;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static org.jnosql.PlayerTestDataBuilder.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {


    @Test
    public void shouldReturnErrorWhenStartIsFuture() {
        Year year = Year.now().plus(1, ChronoUnit.YEARS);
        assertThrows(IllegalArgumentException.class, () -> Player.builder().withStart(year));
    }

    @Test
    public void shouldReturnErrorWhenStartBeforeSoccer() {
        Year year = Player.SOCCER_BORN.plus(-1, ChronoUnit.YEARS);
        assertThrows(IllegalArgumentException.class, () -> Player.builder().withStart(year));
    }

    @Test
    public void shouldReturnErrorWhenEndBeforeSoccer() {
        Year year = Player.SOCCER_BORN.plus(-1, ChronoUnit.YEARS);
        assertThrows(IllegalArgumentException.class, () -> Player.builder().withEnd(year));
    }

    @Test
    public void shouldReturnErrorWhenThereIsInvalidPeriod() {
        Year start = Year.now().plus(1, ChronoUnit.YEARS);
        Year end = Year.now();
        assertThrows(IllegalArgumentException.class, () -> Player.builder().withStart(start).withEnd(end));
    }

    @Test
    public void shouldRefuseNegativeSalary() {
        CurrencyUnit usd = Monetary.getCurrency(Locale.US);
        MonetaryAmount salary = Money.of(-1, usd);
        assertThrows(IllegalArgumentException.class, () -> Player.builder().withSalary(salary));
    }

    @Test
    public void shouldCreateInstance() {
        Player marta = Player.builder().withName(NAME)
                .withEmail(EMAIL)
                .withSalary(SALARY)
                .withStart(START)
                .withPosition(POSITION)
                .build();

        Assertions.assertNotNull(marta);
    }

    @Test
    public void shouldNotAllowSetWrongPeriod() {
        Year end = START.plus(-1, ChronoUnit.YEARS);
        Player marta = PlayerTestDataBuilder.martaPlayer();
        assertThrows(IllegalArgumentException.class, () -> marta.setEnd(end));
    }

    @Test
    public void shouldCreateInstanceDSL() {
        CurrencyUnit usd = Monetary.getCurrency(Locale.US);
        MonetaryAmount salary = Money.of(1_000_000, usd);
        Email email = Email.of("marta@marta.com");
        Year start = Year.now();
        Year end = start.plus(1, ChronoUnit.YEARS);

        Player marta = Player.name("Marta")
                .start(start)
                .end(end).email(email)
                .position(Position.FORWARD)
                .salary(salary);
        Assertions.assertNotNull(marta);
    }
}
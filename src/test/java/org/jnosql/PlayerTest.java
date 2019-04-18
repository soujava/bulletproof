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
        CurrencyUnit usd = Monetary.getCurrency(Locale.US);
        MonetaryAmount salary = Money.of(1_000_000, usd);
        Email email = Email.of("marta@marta.com");
        Year start = Year.now();

        Player marta = Player.builder().withName("Marta")
                .withEmail(email)
                .withSalary(salary)
                .withStart(start)
                .withPosition(Position.FORWARD)
                .build();

        Assertions.assertNotNull(marta);
    }

    @Test
    public void shouldNotAllowSetWrongPeriod() {
        CurrencyUnit usd = Monetary.getCurrency(Locale.US);
        MonetaryAmount salary = Money.of(1_000_000, usd);
        Email email = Email.of("marta@marta.com");
        Year start = Year.now();
        Year end = start.plus(-1, ChronoUnit.YEARS);

        Player marta = Player.builder().withName("Marta")
                .withEmail(email)
                .withSalary(salary)
                .withStart(start)
                .withPosition(Position.FORWARD)
                .build();
        assertThrows(IllegalArgumentException.class, () -> marta.setEnd(end));
    }

}
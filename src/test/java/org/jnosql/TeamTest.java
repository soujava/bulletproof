package org.jnosql;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.Year;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamTest {


    @Test
    public void shouldReturnNPEWhenNameIsNull() {
        assertThrows(NullPointerException.class, () -> Team.of(null));
    }


    @Test
    public void shouldReturnErrorWhenPlayerIsNull() {
        Team bahia = Team.of("Bahia");
        assertThrows(NullPointerException.class, () -> bahia.add(null));

    }

    @Test
    public void shouldReturnErrorWhenTeamIsOutOfLimit() {
        Team bahia = Team.of("Bahia");
        CurrencyUnit usd = Monetary.getCurrency(Locale.US);
        Year start = Year.now();

        for (int index = 0; index < Team.SIZE; index++) {
            MonetaryAmount salary = Money.of(1_000_000, usd);
            Email email = Email.of(index + "email@email.com");

            Player player = Player.builder().withName("Player " + index)
                    .withEmail(email)
                    .withSalary(salary)
                    .withStart(start)
                    .withPosition(Position.FORWARD)
                    .build();
            bahia.add(player);
        }

        MonetaryAmount salary = Money.of(1_000_000, usd);
        Email email = Email.of("email@email.com");
        Player player = Player.builder().withName("Marta")
                .withEmail(email)
                .withSalary(salary)
                .withStart(start)
                .withPosition(Position.FORWARD)
                .build();

        assertThrows(IllegalArgumentException.class, () -> bahia.add(player));
    }


}
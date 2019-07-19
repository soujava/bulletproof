package org.jnosql;

import org.jnosql.FluentPlayer.PlayerEmail;
import org.jnosql.FluentPlayer.PlayerEnd;
import org.jnosql.FluentPlayer.PlayerPosition;
import org.jnosql.FluentPlayer.PlayerSalary;
import org.jnosql.FluentPlayer.PlayerStart;

import javax.money.MonetaryAmount;
import java.time.Year;
import java.util.Objects;

public class PlayerDSL implements PlayerStart,
        PlayerEnd, PlayerEmail, PlayerPosition,
        PlayerSalary, FluentPlayer {

    static final Year SOCCER_BORN = Year.of(1863);

    PlayerDSL() {
    }

    private String name;

    private Year start;

    private Year end;

    private Email email;

    private Position position;

    private MonetaryAmount salary;

    @Override
    public PlayerStart name(String name) {
        Objects.requireNonNull(name, "name is required");
        return this;
    }

    @Override
    public FluentPlayer.PlayerEnd start(Year start) {
        Objects.requireNonNull(start, "start is required");
        if (Year.now().isBefore(start)) {
            throw new IllegalArgumentException("you cannot start in the future");
        }
        if (SOCCER_BORN.isAfter(start)) {
            throw new IllegalArgumentException("Soccer was not born on this time");
        }
        this.start = start;
        return this;
    }

    @Override
    public FluentPlayer.PlayerEmail end(Year end) {
        Objects.requireNonNull(end, "end is required");

        if (start != null && start.isAfter(end)) {
            throw new IllegalArgumentException("the last year of a player must be equal or higher than the start.");
        }

        if (SOCCER_BORN.isAfter(end)) {
            throw new IllegalArgumentException("Soccer was not born on this time");
        }
        this.end = end;
        return this;
    }

    @Override
    public FluentPlayer.PlayerPosition email(Email email) {
        this.email = Objects.requireNonNull(email, "email is required");
        return this;
    }

    @Override
    public FluentPlayer.PlayerSalary position(Position position) {
        this.position = Objects.requireNonNull(position, "position is required");
        return this;
    }

    @Override
    public Player salary(MonetaryAmount salary) {
        Objects.requireNonNull(salary, "salary is required");
        if (salary.isNegativeOrZero()) {
            throw new IllegalArgumentException("A player needs to earn money to play; otherwise, it is illegal.");
        }
        this.salary = salary;
        return new Player(name, start, end, email, position, salary);
    }


}

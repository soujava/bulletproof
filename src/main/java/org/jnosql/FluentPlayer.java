package org.jnosql;

import javax.money.MonetaryAmount;
import java.time.Year;

public interface FluentPlayer {


    PlayerStart name(String name);

    interface PlayerStart {
        PlayerEnd start(Year start);
    }

    interface  PlayerEnd {
        PlayerEmail end(Year start);
    }

    interface PlayerEmail {
        PlayerPosition email(Email email);
    }

    interface PlayerPosition {
        PlayerSalary position(Position position);
    }

    interface PlayerSalary {
        Player salary(MonetaryAmount salary);
    }

}


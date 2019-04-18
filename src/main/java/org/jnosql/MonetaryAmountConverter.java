package org.jnosql;

import org.javamoney.moneta.Money;
import org.jnosql.artemis.AttributeConverter;

import javax.money.MonetaryAmount;

public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String> {

    @Override
    public String convertToDatabaseColumn(MonetaryAmount attribute) {
        if (attribute != null) {
            return attribute.toString();
        }
        return null;
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            return Money.parse(dbData);
        }
        return null;
    }
}

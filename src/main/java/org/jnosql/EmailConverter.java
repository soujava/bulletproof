package org.jnosql;

import org.jnosql.artemis.AttributeConverter;

public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email attribute) {
        if (attribute != null) {
            return attribute.get();
        }
        return null;
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            return Email.of(dbData);
        }
        return null;
    }
}

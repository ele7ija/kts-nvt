package ftn.ktsnvt.culturalofferings.converter;

import ftn.ktsnvt.culturalofferings.model.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        if (userRole == null) {
            return null;
        }
        return userRole.name();
    }

    @Override
    public UserRole convertToEntityAttribute(String userRole) {
        if (userRole == null) {
            return null;
        }

        return Stream.of(UserRole.values())
                .filter(c -> c.name().equals(userRole))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

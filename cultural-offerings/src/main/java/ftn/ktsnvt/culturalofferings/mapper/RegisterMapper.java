package ftn.ktsnvt.culturalofferings.mapper;

import ftn.ktsnvt.culturalofferings.dto.RegisterDTO;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class RegisterMapper implements MapperInterface<User, RegisterDTO> {

    @Override
    public User toEntity(RegisterDTO dto) {
        return new User(
                dto.getEmail(),
                dto.getPassword(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getUserRole() == null ? UserRole.USER : dto.getUserRole(),
                false,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );
    }

    @Override
    public RegisterDTO toDto(User entity) {
        return new RegisterDTO(
                entity.getEmail(),
                entity.getPassword(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getRole()
        );
    }
}

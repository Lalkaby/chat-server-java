package by.temniakov.api.factory;

import by.temniakov.api.dto.UserDTO;
import by.temniakov.store.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDTOFactory {
    public UserDTO mapProjectDTO(UserEntity entity){
        return UserDTO
                .builder()
                .username(entity.getUsername())
                .build();
    }
}

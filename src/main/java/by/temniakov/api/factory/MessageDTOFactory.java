package by.temniakov.api.factory;

import by.temniakov.api.dto.MessageDTO;
import by.temniakov.api.dto.UserDTO;
import by.temniakov.store.entity.MessageEntity;
import by.temniakov.store.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageDTOFactory {
    public MessageDTO mapMessageDTO(MessageEntity entity){
        return MessageDTO
                .builder()
                .message(entity.getMessage())
                .createdAt(entity.getCreatedAt())
                .username(entity.getUser().getUsername())
                .build();
    }
}

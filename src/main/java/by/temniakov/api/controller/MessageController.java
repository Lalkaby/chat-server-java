package by.temniakov.api.controller;

import by.temniakov.api.controller.helper.ControllerHelper;
import by.temniakov.api.dto.MessageDTO;
import by.temniakov.api.factory.MessageDTOFactory;
import by.temniakov.store.entity.MessageEntity;
import by.temniakov.store.entity.UserEntity;
import by.temniakov.store.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Transactional
@AllArgsConstructor
@CrossOrigin
public class MessageController {

    private final MessageRepository messageRepository;
    private final ControllerHelper controllerHelper;
    private final MessageDTOFactory messageDTOFactory;

    public static final String GET_MESSAGES = "/message/list";
    public static final String POST_MESSAGE = "/message";

    @GetMapping(GET_MESSAGES)
    public List<MessageDTO> getMessages(
            @RequestParam(name = "token") UUID token){
        controllerHelper.getUserOrThrowException(token);
        List<MessageEntity> topMessages = messageRepository
                .findTopMessagesOrderByCreatedAt();
        return topMessages.stream()
                .map(messageDTOFactory::mapMessageDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(POST_MESSAGE)
    public Long postMessage(
            @RequestParam(name = "token") UUID token,
            @RequestParam(name = "message") String message){
        UserEntity user = controllerHelper.getUserOrThrowException(token);
        MessageEntity savedMessage = messageRepository.saveAndFlush(
                MessageEntity.builder()
                        .message(message)
                        .user(user)
                        .build());

        return savedMessage.getId();
    }

}

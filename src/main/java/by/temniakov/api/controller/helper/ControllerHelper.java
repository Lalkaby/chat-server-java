package by.temniakov.api.controller.helper;

import by.temniakov.store.entity.UserEntity;
import by.temniakov.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@Transactional
public class ControllerHelper {

    private final UserRepository userRepository;

    public UserEntity getUserOrThrowException(UUID token) {
        return userRepository
                .findById(token)
                .orElseThrow(() ->
                        new RuntimeException(
                                String.format(
                                        "Such user doesn't register"
                                )
                        )
                );
    }
}
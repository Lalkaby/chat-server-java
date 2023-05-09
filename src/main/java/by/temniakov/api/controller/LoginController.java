package by.temniakov.api.controller;

import by.temniakov.api.dto.AckDTO;
import by.temniakov.store.entity.UserEntity;
import by.temniakov.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Transactional
@CrossOrigin
public class LoginController {

    private final UserRepository userRepository;

    public static final String LOGIN_USER = "/login";
    public static final String LOGOUT_USER = "/logout";

    @PostMapping(LOGIN_USER)
    public UUID loginNewUser(
            @RequestParam(name = "username",required = true) String username ){
         userRepository.findByUsername(username)
                 .ifPresent(s -> {throw new RuntimeException("Already in chat");});

       UserEntity user = UserEntity.builder().username(username).build();

        userRepository.saveAndFlush(user);

        return user.getToken();
    }

    @DeleteMapping(LOGOUT_USER)
    public AckDTO logoutUser(
            @RequestParam(name = "token") UUID
                    token ){

        userRepository.findById(token).orElseThrow(() -> new RuntimeException("No such user"));

        userRepository.deleteById(token);

        return AckDTO.makeDefault(true);
    }

}

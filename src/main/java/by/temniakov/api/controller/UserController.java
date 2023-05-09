package by.temniakov.api.controller;

import by.temniakov.api.dto.UserDTO;
import by.temniakov.api.factory.UserDTOFactory;
import by.temniakov.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Transactional
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;
    private final UserDTOFactory userDTOFactory;

    public static final String GET_LOGGED_USERS = "/user/list";

    @GetMapping(GET_LOGGED_USERS)
    public List<UserDTO> getUsersList(
            @RequestParam(name = "token",required = true)
            UUID token
            ){
        userRepository.findById(token).orElseThrow(() -> new RuntimeException("No such user logged"));

        return userRepository.findAll().stream()
                .map(userDTOFactory::mapProjectDTO)
                .collect(Collectors.toList());
    }
}

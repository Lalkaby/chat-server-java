package by.temniakov.api.dto;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NonNull
    private String username;
}

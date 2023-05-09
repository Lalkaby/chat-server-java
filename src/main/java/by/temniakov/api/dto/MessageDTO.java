package by.temniakov.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    @NonNull
    private String message;

    @NonNull
    @JsonProperty("created_at")
    private Instant createdAt;

    @NonNull
    private String username;
}

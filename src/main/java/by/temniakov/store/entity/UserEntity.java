package by.temniakov.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chat_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID token;

    private String username;

    @Builder.Default
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<MessageEntity>  messages = new ArrayList<>();
}

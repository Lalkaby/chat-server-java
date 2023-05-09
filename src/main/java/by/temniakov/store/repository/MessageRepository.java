package by.temniakov.store.repository;

import by.temniakov.store.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity,Long> {
    @Query(value = "select m from MessageEntity m order by m.createdAt limit 50")
    List<MessageEntity> findTopMessagesOrderByCreatedAt();
}

package krsu.beks.repository;

import java.util.List;

import krsu.beks.model.Status;
import krsu.beks.model.Task;
import krsu.beks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findTaskByStatusAndAuthor(Status status, User user);

    void deleteById(Long id);

    @Query( value = "select * from task where status NOT IN ('DONE') and deadline <= CURRENT_DATE", nativeQuery = true)
    List<Task> findAllByStatus();
}

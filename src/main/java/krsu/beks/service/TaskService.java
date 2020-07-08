package krsu.beks.service;

import krsu.beks.model.Status;
import krsu.beks.model.Task;
import krsu.beks.model.User;
import krsu.beks.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TaskService {

    private TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public boolean addNewTask(User user, Task task) {
        task.setAuthor(user);
        task.setCreateOnDate(LocalDateTime.now());
        task.setStatus(Status.NEW);
        task.setDeadline(task.getDeadline());
        taskRepo.save(task);
        return true;
    }

    public Iterable<Task> listTasks(Status status, User user) {
        return taskRepo.findTaskByStatusAndAuthor(status,user);
    }

    public void saveTask(User user, Task task, String name, String description, Status status, Boolean importance) {
        task.setName(name);
        task.setDescription(description);
        task.setStatus(status);
        task.setImportance(importance);
        task.setCreateOnDate(LocalDateTime.now());
        task.setAuthor(user);
        taskRepo.save(task);
    }

    public boolean deleteTask(User user, Task task) {
        if (user.getUsername().equals(task.getAuthor().getUsername()))
        taskRepo.deleteById(task.getId());
        return true;
    }



}

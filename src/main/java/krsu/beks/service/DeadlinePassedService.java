package krsu.beks.service;


import krsu.beks.model.Task;
import krsu.beks.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeadlinePassedService {

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    TaskRepo taskRepo;

    @Scheduled(cron = "*/10 * * * * *")
    public void sendMessage() {
        List<Task> taskList = taskRepo.findAllByStatus();
        if (taskList.isEmpty()){
            return;
        }
        for (Task task: taskList) {
            if (task.getDeadline().equals(LocalDate.now())) {
                String message = "Deadline passed";
                String subject = "Deadline";
                mailSenderService.send(task.getAuthor().getEmail(), subject, message);
            }
        }

    }
    
}

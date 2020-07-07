package krsu.beks.service;


import krsu.beks.model.Task;
import krsu.beks.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DeadlinePassedService {

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    TaskRepo taskRepo;

   private LocalDate date = LocalDate.now();

    @Scheduled(cron = "*/10 * * * * *")
    public void sendMessage() {


        for (Task task: taskRepo.findAllByStatus()) {
            String message = "Deadline passed";
            String subject = "Deadline";
            mailSenderService.send(task.getAuthor().getEmail(),subject,message);
        }

    }
    
}

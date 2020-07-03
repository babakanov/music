package krsu.beks.service;


import krsu.beks.model.Status;
import krsu.beks.model.Task;
import krsu.beks.model.User;
import krsu.beks.repository.TaskRepo;
import krsu.beks.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.util.Date;

@Service
public class DeadlinePassedService {

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    TaskRepo taskRepo;

   private LocalDate date = LocalDate.now();

    @Scheduled(cron = "*/10 * * * * *")
    public void sendMessage() {

        for (Task task: taskRepo.findAll()){
//            if (!(task.getStatus().equals(Status.DONE) ) && (task.getDeadline().isEqual(date))) {
            if (task.getDeadline().getMonth().equals(date.getMonth())) {
                String message = "Deadline passed";
                String subject = "Deadline";
                mailSenderService.send(task.getAuthor().getEmail(),subject,message);
            }
        }
    }
    
}

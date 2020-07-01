package krsu.beks.controller;

import krsu.beks.model.Task;
import krsu.beks.model.User;
import krsu.beks.repository.TaskRepo;
import krsu.beks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/deleteTask")
public class DeleteTaskController {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private TaskService taskService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("{task1}")
    public String taskDeleteForm(
            @PathVariable Task task1,
            Model model) {
        model.addAttribute("task1", task1);
        return "taskDelete";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public String TaskDelete(
            @AuthenticationPrincipal User user,
            @RequestParam("taskId") Task task,
            Model model
    ) {
        if (!taskService.deleteTask(user, task)) {
            model.addAttribute("taskErrorDelete", "Task delete error!");
            return "taskDelete";
        }
        return "redirect:/task/allTasks";
    }

}

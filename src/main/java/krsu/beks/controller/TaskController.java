package krsu.beks.controller;

import krsu.beks.model.Status;
import krsu.beks.model.Task;
import krsu.beks.model.User;
import krsu.beks.repository.TaskRepo;
import krsu.beks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private TaskService taskService;

    @GetMapping("allTasks")
    public ModelAndView tasks(@AuthenticationPrincipal User user) {
        return new ModelAndView("allTasks")
                .addObject("newTask", taskService.listTasks(Status.NEW, user))
                .addObject("ipTask", taskService.listTasks(Status.IN_PROGRESS, user))
                .addObject("doneTask", taskService.listTasks(Status.DONE, user));
    }

    @GetMapping("newTask")
    public ModelAndView allTasks(@AuthenticationPrincipal User user) {
        Iterable<Task> songIterable = taskRepo.findAll();

        return new ModelAndView("newTask")
                .addObject("taskList", songIterable);

    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("newTask")
    public String addNewTask(
            @AuthenticationPrincipal User user,
            @Valid Task task,
            Model model) {

        if (!taskService.addNewTask(user, task)) {
            model.addAttribute("taskError", "Task error!");
            return "newTask";
        }
        return "redirect:/task/allTasks";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("{task}")
    public String taskEditForm(
            @PathVariable Task task,
            Model model) {
        model.addAttribute("task", task);
        return "taskEdit";
    }


    @PreAuthorize("hasAuthority('USER')")
    @PostMapping()
    public String TaskSave(
            @AuthenticationPrincipal User user,
            @RequestParam("taskId") Task task,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Status status,
            @RequestParam Boolean importance

    ) {
        taskService.saveTask(user, task,name, description, status, importance);
        return "redirect:/task/allTasks";
    }

}
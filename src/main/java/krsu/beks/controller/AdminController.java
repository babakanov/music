package krsu.beks.controller;

import krsu.beks.model.User;
import krsu.beks.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private UserSevice userSevice;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("addUser")
    public String getAdminProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        return "addUser";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("addUser")
    public String addUserAdmin(
            @Valid User user,
            BindingResult bindingResult,
            Model model) {

        if (user.getPassword() != null) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            return "addUser";
        }

        if (user.getEmail() != null) {
            model.addAttribute("emailErrors", "Email are different!");
        }

        if (!userSevice.addUserAdmin(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "addUser";
        }
        return "redirect:/user";
    }

}

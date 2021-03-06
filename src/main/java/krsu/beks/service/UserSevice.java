package krsu.beks.service;

import krsu.beks.model.Role;
import krsu.beks.model.User;
import krsu.beks.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserSevice implements UserDetailsService {

    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    MailSenderService mailSender;

    public UserSevice(UserRepo userRepo, PasswordEncoder passwordEncoder,  MailSenderService mailSender) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());

        userRepo.save(user);


        return true;
    }

    public boolean addUserAdmin(User user) {
        User existingUser = userRepo.findByUsername(user.getUsername());
        User existingEmail = userRepo.findByEmail(user.getEmail());

        if (existingUser != null) {
            return false;
        }

        if (existingEmail != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());

        userRepo.save(user);
        return true;
    }


    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, Boolean active, Map<String, String> form) {
        user.setActive(active);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public void updateProfile(User user, String username, String password, String email) {
        user.setUsername(username);
        user.setEmail(email);
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepo.save(user);
    }
}

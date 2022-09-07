package com.alkemy.ong.utils.seeder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.alkemy.ong.security.model.Role;
import com.alkemy.ong.security.model.User;
import com.alkemy.ong.security.repository.RolesRepository;
import com.alkemy.ong.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SeederDataBaseImpl implements CommandLineRunner, ISeederDataBase {

    private final RolesRepository rolesRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final MessageSource messageSource;
    private final BCryptPasswordEncoder encoder;

    private final Logger LOG = LoggerFactory
            .getLogger(SeederDataBaseImpl.class);

    @Override
    public void run(String... args) throws Exception {
        seedActivitiesTable(5);
        seedRolesTable(2);
        seedUsersTable(20);
    }

    @Override
    public void seedActivitiesTable(int amount) {
        if (activityRepository.count() == 0) {
            List<Activity> activities = new ArrayList<>(amount);
            int number = 1;
            LocalDateTime date = LocalDateTime.now();

            for (int i = 0; i < amount; i++) {
                Activity activity = new Activity();
                activity.setName("name" + number);
                activity.setContent("content" + number);
                activity.setImage("image" + number);
                activity.setCreationDate(date);
                activity.setUpdateDate(date);
                activities.add(activity);
                number++;
            }
            activityRepository.saveAll(activities);

            LOG.info(messageSource.getMessage("info-positive",
                    new Object[]{"Activities table", amount}, Locale.US));
        } else {
            LOG.info(messageSource.getMessage("info-negative",
                    new Object[]{"Activities table"}, Locale.US));
        }

    }

    @Override
    public void seedRolesTable(int amount) {
        if (rolesRepository.count() == 0) {
            List<Role> roles = new ArrayList<>(amount);
            LocalDateTime date = LocalDateTime.now();

            Role role1 = new Role("ADMIN", "ADMIN", date, date);
            Role role2 = new Role("USER", "USER", date, date);
            roles.add(role1);
            roles.add(role2);
            rolesRepository.saveAll(roles);

            LOG.info(messageSource.getMessage("info-positive",
                    new Object[]{"Roles table", amount}, Locale.US));
        } else {
            LOG.info(messageSource.getMessage("info-negative",
                    new Object[]{"Roles table"}, Locale.US));
        }

    }


    @Override
    public void seedUsersTable(int amount) {
        if (userRepository.count() == 0) {
            List<User> users = new ArrayList<>(amount);
            List<Role> roles = rolesRepository.findAll();
            int number = 1;
            LocalDateTime date = LocalDateTime.now();

            for (int i = 0; i < amount; i++) {
                User user = new User();
                user.setFirstName("firstname" + number);
                user.setLastName("lastname" + number);
                user.setEmail("email" + number + "@gmail.com");
                user.setPassword(encoder.encode("password"));
                user.setCreationDate(date);
                user.setUpdateDate(date);
                user.setRole((i < (amount/2) ? roles.get(0) : roles.get(1)));
                users.add(user);
                number++;

            }
            userRepository.saveAll(users);

            LOG.info(messageSource.getMessage("info-positive",
                    new Object[]{"Users table", amount}, Locale.US));
        } else {
            LOG.info(messageSource.getMessage("info-negative",
                    new Object[]{"Users table"}, Locale.US));
        }
    }

}




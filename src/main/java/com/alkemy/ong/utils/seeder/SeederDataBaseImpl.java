package com.alkemy.ong.utils.seeder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.alkemy.ong.enums.RoleEnum;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;
    private final MessageSource messageSource;
    private final BCryptPasswordEncoder encoder;

    private final Logger LOG = LoggerFactory
            .getLogger(SeederDataBaseImpl.class);

    @Override
    public void run(String... args) throws Exception {
        seedActivitiesTable(25);
        seedRolesTable();
        seedUsersTable();
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
    public void seedCategoriesTable(int amount) {
        if (categoryRepository.count() == 0) {
            List<Category> categories = new ArrayList<>(amount);
            int number = 1;
            LocalDateTime date = LocalDateTime.now();

            for (int i = 0; i < amount; i++) {
                Category category = new Category();
                category.setName("name" + number);
                category.setDescription("description" + number);
                category.setImage("image" + number);
                category.setCreationDate(date);
                category.setUpdateDate(date);
                categories.add(category);
                number++;
            }
            categoryRepository.saveAll(categories);

            LOG.info(messageSource.getMessage("info-positive",
                    new Object[]{"Categories table", amount}, Locale.US));
        } else {
            LOG.info(messageSource.getMessage("info-negative",
                    new Object[]{"Categories table"}, Locale.US));
        }

    }

    @Override
    public void seedRolesTable() {
        if (rolesRepository.count() == 0) {
            List<Role> roles = new ArrayList<>();
            LocalDateTime date = LocalDateTime.now();

            Role role1 = new Role(1L, RoleEnum.ADMIN, "ADMIN", date, date);
            Role role2 = new Role(2L,RoleEnum.USER, "USER", date, date);
            Role role3 = new Role(3L,RoleEnum.DEVELOPER, "DEVELOPER", date, date);
            roles.add(role1);
            roles.add(role2);
            roles.add(role3);
            rolesRepository.saveAll(roles);

            LOG.info(messageSource.getMessage("info-positive",
                    new Object[]{"Roles table", 3}, Locale.US));
        } else {
            LOG.info(messageSource.getMessage("info-negative",
                    new Object[]{"Roles table"}, Locale.US));
        }

    }

    @Override
    public void seedUsersTable() {
        List<User> users = null;
        if (userRepository.count() == 0) {
            users = new ArrayList<>();
            List<Role> roles = rolesRepository.findAll();
            LocalDateTime date = LocalDateTime.now();
            users.add(new User(1L, "Antonio", "García", "antoniogarcia@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(0), Boolean.FALSE, date, date));
            users.add(new User(2L, "Carmen", "Martinez", "carmenmartinez@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(0), Boolean.FALSE, date, date));
            users.add(new User(3L, "Francisco", "Lopez", "franciscolopez@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(0), Boolean.FALSE, date, date));
            users.add(new User(4L, "Isabel", "Sanchez", "isabelsanchez@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(0), Boolean.FALSE, date, date));
            users.add(new User(5L, "Manuel", "Gomez", "manuelgomez@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(0), Boolean.FALSE, date, date));
            users.add(new User(6L, "Martín", "Canales", "martincanales@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(0), Boolean.FALSE, date, date));
            users.add(new User(7L, "Francisca", "Fernandez", "franciscafernandez@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(0), Boolean.FALSE, date, date));
            users.add(new User(8L, "Juan", "Perez", "juanperez@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(0), Boolean.FALSE, date, date));
            users.add(new User(9L, "Luciano", "Gonzalez", "lucianogonzalez@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(0), Boolean.FALSE, date, date));
            users.add(new User(10L, "Natalia", "Moreno", "nataliamoreno@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(0), Boolean.FALSE, date, date));
            users.add(new User(11L, "Miguel", "Gimenez", "miguelgimenez@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(1), Boolean.FALSE, date, date));
            users.add(new User(12L, "Dolores", "Rodriguez", "doloresrodriguez@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(1), Boolean.FALSE, date, date));
            users.add(new User(13L, "José", "Navarro", "josenavarro@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(1), Boolean.FALSE, date, date));
            users.add(new User(14L, "Ana", "Ruiz", "anaruiz@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(1), Boolean.FALSE, date, date));
            users.add(new User(15L, "Carlos", "Diaz", "carlosdiaz@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(1), Boolean.FALSE, date, date));
            users.add(new User(16L, "Lucía", "Serrano", "luciaserrano@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(1), Boolean.FALSE, date, date));
            users.add(new User(17L, "Daniel", "Hernandez", "danielhernandez@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(1), Boolean.FALSE, date, date));
            users.add(new User(18L, "Cristina", "Munoz", "cristinamunoz@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(1), Boolean.FALSE, date, date));
            users.add(new User(19L, "Luis", "Saez", "luissaez@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(1), Boolean.FALSE, date, date));
            users.add(new User(20L, "Juana", "Alfaro", "juanaalfaro@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(1), Boolean.FALSE, date, date));
            users.add(new User(21L, "Federico", "Carrasco", "federicocarrasco@gmail.com", encoder.encode("password"), "photo.jpg", roles.get(1), Boolean.FALSE, date, date));


            userRepository.saveAll(users);

            LOG.info(messageSource.getMessage("info-positive",
                    new Object[]{"Users table", 21}, Locale.US));
        } else {

            LOG.info(messageSource.getMessage("info-negative",
                    new Object[]{"Users table"}, Locale.US));
        }
    }
}








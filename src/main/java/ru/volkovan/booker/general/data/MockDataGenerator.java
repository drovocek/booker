package ru.volkovan.booker.general.data;

import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import ru.volkovan.booker.views.users.data.GridUser;
import ru.volkovan.booker.views.users.data.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
public class MockDataGenerator {

    @Bean
    public CommandLineRunner loadMockData(UserRepository userRepository) {

        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (userRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Expense generating demo data");
            ExampleDataGenerator<GridUser> userGenerator = new ExampleDataGenerator<>(GridUser.class, LocalDateTime.now());
            userGenerator.setData(GridUser::setEmail, DataType.EMAIL);
            userGenerator.setData(GridUser::setUsername, DataType.FIRST_NAME);
            userGenerator.setData(GridUser::setLastAccess, DataType.DATETIME_LAST_30_DAYS);
            userGenerator.setData(GridUser::setRegistration, DataType.DATETIME_LAST_1_YEAR);

            List<GridUser> users = userGenerator.create(100, seed).stream()
                    .peek(user -> user.setEnabled(true))
                    .peek(user -> user.setActive(true))
                    .collect(Collectors.toList());

            List<GridUser> usersWithId = userRepository.saveAll(users);
            logger.info("Expense generated demo data");
        };
    }
}

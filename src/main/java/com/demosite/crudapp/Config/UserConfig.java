package com.demosite.crudapp.Config;

import com.demosite.crudapp.model.User;
import com.demosite.crudapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User nun = new User(
                    "nancy",
                    "nancy@gmail.com"
            );
            User brad = new User(
                    "brad",
                    "brad@gmail.com"
            );
            repository.saveAll(
                    List.of(nun,brad)
            );
        };
    }
}

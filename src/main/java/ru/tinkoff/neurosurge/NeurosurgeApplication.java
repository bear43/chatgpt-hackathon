package ru.tinkoff.neurosurge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.tinkoff.neurosurge.dto.ChatGPTConfig;

@SpringBootApplication
@EnableConfigurationProperties(ChatGPTConfig.class)
@EnableMongoRepositories(basePackages = "ru.tinkoff.neurosurge.repo")
public class NeurosurgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeurosurgeApplication.class, args);
    }

}

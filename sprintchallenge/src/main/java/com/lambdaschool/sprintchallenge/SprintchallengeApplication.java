package com.lambdaschool.sprintchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SprintchallengeApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SprintchallengeApplication.class,
            args);
    }

}

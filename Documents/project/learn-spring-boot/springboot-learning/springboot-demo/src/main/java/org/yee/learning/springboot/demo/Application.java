package org.yee.learning.springboot.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * package: org.yee.learning.springboot.demo
 * class: Application
 * describe:
 *
 * @author: brucewong
 * time: 05/02/2018 13:42
 **/
/* 开启组件扫描和自动配置 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 负责启动引导应用程序
        SpringApplication.run(Application.class, args);
    }



    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {

        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            int i = 0;
            String[] beanNames = context.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(i + ". " + beanName);
                i++;
            }
            System.out.println("共计：" + i + " 个 bean ");
        };
    }
}

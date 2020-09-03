package com.pracore.user;

import com.pracore.user.config.TestConfig;
import com.pracore.user.services.LogService;
import com.pracore.user.utils.Test;
import com.pracore.user.utils.Utility;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

@EntityScan
@EnableJpaRepositories
@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration
public class UserApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
        readFromResource1();
        readFromResource2();
        autoWireTests();
    }

    public static void autoWireTests() {
//        @ComponentScan
//        @Bean

//        Stereotypes
//        @Component
//        @Repository
//        @Service
//        @Controller

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

        Utility utility1 = (Utility) applicationContext.getBean("utility");
        utility1.setLogMessage("utility1");
        utility1.log();

        System.out.println(utility1.toString());

        Utility utility2 = (Utility) applicationContext.getBean("utility");

        System.out.println(utility2.toString());

        LogService logService = (LogService) applicationContext.getBean("logService");
        logService.log();

        Test t = (Test) applicationContext.getBean("Test");
    }

    public static void readFromResource1() {
        try {
            Resource resource = new ClassPathResource("test.txt");
            InputStream input = resource.getInputStream();
            File file = resource.getFile();
            System.out.println(file.getName());
            byte[] content = input.readAllBytes();
            System.out.println(new String(content));

        }catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void readFromResource2() {
        try {
            // not recommended
            File file = ResourceUtils.getFile("test.txt");
            System.out.println(file.getName());
        }catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }


    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(30000);
        factory.setReadTimeout(30000);
        return new RestTemplate(factory);
    }
}

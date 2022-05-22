package com.example.bootspring;



import com.example.bootspring.models.Post;
import com.example.bootspring.models.Role;
import com.example.bootspring.models.User;
import com.example.bootspring.models.repository.PostRepository;
import com.example.bootspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Set;

@SpringBootApplication
public class BootSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootSpringApplication.class, args);


        }
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;

    @Bean
    public CommandLineRunner bootstrap(){
        return new CommandLineRunner(){
            @Override
            public void run(String... args) throws Exception {


                Role role_admin = new Role("ROLE_ADMIN");
                Role role_user= new Role(("ROLE_USER"));
                User admin = saveUser("edi",
                        "Zh",
                        "123",
                        role_admin);
                User user = saveUser("omko", "av", "456", role_user);

            }
            private User saveUser(String firstname, String lastname, String username_password, Role role_user) throws IOException {
                User user = new User().setFirstName(firstname)
                        .setLastName(lastname)
                        .setUsername(username_password)
                        .setPassword(username_password)
                        .setRoles(Set.of(role_user));

                userService.saveUser(user);
                return user;

            }


            private void savePost(String title, String content, String price,File img01) throws IOException {
                try (FileInputStream fileInputStream = new FileInputStream(img01)) {
                    Post post = new Post()
                            .setTitle(title)
                            .setContent(content)
                            .setPhoto(fileInputStream.readAllBytes())
                            .setPrice(price);
                    postRepository.save(post);
                }
            }
    };

    }
    @Bean("base64encoder")
    public Base64EncoderToString base64() {
        return bytes -> Base64.getEncoder().encodeToString(bytes);
    }
}

interface Base64EncoderToString{
    String encode(byte[] bytes);
}
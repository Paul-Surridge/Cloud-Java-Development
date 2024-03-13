package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        log.info("Guess the number game");

        /*
            Summary:

                1. Create container i.e. a context using AnnotationConfigApplicationContext(GameConfig.class),
                2. AnnotationConfigApplicationContext is passed the configuration class GameConfig which describes which beans that shall be instantiated and held within the container.
                3. ConfigurableApplicationContext is an interface (adhering to the good practice of programming to the interface).
                4. Once the game is ended the context is closed.
        */

        //Manual creation/closing of the container are not needed, given that Spring Boot creates them automatically.
            //ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);
            //context.close();

        //Manual creation/closing of the container is implemented within:
        SpringApplication.run(Main.class, args);
    }
}
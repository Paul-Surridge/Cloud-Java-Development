package academy.learnprogramming.console;

import academy.learnprogramming.config.GameConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Guess the number game");

        /*
            Summary:

                1. Create container i.e. a context using AnnotationConfigApplicationContext(GameConfig.class),
                2. AnnotationConfigApplicationContext is passed the configuration class GameConfig which describes which beans that shall be instantiated and held within the container.
                3. ConfigurableApplicationContext is an interface (adhering to the good practice of programming to the interface).
                4. Once the game is ended the context is closed.
        */

        //Creates a container with all the beans as defined within beans.xml
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);

        // Close the container to ensure no memory leaks
        context.close();
    }
}
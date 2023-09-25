package academy.learnprogramming.console;

import academy.learnprogramming.config.AppConfig;
import academy.learnprogramming.Game;
import academy.learnprogramming.MessageGenerator;
import academy.learnprogramming.NumberGenerator;
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

                1. Create container i.e. a context using AnnotationConfigApplicationContext(AppConfig.class),
                2. AnnotationConfigApplicationContext is passed the configuration class AppConfig which describes which beans that shall be instantiated and held within the container.
                3. ConfigurableApplicationContext is an interface (adhering to the good practice of programming to the interface).
                4. Beans 'NumberGeneratorImpl' and 'GameImpl' are pulled out of the container and assigned to the interfaces 'NumberGenerator' and 'Game'.
                5: Beans can be pulled out of the spring container using many different ways as illustrated by the getBean() overloaded methods.
        */

        //Creates a container with all the beans as defined within beans.xml
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Extracts the bean 'NumberGeneratorImpl' from the container using the bean id 'numberGenerator' (as defined in the AppConfig Class)
        // and assign to the interface 'numberGenerator'.
        NumberGenerator numberGenerator = context.getBean("numberGenerator",NumberGenerator.class);

        // Call method next() to get the next random number
        int number = numberGenerator.next();

        // log generated number
        log.info("number = {}", number);



        // Extracts the bean 'MessageGeneratorImpl' from the container using the bean id 'messageGenerator' (as defined in the AppConfig Class)
        // and assign to the interface 'numberGenerator'.
        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);

        // Call both methods in the messageGenerator
        log.info(messageGenerator.getMainMessage());
        log.info(messageGenerator.getResultMessage());


        // Extracts the bean 'GameImpl' from the container using 'Game.class' and assign to the interface 'game'.
        Game game = context.getBean(Game.class);

        game.reset();



        // Close the container to ensure no memory leaks
        context.close();
    }
}
package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Random;

public class MessageGeneratorImpl implements MessageGenerator{

    // == constants ==
    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    // == fields ==
    @Autowired
    private Game game;              //This will assign GameImpl given that GameImpl is the only class that implements this interface in the container

    //== public methods ==
    @PostConstruct
    public void checkGameNotNull() {
        if(game == null)    log.info("MessageGeneratorImpl: Fail: game is null");
        else                log.info("MessageGeneratorImpl: Pass: game not null");
    }

    @Override
    public String getMainMessage() {
        return "Number is between " + game.getSmallest() + " and " + game.getBiggest() + ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon())                                            return "You guessed it!, The number was " + game.getNumber();
        else if(game.isGameLost())                                      return "You lost. The number was " + game.getNumber();
        else if(!game.isValidRange())                                   return "Invalid number range!";
        else if(game.getRemainingGuesses() == game.getGuessCount())     return "What is the first guess?";
        else
        {
            String direction = "Lower";

            if(game.getGuess() < game.getNumber())
                direction = "Higher";

            return direction + "! You have " + game.getRemainingGuesses() + " guesses left";
        }
    }
}

package academy.learnprogramming;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Component
public class GameImpl implements Game {

    // == fields ==
    @Getter(AccessLevel.NONE)                       //Overrides the class level @Getter, whereby setting the getter method associated with numberGenerator to .NONE i.e. not accessible external to the class.
    private final NumberGenerator numberGenerator;
    private final int guessCount;

    private int number, smallest, biggest, remainingGuesses;
    private boolean validNumberRange = true;

    @Setter
    private int guess;

    //== constructors ==
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    // == init ==
    //init methods normally contain the code that would naturally be contained within constructors.
    //Therefore, init methods should naturally be placed after the field declarations where the constructor would be located.
    @PostConstruct
    @Override
    public void reset() {
        smallest = numberGenerator.getMinNumber();
        guess = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.debug("the number is {}", number);

        log.debug("the guess is {}", guess);
        log.debug("the smallest is {}", smallest);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("in Game preDestroy");
    }

    // == public methods ==
    @Override
    public void check() {
        checkValidNumberRange();

        if(validNumberRange) {
            if (guess > number)
                biggest = guess - 1;

            if (guess < number)
                smallest = guess + 1;
        }

        remainingGuesses--;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <=0;
    }

    // == private methods ==
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
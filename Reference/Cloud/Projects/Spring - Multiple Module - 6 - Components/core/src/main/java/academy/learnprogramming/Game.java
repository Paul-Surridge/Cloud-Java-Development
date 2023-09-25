package academy.learnprogramming;

public interface Game {

    int getNumber();
    int getGuess();
    void setGuess(int guess);
    int getSmallest();
    int getBiggest();
    int getRemainingGuesses();
    int getGuessCount();
    void reset();
    void check();
    boolean isValidRange();
    boolean isGameWon();
    boolean isGameLost();
}

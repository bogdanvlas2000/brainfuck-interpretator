package brainfuck.exceptions;

public class WrongInputCharacterException extends RuntimeException {
    private char ch;

    public WrongInputCharacterException(char ch) {
        super("Wrong input character: " + ch);
    }
}

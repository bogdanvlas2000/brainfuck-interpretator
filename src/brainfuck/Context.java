package brainfuck;


import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Простой интерпретатор языка Brainfuck
 *
 * @author Bogdan
 */

public class Context {
    private String inputLine;
    private StringBuffer outputLine;
    private byte[] memory;

    private ActionFactory actionFactory;

    private int characterIndex;
    private int memoryIndex;
    private int cyclesIndex;


    //default constructor

    public Context() {
        this.memory = new byte[30_000];
        this.actionFactory = new ActionFactory();
        this.inputLine = "";
        this.outputLine = new StringBuffer();
    }

    public Context(String inputLine) {
        this();
        this.inputLine = inputLine;
    }

    public String interpret() {
        while (characterIndex < inputLine.length()) {
            char ch = inputLine.charAt(characterIndex);
            Consumer<Context> action = actionFactory.getAction(ch);
            action.accept(this);
            characterIndex++;
        }
        return outputLine.toString();
    }

    // service methods

    // >
    public void nextCell() {
        if (memoryIndex < memory.length - 1) {
            memoryIndex++;
        }
    }

    // <
    public void prevCell() {
        if (memoryIndex > 0) {
            memoryIndex--;
        }
    }

    // +
    public void increase() {
        memory[memoryIndex] = (byte) (memory[memoryIndex] + 1);
    }

    // -
    public void reduce() {
        memory[memoryIndex] = (byte) (memory[memoryIndex] - 1);
    }

    // .
    public void read() {
        this.outputLine.append(memory[memoryIndex]);
    }

    // ,
    public void write() {
        Scanner sc = new Scanner(System.in);
        memory[memoryIndex] = sc.nextByte();
    }

    // [
    public void processCycleBegin() {
        if (memory[memoryIndex] == 0) {
            characterIndex++;
            while (cyclesIndex > 0 || inputLine.charAt(characterIndex) != ']') {
                if (inputLine.charAt(characterIndex) == '[')
                    cyclesIndex++;
                else if (inputLine.charAt(characterIndex) == ']')
                    cyclesIndex--;
                characterIndex++;
            }
        }
    }

    // ]
    public void processCycleEnd() {
        if (memory[memoryIndex] != 0) {
            characterIndex--;
            while (cyclesIndex > 0 || inputLine.charAt(characterIndex) != '[') {
                if (inputLine.charAt(characterIndex) == ']')
                    cyclesIndex++;
                else if (inputLine.charAt(characterIndex) == '[')
                    cyclesIndex--;
                characterIndex--;
            }
            characterIndex--;
        }
    }
}

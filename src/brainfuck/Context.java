package brainfuck;

import brainfuck.actions.Action;

import java.util.Scanner;

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

    public String getOutputLine() {
        return outputLine.toString();
    }


    public void interpret() {
        while (characterIndex < inputLine.length()) {
            char ch = inputLine.charAt(characterIndex);
            Action action = actionFactory.getAction(ch);
            action.execute(this);
            characterIndex++;
        }
    }

    // service methods

    public boolean hasNextCell() {
        return memoryIndex < memory.length - 1;
    }

    public boolean hasPrevCell() {
        return memoryIndex > 0;
    }

    // >
    public void nextCell() {
        memoryIndex++;
    }

    // <
    public void prevCell() {
        memoryIndex--;
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

package brainfuck;

import brainfuck.states.StartState;
import brainfuck.states.State;
import brainfuck.states.factory.StateFactory;

import java.util.Scanner;

public class Context {
    private String commandLine;
    private StringBuffer outputLine;
    private int characterIndex;
    private byte[] memory;
    private int memoryIndex;
    private StateFactory stateFactory;
    private State currentState;
    private int cyclesCounter;


    //default constructor

    public Context() {
        this.memory = new byte[30_000];
        this.stateFactory = new StateFactory();
        this.currentState = new StartState();
        this.commandLine = "";
        this.outputLine = new StringBuffer();
    }

    // getters and setters
    public String getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }


    public String getOutputLine() {
        return outputLine.toString();
    }

    // service methods

    public void interpret() {
        while (characterIndex < commandLine.length()) {
            char ch = commandLine.charAt(characterIndex);
            currentState = stateFactory.getState(ch);
//            реализация на лямбда-выражениях
//            currentState = stateFactory.getStateWithLambdas(ch);
            currentState.execute(this);
            characterIndex++;
        }
    }

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
            while (cyclesCounter > 0 || commandLine.charAt(characterIndex) != ']') {
                if (commandLine.charAt(characterIndex) == '[')
                    cyclesCounter++;
                else if (commandLine.charAt(characterIndex) == ']')
                    cyclesCounter--;
                characterIndex++;
            }
        }
    }

    public void processCycleEnd() {
        if (memory[memoryIndex] != 0) {
            characterIndex--;
            while (cyclesCounter > 0 || commandLine.charAt(characterIndex) != '[') {
                if (commandLine.charAt(characterIndex) == ']')
                    cyclesCounter++;
                else if (commandLine.charAt(characterIndex) == '[')
                    cyclesCounter--;
                characterIndex--;
            }
            characterIndex--;
        }
    }
}

package brainfuck.states;

import brainfuck.Context;

public class StartState implements State {

    public void execute(Context context) {
        System.out.println("Brainfuck started!");
    }
}

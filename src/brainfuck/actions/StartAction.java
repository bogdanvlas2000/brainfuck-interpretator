package brainfuck.actions;

import brainfuck.Context;

public class StartAction implements Action {

    public void execute(Context context) {
        System.out.println("Brainfuck started!");
    }
}

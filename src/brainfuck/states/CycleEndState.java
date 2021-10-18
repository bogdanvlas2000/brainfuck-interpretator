package brainfuck.states;

import brainfuck.Context;

public class CycleEndState implements State {

    @Override
    public void execute(Context context) {
        context.processCycleEnd();
    }
}

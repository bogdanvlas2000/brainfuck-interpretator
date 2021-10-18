package brainfuck.states;

import brainfuck.Context;

public class ReduceState implements State {

    @Override
    public void execute(Context context) {
        context.reduce();
    }
}

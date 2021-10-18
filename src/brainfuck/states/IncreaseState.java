package brainfuck.states;

import brainfuck.Context;

public class IncreaseState implements State {

    @Override
    public void execute(Context context) {
        context.increase();
    }
}

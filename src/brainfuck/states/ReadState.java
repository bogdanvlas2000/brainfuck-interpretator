package brainfuck.states;

import brainfuck.Context;

public class ReadState implements State {

    @Override
    public void execute(Context context) {
        context.read();
    }
}

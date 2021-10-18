package brainfuck.states;

import brainfuck.Context;

public class WriteState implements State {


    @Override
    public void execute(Context context) {
        context.write();
    }
}

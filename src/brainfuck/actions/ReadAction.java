package brainfuck.actions;

import brainfuck.Context;

public class ReadAction implements Action {

    @Override
    public void execute(Context context) {
        context.read();
    }
}

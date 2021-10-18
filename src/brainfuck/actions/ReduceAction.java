package brainfuck.actions;

import brainfuck.Context;

public class ReduceAction implements Action {

    @Override
    public void execute(Context context) {
        context.reduce();
    }
}

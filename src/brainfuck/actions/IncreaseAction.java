package brainfuck.actions;

import brainfuck.Context;

public class IncreaseAction implements Action {

    @Override
    public void execute(Context context) {
        context.increase();
    }
}

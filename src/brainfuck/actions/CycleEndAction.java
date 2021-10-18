package brainfuck.actions;

import brainfuck.Context;

public class CycleEndAction implements Action {

    @Override
    public void execute(Context context) {
        context.processCycleEnd();
    }
}

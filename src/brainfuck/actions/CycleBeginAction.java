package brainfuck.actions;

import brainfuck.Context;

public class CycleBeginAction implements Action {
    @Override
    public void execute(Context context) {
        context.processCycleBegin();
    }
}

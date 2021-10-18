package brainfuck.states;

import brainfuck.Context;

public class CycleBeginState implements State {
    @Override
    public void execute(Context context) {
        context.processCycleBegin();
    }
}

package brainfuck.states;

import brainfuck.Context;

public class PrevCellState implements State {

    @Override
    public void execute(Context context) {
        if (context.hasPrevCell()) {
            context.prevCell();
        }
    }
}

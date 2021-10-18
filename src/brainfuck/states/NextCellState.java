package brainfuck.states;

import brainfuck.Context;

public class NextCellState implements State {
    @Override
    public void execute(Context context) {
        if (context.hasNextCell()) {
            context.nextCell();
        }
    }
}

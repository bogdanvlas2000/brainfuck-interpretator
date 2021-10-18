package brainfuck.actions;

import brainfuck.Context;

public class PrevCellAction implements Action {

    @Override
    public void execute(Context context) {
        if (context.hasPrevCell()) {
            context.prevCell();
        }
    }
}

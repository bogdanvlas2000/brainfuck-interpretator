package brainfuck.actions;

import brainfuck.Context;

public class NextCellAction implements Action {
    @Override
    public void execute(Context context) {
        if (context.hasNextCell()) {
            context.nextCell();
        }
    }
}

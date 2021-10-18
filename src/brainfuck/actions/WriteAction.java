package brainfuck.actions;

import brainfuck.Context;

public class WriteAction implements Action {


    @Override
    public void execute(Context context) {
        context.write();
    }
}

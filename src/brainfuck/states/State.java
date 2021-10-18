package brainfuck.states;

import brainfuck.Context;

public interface State {
    void execute(Context context);
}

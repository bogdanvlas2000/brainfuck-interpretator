package brainfuck.states.factory;

import brainfuck.Context;
import brainfuck.exceptions.WrongInputCharacterException;
import brainfuck.states.*;

public class StateFactory {
    //можно реализовать интерфейс в классах
    public State getState(char ch) {
        State state = switch (ch) {
            case '>' -> new NextCellState();
            case '<' -> new PrevCellState();
            case '+' -> new IncreaseState();
            case '-' -> new ReduceState();
            case '.' -> new ReadState();
            case ',' -> new WriteState();
            case '[' -> new CycleBeginState();
            case ']' -> new CycleEndState();
            default -> throw new WrongInputCharacterException(ch);
        };
        return state;
    }

    //можно реализовать на лямбда-выражениях, в этом случае ненужные классы можно удалить
    public State getStateWithLambdas(char ch) {
        State state = switch (ch) {
            case '>' -> ctx -> {
                if (ctx.hasNextCell()) {
                    ctx.nextCell();
                }
            };
            case '<' -> ctx -> {
                if (ctx.hasPrevCell()) {
                    ctx.prevCell();
                }
            };
            case '+' -> ctx -> ctx.increase();
            case '-' -> ctx -> ctx.reduce();
            case '.' -> ctx -> ctx.read();
            case ',' -> ctx -> ctx.write();
            case '[' -> ctx -> ctx.processCycleBegin();
            case ']' -> ctx -> ctx.processCycleEnd();
            default -> throw new WrongInputCharacterException(ch);
        };
        return state;
    }
}

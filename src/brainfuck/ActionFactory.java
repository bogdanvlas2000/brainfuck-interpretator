package brainfuck;

import brainfuck.Context;
import brainfuck.exceptions.WrongInputCharacterException;
import brainfuck.actions.*;

import java.util.function.Consumer;


/**
 * Фабрика, порождающая объекты Action в ответ на входящий символ
 *
 * @author Bogdan
 */
public class ActionFactory {
    //СПОСОБ 1
    //экземпляры классов, реализующих интерфейс
    public Action getAction(char ch) {
        Action action = switch (ch) {
            case '>' -> new NextCellAction();
            case '<' -> new PrevCellAction();
            case '+' -> new IncreaseAction();
            case '-' -> new ReduceAction();
            case '.' -> new ReadAction();
            case ',' -> new WriteAction();
            case '[' -> new CycleBeginAction();
            case ']' -> new CycleEndAction();
            default -> throw new WrongInputCharacterException(ch);
        };
        return action;
    }

    //СПОСОБ 2
    //реализация на лямбда-выражениях
    //в этом случае можно удалить всю иерархию Action
    public Consumer<Context> getActionWithLambdas(char ch) {
        Consumer<Context> action = switch (ch) {
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
        return action;
    }
}

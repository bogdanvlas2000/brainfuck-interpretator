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
}

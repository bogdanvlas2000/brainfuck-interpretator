package brainfuck;

import brainfuck.exceptions.WrongInputCharacterException;

import java.util.function.Consumer;


/**
 * Фабрика, порождающая объекты Action в ответ на входящий символ
 *
 * @author Bogdan
 */
public class ActionFactory {
    //СПОСОБ 2
    //реализация на лямбда-выражениях
    //в этом случае можно удалить всю иерархию Action
    public Consumer<Context> getAction(char ch) {
        Consumer<Context> action = switch (ch) {
            case '>' -> ctx -> ctx.nextCell();
            case '<' -> ctx -> ctx.prevCell();
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

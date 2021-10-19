package brainfuck;

public class BrainfuckTest {
    private Context context;

    // summing test
    public boolean testSum() {
        // 4 + 2
        String commandLine = "++++>++[<<+>-]<.";
        context = new Context(commandLine);
        String resultLine = context.interpret();
        return resultLine.equals("6");
    }


    //multiplication test
    public boolean testMul() {
        // 4 * 2
        String commandLine = "++++>++<[->[->+>+<<]>[-<+>]<<]>>>.";
        context = new Context(commandLine);
        String resultLine = context.interpret();
        return resultLine.equals("8");
    }

    public static void main(String[] args) {
        BrainfuckTest brainfuckTest = new BrainfuckTest();
        System.out.println(brainfuckTest.testSum());
        System.out.println(brainfuckTest.testMul());
    }
}

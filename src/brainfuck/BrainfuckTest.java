package brainfuck;

public class BrainfuckTest {
    private Context context;

    public BrainfuckTest(Context context) {
        this.context = context;
    }

    // summing test
    public boolean testSum() {
        // 4 + 2
        String commandLine = "++++>++[<<+>-]<.";
        context.setCommandLine(commandLine);
        context.interpret();
        String resultLine = context.getOutputLine();
        return resultLine.equals("6");
    }

    public boolean testMul() {
        // 4 * 2
        String commandLine = "++++>++<[->[->+>+<<]>[-<+>]<<]>>>.";
        context.setCommandLine(commandLine);
        context.interpret();
        String resultLine = context.getOutputLine();
        return resultLine.equals("8");
    }

    public static void main(String[] args) {
        Context context = new Context();
        BrainfuckTest brainfuckTest = new BrainfuckTest(context);
        System.out.println(brainfuckTest.testSum());
        System.out.println(brainfuckTest.testMul());
    }
}

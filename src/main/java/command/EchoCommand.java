package command;

public class EchoCommand implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            return "-ERR wrong number of arguments for 'echo' command\r\n";
        }
        return "$" + args[1].length() + "\\r\\n" + args[1] + "\\r\\n";
    }
}

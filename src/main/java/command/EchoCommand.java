package command;

import java.util.List;

public class EchoCommand implements Command {
    @Override
    public String execute(List<String> args) {
        System.out.println("Inside EchoCommand");
        if (args.size() != 2) {
            return "-ERR wrong number of arguments for 'echo' command\r\n";
        }
        return "$" + args.get(1).length() + "\\r\\n" + args.get(1) + "\\r\\n";
    }
}

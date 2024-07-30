package command;

import java.util.List;

public class EchoCommand implements Command {
    @Override
    public String execute(List<String> args) {
        System.out.println("Inside EchoCommand");
        if (args.size() != 1) {
            return "-ERR wrong number of arguments for 'echo' command\r\n";
        }
        return "$" + args.get(0).length() + "\\r\\n" + args.get(0) + "\\r\\n";
    }
}

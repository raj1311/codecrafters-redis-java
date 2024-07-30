package command;

import java.util.List;

public class PingCommand implements Command {
    @Override
    public String execute(List<String> args) {
        return "+PONG\r\n";    }
}

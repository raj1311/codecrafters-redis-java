package command;

import java.util.HashMap;
import java.util.Map;

public class RedisCommandParser {

    private static final Map<String, command.Command> commandMap = new HashMap<>();

    static {
        commandMap.put("PING", new PingCommand());
        commandMap.put("ECHO", new EchoCommand());
        commandMap.put("SET", new SetCommand());
        commandMap.put("GET", new GetCommand());
        // ... add more commands here
    }

    public static String parseAndExecute(String commandLine) {
        System.out.println(commandLine);
        String[] parts = commandLine.split(" ");
        String commandName = parts[0].toUpperCase();

        Command command = commandMap.get(commandName);
        if (command != null) {
            return command.execute(parts);
        } else {
            return "-ERR unknown command '" + commandName + "'\r\n";
        }
    }
}

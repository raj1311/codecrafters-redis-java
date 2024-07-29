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

        String[] parts = commandLine.split("\r\n");

        // 1. Extract Number of Arguments
        int numArgs = Integer.parseInt(parts[0].substring(1)); // Remove "*"

        String commandName ="";
        int counter = 1;
        // 2. Extract Command Name
        for(int i = 1; i <= numArgs; i++) {
            int strLen = Integer.parseInt(parts[counter].substring(1));
            counter++;
            String data = parts[counter].substring(0, strLen);
            counter++;
            if(i==1){
                commandName = data;
            }
        }

        Command command = commandMap.get(commandName);
        if (command != null) {
            return command.execute(parts);
        } else {
            return "-ERR unknown command '" + commandName + "'\r\n";
        }
    }
}

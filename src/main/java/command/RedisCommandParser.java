package command;

import model.CommandData;

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

    public static String parseAndExecute(CommandData commandLine) {
        System.out.println("Inside parseAndExecute");
        Command command = commandMap.get(commandLine.getCommand());
        if (command != null) {
            return command.execute(commandLine.getCommandArgs());
        } else {
            return "-ERR unknown command '" + commandLine.getCommand() + "'\r\n";
        }
    }

    public static DataType findDataType(String line) {
        if (line.startsWith("*")) {
            return DataType.ASTERISK;
        } else if (line.startsWith("$")) {
            return DataType.STR_SIZE;
        } else if (RedisCommandParser.commandMap.containsKey(line)) {
            return DataType.COMMAND;
        }
        return DataType.STRING;
    }

    public static void findDataTypeAndSetInfo(String line, CommandData commandData) {
        DataType dataType = findDataType(line);

        if (dataType.equals(DataType.STRING)) {
            commandData.getCommandArgs().add(line);

        } else if (dataType.equals(DataType.ASTERISK)) {
            commandData.setTotalArgs(Integer.parseInt(line.substring(1)));

        } else if (dataType.equals(DataType.COMMAND)) {
            commandData.setCommand(line);
        }
    }
}

package command;

import org.junit.Test;


public class RedisCommandParserTest {

    @Test
    public void parseAndExecute() {
        String commandStr = "*3\r\n$3\r\nSET\r\n$5\r\nmykey\r\n$7\r\nmyvalue\r\n";
        String resp = RedisCommandParser.parseAndExecute(commandStr);
        System.out.println(resp);

    }
}
package command;

import repo.RedisStringRepoImpl;

import java.util.List;

public class SetCommand implements Command {

    RedisStringRepoImpl repo = RedisStringRepoImpl.getInstance();

    @Override
    public String execute(List<String> args) {

        if (args.size() != 3) {
            return "Err : Wrong arguments for SET";
        }
        repo.save(args.get(1), args.get(2));
        return "+OK\r\n";
    }
}

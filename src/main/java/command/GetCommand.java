package command;

import repo.RedisStringRepoImpl;

import java.util.List;

public class GetCommand implements Command {

    RedisStringRepoImpl redisRepo = RedisStringRepoImpl.getInstance();
    @Override
    public String execute(List<String> args) {
        String resp = "-1";
        if (args.size() != 2) {
            return "-ERR wrong number of arguments for 'get' command\r\n";
        }
        if(redisRepo.isPresent(args.get(1))){
            String value = redisRepo.findById(args.get(1));
            resp = resp.replace("-1",value.length() + "\r\n" + value );
        }
        return "$" + resp + "\r\n";
    }
}

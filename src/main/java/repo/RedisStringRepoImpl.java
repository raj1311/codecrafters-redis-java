package repo;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class RedisStringRepoImpl implements CrudRepo<String, String> {

    ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    private static final RedisStringRepoImpl redisRepo = getInstance();
    public static RedisStringRepoImpl getInstance(){
        if(redisRepo!=null){
            return redisRepo;
        }
        return new RedisStringRepoImpl();
    }
    @Override
    public String findById(String key) {
        return map.get(key);
    }

    @Override
    public Collection<String> findAll() {
        return map.values();
    }

    @Override
    public void save(String key, String value) {
        map.put(key, value);
    }

    @Override
    public void delete(String key) {
        map.remove(key);
    }

    @Override
    public boolean isPresent(String key) {
        return map.containsKey(key);
    }
}

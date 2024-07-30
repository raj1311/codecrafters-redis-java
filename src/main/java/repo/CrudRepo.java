package repo;

import java.util.Collection;

public interface CrudRepo <T,K>{
    public T findById(K key);
    public Collection<T> findAll();
    public void save(K key,T value);
    public void delete(K key);
    public boolean isPresent(K key);
}

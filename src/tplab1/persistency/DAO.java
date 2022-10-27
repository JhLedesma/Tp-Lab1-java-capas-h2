package tplab1.persistency;

import tplab1.persistency.exception.NonExistentElement;

import java.util.List;

public interface DAO<T, ID> {

    public void save(T object);
    public T get(ID id) throws NonExistentElement;
    public List<T> getAll() throws NonExistentElement;
    public void delete(ID id);
}

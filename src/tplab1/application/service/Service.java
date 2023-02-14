package tplab1.application.service;

import tplab1.application.exception.ApplicationException;
import tplab1.persistency.DAO;
import tplab1.persistency.exception.NonExistentElement;

import java.util.List;

public abstract class Service<T> {

    private DAO<T, Integer> dao;

    public List<T> getContentTable() {
        return dao.getAll();
    }

    public void delete(Integer id) {
        try {
            dao.delete(id);
        } catch (NonExistentElement e) {
            throw new ApplicationException(e.getMessage());
        }
    }
}

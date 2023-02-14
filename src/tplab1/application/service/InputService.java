package tplab1.application.service;

import tplab1.application.exception.ApplicationException;
import tplab1.application.model.Input;
import tplab1.persistency.DAO;
import tplab1.persistency.exception.NonExistentElement;

import java.util.List;

public class InputService {

    private DAO<Input, Integer> dao;

    public InputService(DAO<Input, Integer> dao) {
        this.dao = dao;
    }

    public List<Input> getContentTable() {
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

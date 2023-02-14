package tplab1.application.service;

import tplab1.application.exception.ApplicationException;
import tplab1.application.model.Output;
import tplab1.persistency.DAO;
import tplab1.persistency.exception.NonExistentElement;

import java.util.List;

public class OutputService {

    private DAO<Output, Integer> dao;

    public List<Output> getContentTable() {
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

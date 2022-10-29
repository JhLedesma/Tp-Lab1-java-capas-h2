package tplab1.application;

import tplab1.persistency.DAO;
import tplab1.persistency.exception.NonExistentElement;

import java.util.Collections;
import java.util.List;

public class DptoService {

    private DAO<Dpto, Integer> dao;

    public DptoService(DAO<Dpto, Integer> dao) {
        this.dao = dao;
    }

    public void save(String id, String name, String surname) {
        Dpto dpto = new Dpto(Integer.parseInt(id), name, surname);
        dao.save(dpto);
    }

    public List<Dpto> getContentTable() {
        List<Dpto> content;
        try {
            content = dao.getAll();
        } catch (NonExistentElement e) {
            content = Collections.emptyList();
        }
        return content;
    }

    public void delete(Integer id) {
        dao.delete(id);
    }
}

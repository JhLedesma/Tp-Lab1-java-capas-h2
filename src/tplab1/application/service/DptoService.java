package tplab1.application.service;

import tplab1.application.exception.ApplicationException;
import tplab1.application.model.Dpto;
import tplab1.persistency.DAO;
import tplab1.persistency.exception.NonExistentElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return dao.getAll();
    }

    public void delete(Integer id) {
        try {
            dao.delete(id);
        } catch (NonExistentElement e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    public String[] getAvailablesIds() {
        List<String> dtosIds = getContentTable().stream().map(d -> d.getId().toString()).collect(Collectors.toList());
        List<String> availableIds = getDptosNumbers().stream().filter(item -> !dtosIds.contains(item)).collect(Collectors.toList());
        String[] items = new String[availableIds.size()];
        return availableIds.toArray(items);
    }

    private ArrayList<String> getDptosNumbers() {
        ArrayList<String> items = new ArrayList();
        for (int i = 0; i < 21; i++) {
            int item = i+1;
            items.add(String.valueOf(item));
        }
        return items;
    }
}

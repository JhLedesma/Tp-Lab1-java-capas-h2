package tplab1.application.service;

import tplab1.application.exception.ApplicationException;
import tplab1.application.model.Dpto;
import tplab1.application.model.Input;
import tplab1.persistency.DAO;
import tplab1.persistency.exception.NonExistentElement;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InputService {

    private DAO<Input, Integer> dao;
    private DAO<Dpto, Integer> dptoDao;

    public InputService(DAO<Input, Integer> dao, DAO<Dpto, Integer> dptoDao) {
        this.dao = dao;
        this.dptoDao = dptoDao;
    }

    public void save(String id, String amount, String description, Date date, String dptoId) {
        LocalDateTime inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Integer inputId = id != null ? Integer.parseInt(id) : null;
        dao.save(new Input(inputId, Double.valueOf(amount), description, inputDate, Integer.parseInt(dptoId)));
    }

    public String[] getDptosIds() {
        List<String> ids = dptoDao.getAll().stream().map(dpto -> dpto.getId().toString()).collect(Collectors.toList());
        String[] items = new String[ids.size()];
        return ids.toArray(items);
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

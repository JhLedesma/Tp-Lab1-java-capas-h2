package tplab1.application.service;

import tplab1.application.exception.ApplicationException;
import tplab1.application.model.Output;
import tplab1.persistency.DAO;
import tplab1.persistency.exception.NonExistentElement;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class OutputService {

    private DAO<Output, Integer> dao;

    public OutputService(DAO<Output, Integer> dao) {
        this.dao = dao;
    }

    public void save(String id, String amount, String description, Date date) {
        LocalDateTime outputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Integer outputId = id != null ? Integer.parseInt(id) : null;
        dao.save(new Output(outputId, Double.valueOf(amount), description, outputDate));
    }

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

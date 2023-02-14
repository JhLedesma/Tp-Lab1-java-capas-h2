package tplab1.persistency;

import tplab1.application.model.Dpto;
import tplab1.application.model.Habitant;
import tplab1.application.model.Input;
import tplab1.application.model.Output;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DbBootstrapping {

    private TableManager tableManager;
    private DAO<Dpto, Integer> dptoDAO;
    private DAO<Output, Integer> outputDAO;

    public DbBootstrapping(TableManager tableManager, DAO<Dpto, Integer> dptoDAO, DAO<Output, Integer> outputDAO) {
        this.tableManager = tableManager;
        this.dptoDAO = dptoDAO;
        this.outputDAO = outputDAO;
    }

    public void exec() {
        tableManager.clearDb();

        Dpto dpto = new Dpto(1, "A", 35);
        Dpto dpto2 = new Dpto(2, "A", 35);
        Dpto dpto5 = new Dpto(5, "A", 35);
        Dpto dpto6 = new Dpto(6, "B", 35);
        Dpto dpto10 = new Dpto(10, "B", 40);
        Dpto dpto12 = new Dpto(12, "C", 40);
        Dpto dpto14 = new Dpto(14, "C", 40);
        Dpto dpto15 = new Dpto(15, "C", 40);
        Dpto dpto17 = new Dpto(17, "D", 45);
        Dpto dpto19 = new Dpto(19, "D", 45);
        Dpto dpto20 = new Dpto(20, "D", 45);

        Habitant habitant = new Habitant(113012, "Jesus", "Ledesma", dpto.getId());
        Habitant habitant2 = new Habitant(221023, "Lucas", "Alario", dpto2.getId());
        Habitant habitant5 = new Habitant(533011, "Pablo", "Barrios", dpto5.getId());
        Habitant habitant6 = new Habitant(632200, "Florencia", "Fernandez", dpto6.getId());
        Habitant habitant10 = new Habitant(103011, "Cristian", "Palacios", dpto10.getId());
        Habitant habitant12 = new Habitant(123012, "Lucia", "Reyes", dpto12.getId());
        Habitant habitant14 = new Habitant(143012, "Carlos", "Rosas", dpto14.getId());
        Habitant habitant15 = new Habitant(153022, "Norma", "Sarmiento", dpto15.getId());
        Habitant habitant17 = new Habitant(177012, "Martin", "Lopez", dpto17.getId());
        Habitant habitant19 = new Habitant(193999, "Laura", "Alvarez", dpto19.getId());
        Habitant habitant20 = new Habitant(203733, "Marcela", "Gonzalez", dpto20.getId());

        dpto.setHabitant(habitant);
        dpto2.setHabitant(habitant2);
        dpto5.setHabitant(habitant5);
        dpto6.setHabitant(habitant6);
        dpto10.setHabitant(habitant10);
        dpto12.setHabitant(habitant12);
        dpto14.setHabitant(habitant14);
        dpto15.setHabitant(habitant15);
        dpto17.setHabitant(habitant17);
        dpto19.setHabitant(habitant19);
        dpto20.setHabitant(habitant20);

        addInputs(dpto, 25000.0, 20, "Alquiler");
        addInputs(dpto10, 35000.0, 10, "Alquiler");
        addInputs(dpto15, 45000.0, 5, "Alquiler");
        addInputs(dpto, 2000.0, 20, "Luz");
        addInputs(dpto10, 3000.0, 10, "Luz");
        addInputs(dpto15, 4000.0, 5, "Luz");

        addOutputs(70000.0, 5, "Encargado");
        addOutputs(30000.0, 5, "Luz General");

        dptoDAO.save(dpto);
        dptoDAO.save(dpto2);
        dptoDAO.save(dpto5);
        dptoDAO.save(dpto6);
        dptoDAO.save(dpto10);
        dptoDAO.save(dpto12);
        dptoDAO.save(dpto14);
        dptoDAO.save(dpto15);
        dptoDAO.save(dpto17);
        dptoDAO.save(dpto19);
        dptoDAO.save(dpto20);
    }

    private void addInputs(Dpto dpto, double amount, Integer maxDay, String description) {
        List<Input> inputs = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
                .stream()
                .map(month -> new Input(amount, description, LocalDateTime.of(2022, month, new Random().nextInt(maxDay) + 1, 10, 12), dpto.getId()))
                .collect(Collectors.toList());
        dpto.getInputs().addAll(inputs);
    }

    private void addOutputs(double amount, Integer maxDay, String description) {
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
                .stream()
                .map(month -> new Output(amount, description, LocalDateTime.of(2022, month, new Random().nextInt(maxDay) + 1, 10, 12)))
                .forEach(output -> outputDAO.save(output));
    }
}

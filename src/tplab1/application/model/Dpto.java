package tplab1.application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dpto {

    private Integer id;
    private Habitant habitant;
    private List<Input> inputs;


    public Dpto(Integer id) {
        this.id = id;
        this.habitant = null;
        this.inputs = new ArrayList();
    }

    public Dpto(Integer id, Habitant habitant) {
        this.id = id;
        this.habitant = habitant;
        this.inputs = new ArrayList();
    }

    public Dpto(Integer id, Habitant habitant, List<Input> inputs) {
        this.id = id;
        this.habitant = habitant;
        this.inputs = inputs;
    }

    public Dpto(Habitant habitant, List<Input> inputs) {
        this.habitant = habitant;
        this.inputs = inputs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Habitant getHabitant() {
        return habitant;
    }

    public void setHabitant(Habitant habitant) {
        this.habitant = habitant;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dpto dpto = (Dpto) o;
        return Objects.equals(id, dpto.id) && Objects.equals(habitant, dpto.habitant) && Objects.equals(inputs, dpto.inputs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, habitant, inputs);
    }

    @Override
    public String toString() {
        return "Dpto{" +
                "id=" + id +
                ", habitant=" + habitant +
                ", inputs=" + inputs +
                '}';
    }
}

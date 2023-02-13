package tplab1.application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dpto {

    private Integer id;
    private String floor;
    private Integer size;
    private Habitant habitant;
    private List<Input> inputs;


    public Dpto(Integer id, String floor, Integer size) {
        this.id = id;
        this.floor = floor;
        this.size = size;
        this.habitant = null;
        this.inputs = new ArrayList();
    }

    public Dpto(Integer id, String floor, Integer size, Habitant habitant) {
        this.id = id;
        this.floor = floor;
        this.size = size;
        this.habitant = habitant;
        this.inputs = new ArrayList();
    }

    public Dpto(Integer id, String floor, Integer size, Habitant habitant, List<Input> inputs) {
        this.id = id;
        this.floor = floor;
        this.size = size;
        this.habitant = habitant;
        this.inputs = inputs;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
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
        return Objects.equals(id, dpto.id) && Objects.equals(floor, dpto.floor) && Objects.equals(size, dpto.size) && Objects.equals(habitant, dpto.habitant) && Objects.equals(inputs, dpto.inputs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, floor, size, habitant, inputs);
    }

    @Override
    public String toString() {
        return "Dpto{" +
                "id=" + id +
                ", floor='" + floor + '\'' +
                ", size=" + size +
                ", habitant=" + habitant +
                ", inputs=" + inputs +
                '}';
    }
}

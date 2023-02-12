package tplab1.application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dpto {

    private Integer id;
    private String name;
    private String surname;
    private List<Input> inputs;

    public Dpto(Integer id, String name, String surname, List<Input> inputs) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.inputs = inputs;
    }

    public Dpto(String name, String surname, List<Input> inputs) {
        this.name = name;
        this.surname = surname;
        this.inputs = inputs;
    }

    public Dpto(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.inputs = new ArrayList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        return Objects.equals(id, dpto.id) && Objects.equals(name, dpto.name) && Objects.equals(surname, dpto.surname) && Objects.equals(inputs, dpto.inputs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, inputs);
    }

    @Override
    public String toString() {
        return "Dpto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", inputs=" + inputs +
                '}';
    }
}

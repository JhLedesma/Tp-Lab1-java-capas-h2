package tplab1.application.model;

import java.util.Objects;

public class Habitant {

    private Integer dni;
    private String name;
    private String surname;
    private Integer dptoId;

    public Habitant(Integer dni, String name, String surname, Integer dptoId) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.dptoId = dptoId;
    }

    public Integer getDptoId() {
        return dptoId;
    }

    public void setDptoId(Integer dptoId) {
        this.dptoId = dptoId;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitant habitant = (Habitant) o;
        return Objects.equals(dni, habitant.dni) && Objects.equals(name, habitant.name) && Objects.equals(surname, habitant.surname) && Objects.equals(dptoId, habitant.dptoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, name, surname, dptoId);
    }

    @Override
    public String toString() {
        return "Renter{" +
                "dni=" + dni +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dptoId=" + dptoId +
                '}';
    }
}

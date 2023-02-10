package tplab1.application;

import java.util.Objects;

public class Input {

    private Integer id;
    private Double amount;
    private String description;
    private Integer dptoId;

    public Input(Double amount, String description, Integer dptoId) {
        this.amount = amount;
        this.description = description;
        this.dptoId = dptoId;
    }

    public Input(Integer id, Double amount, String description, Integer dptoId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.dptoId = dptoId;
    }

    public Integer getDptoId() {
        return dptoId;
    }

    public void setDptoId(Integer dptoId) {
        this.dptoId = dptoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Input input = (Input) o;
        return Objects.equals(id, input.id) && Objects.equals(amount, input.amount) && Objects.equals(description, input.description) && Objects.equals(dptoId, input.dptoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, dptoId);
    }

    @Override
    public String toString() {
        return "Input{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", dptoId='" + dptoId + '\'' +
                '}';
    }
}

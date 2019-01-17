package drools.model;

import java.util.List;
import javax.persistence.*;


@Entity
public class Medication {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id" , unique = true, nullable = false)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "medication_type")
    private String medicationType;

    @ManyToMany
    private List<Ingredient> ingredient;


    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", type='" + medicationType + '\'' +
                ", ingredient=" + ingredient +
                '}';
    }

    public Medication() {
    }

    public Medication(String text, String type, List<Ingredient> ingredient) {

        this.text = text;
        this.medicationType = type;
        this.ingredient = ingredient;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return medicationType;
    }

    public void setType(String type) {
        this.medicationType = type;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }
}

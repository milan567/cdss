package drools.model;

import java.util.ArrayList;
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

    @ManyToMany
    private List<Ingredient> ingredients;

    @Column(name= "medicationType")
    private MedicationType medicationType;

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", type='" + medicationType + '\'' +
                ", ingredient=" + ingredients +
                '}';
    }

    public Medication() {
    }

    public Medication(String text, MedicationType type, List<Ingredient> ingredient) {

        this.text = text;
        this.medicationType = type;
        this.ingredients = ingredient;
    }

    public Medication(String text, MedicationType type) {

        this.text = text;
        this.medicationType = type;
        this.ingredients = new ArrayList<>();
    }

    public MedicationType getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(MedicationType medicationType) {
        this.medicationType = medicationType;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredient) {
        this.ingredients = ingredient;
    }
}

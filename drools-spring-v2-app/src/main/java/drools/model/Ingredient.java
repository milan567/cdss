package drools.model;
import javax.persistence.*;


@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id" , unique = true, nullable = false)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
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

    public Ingredient() {

    }

    public Ingredient(String text) {

        this.text = text;
    }
}

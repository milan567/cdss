package drools.model;
import javax.persistence.*;
import java.util.Objects;


@Entity
public class Symptom {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id" , unique = true, nullable = false)
    private Integer id;

    @Column(name = "text")
    private String text;

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Symptom() {
    }

    public Symptom(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symptom symptom = (Symptom) o;
        return Objects.equals(id, symptom.id) &&
                Objects.equals(text, symptom.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

}

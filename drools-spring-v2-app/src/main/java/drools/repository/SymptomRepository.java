package drools.repository;

import drools.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SymptomRepository extends JpaRepository<Symptom,Integer> {

    @Query("SELECT w FROM Symptom w WHERE w.text = :text")
    Symptom findByText(@Param("text") String text);
}

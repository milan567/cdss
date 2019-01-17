package drools.repository;


import drools.model.OrdinaryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdinaryUserRepository extends JpaRepository<OrdinaryUser,Integer> {
}

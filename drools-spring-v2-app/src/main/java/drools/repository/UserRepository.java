package drools.repository;

import drools.model.Authority;
import drools.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT w FROM User w WHERE w.username = :username")
    User findByUsername(@Param("username") String username);


    List<User> findByUserAuthority(Authority doctor);
}

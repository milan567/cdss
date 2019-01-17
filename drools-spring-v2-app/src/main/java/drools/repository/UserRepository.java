package drools.repository;

import drools.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT w FROM User w WHERE w.username = :username")
    User findByUsername(@Param("username") String username);


}

package anderk222.hateoas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import anderk222.hateoas.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.name like '%'|| ?1 ||'%'")
    List<Role> search(String name); 
    
}
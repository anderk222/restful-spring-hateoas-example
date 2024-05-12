package anderk222.hateoas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String name;


}

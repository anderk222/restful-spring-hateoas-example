package anderk222.hateoas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anderk222.hateoas.entity.Role;
import anderk222.hateoas.repository.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    RoleRepository repository;


    public Role save(Role role){

        return repository.save(role);
        
    }

    public Role findById(long id){

        return repository.findById(id)
        .orElseThrow();

    }

    public List<Role> search(String name){

        return repository.search(name);

    }

    public List<Role> findAll(){

        return repository.findAll();
    }

    public void deleteById(long id){

        repository.deleteById(id);

    }

}

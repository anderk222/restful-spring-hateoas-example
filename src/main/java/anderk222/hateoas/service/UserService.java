package anderk222.hateoas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import anderk222.hateoas.entity.User;
import anderk222.hateoas.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository repository;

    public UserService(UserRepository repository){

        this.repository = repository;

    }

    public User save(User user){

        return repository.save(user);
        
    }

    public User findById(long id){

        return repository.findById(id)
        .orElseThrow();

    }

    public List<User> search(String name){

        return repository.search(name);

    }

    public List<User> findAll(){

        return repository.findAll();
    }

    public void deleteById(long id){

        repository.deleteById(id);

    }

}

package anderk222.hateoas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anderk222.hateoas.entity.User;
import anderk222.hateoas.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Ejemplo usando EntityModel

@RequestMapping("user")
@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service){

        this.service= service;
    }

    @GetMapping
    public List<EntityModel<User>> findAll() {
        return service.findAll().stream()
                .map(user -> EntityModel.of(user,
                        linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).findAll()).withRel("users")))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public EntityModel<User> findById(@PathVariable("id") long id) {
        return EntityModel.of(service.findById(id))
                .add(linkTo(methodOn(UserController.class)
                .findById(id)).withSelfRel())
                .add(linkTo(methodOn(UserController.class)
                .findAll()).withRel("users"));
    }

    @GetMapping("search")
    public CollectionModel<EntityModel<User>> search(@RequestParam(name = "name") String name) {

        List<EntityModel<User>> data = service.search(name)
        .stream()
        .map(user -> EntityModel.of(user,
                linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).findAll()).withRel("users")))
        .toList();

        return CollectionModel.of(data)
        .add(linkTo(methodOn(UserController.class).search(name)).withSelfRel());

    }

    @PostMapping
    public EntityModel<User> save(@RequestBody User req) {

        User user = service.save(req);

        return EntityModel.of(service.save(user))
                .add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel())
                .add(linkTo(methodOn(UserController.class).findAll()).withRel("users"));
    };


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") long id) {

        service.deleteById(id);

    }

}

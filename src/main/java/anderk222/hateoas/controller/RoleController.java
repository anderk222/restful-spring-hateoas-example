package anderk222.hateoas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import anderk222.hateoas.assembler.RoleModelAssembler;
import anderk222.hateoas.entity.Role;
import anderk222.hateoas.service.RoleService;

// Ejemplo usando assambler

@RequestMapping("role")
@RestController
public class RoleController {

    @Autowired
    RoleService service;

    @Autowired
    RoleModelAssembler assembler;

    @GetMapping
    public List<EntityModel<Role>> findAll() {

        return service.findAll()
                .stream()
                .map(assembler::toModel)
                .toList();
    }

    @GetMapping("{id}")
    public EntityModel<Role> findById(@PathVariable("id") long id) {
        return assembler.toModel(service.findById(id));
    }

    @GetMapping("search")
    public CollectionModel<EntityModel<Role>> search(@RequestParam(name = "name") String name) {

        List<EntityModel<Role>> data = service.search(name)
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(data)
                .add(linkTo(methodOn(RoleController.class).search(name)).withSelfRel());

    }

    @PostMapping
    public EntityModel<Role> save(@RequestBody Role req) {
        
        return  assembler.toModel(service.save(req));

    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") long id) {

        service.deleteById(id);

    }
}
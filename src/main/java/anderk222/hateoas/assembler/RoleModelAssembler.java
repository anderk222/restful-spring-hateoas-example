package anderk222.hateoas.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import anderk222.hateoas.controller.RoleController;
import anderk222.hateoas.entity.Role;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class RoleModelAssembler implements RepresentationModelAssembler<Role,
EntityModel<Role>> {

    @Override
    public EntityModel<Role> toModel(Role entity) {
        
        return EntityModel.of(entity)
                .add(linkTo(methodOn(RoleController.class).findById(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(RoleController.class).findAll()).withRel("roles"));
    }
    
}

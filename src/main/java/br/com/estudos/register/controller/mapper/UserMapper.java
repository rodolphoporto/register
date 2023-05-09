package br.com.estudos.register.controller.mapper;


import br.com.estudos.register.controller.request.UserRequest;
import br.com.estudos.register.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(UserRequest userRequest);


}

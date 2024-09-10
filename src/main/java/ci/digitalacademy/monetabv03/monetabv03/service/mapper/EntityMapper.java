package ci.digitalacademy.monetabv03.monetabv03.service.mapper;

public interface EntityMapper <D , E>{

    D ToDto(E entity);
    E DtoToEntity(D dto);
}

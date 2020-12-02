package ftn.ktsnvt.culturalofferings.helper;

public interface MapperInterface<T,U> {

    T toEntity(U dto);

    U toDto(T entity);
}

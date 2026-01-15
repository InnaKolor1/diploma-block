package java.ru.skypro.homework.mapper;


public @interface Mapper {
    String componentModel();

    Class<?>[] uses();

}
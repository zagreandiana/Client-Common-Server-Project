package ro.ubb.common.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionMessages {

    ID_SHOULD_BE_NUMERIC("Id should be numeric and greater than 0."),
    ENTITY_SHOULD_BE_NON_NULL("Entity should be non-null."),
    ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST("Entity with given id does not exist."),
    ENTITY_WITH_GIVEN_ID_ALREADY_EXISTS("Entity with given id already exists."),
    DB_CONNECTION_ERROR("DB connection error.");

    public final String message;
}

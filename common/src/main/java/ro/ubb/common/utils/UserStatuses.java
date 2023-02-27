package ro.ubb.common.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatuses {

    ACTIVE,
    INACTIVE;

    public static UserStatuses of(String role) {
        try {
            return UserStatuses.valueOf(role.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return UserStatuses.ACTIVE;
        }
    }
}

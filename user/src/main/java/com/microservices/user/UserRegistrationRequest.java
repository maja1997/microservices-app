package com.microservices.user;

public record UserRegistrationRequest(
        String firstName,
        String lastName,
        String username,
        String email
) {
}

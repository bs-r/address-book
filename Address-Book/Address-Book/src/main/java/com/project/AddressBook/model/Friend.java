package com.project.AddressBook.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.regex.Pattern;
@Slf4j
@Data
@NoArgsConstructor
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String phoneNumber;

    public Friend(String name, String phoneNumber) {
        try {
            validateName(name);
            validatePhoneNumber(phoneNumber);
        } catch (IllegalArgumentException e) {
            log.error("Failed to create friend: " + e.getMessage(), e);
            throw e;
        }
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    private void validatePhoneNumber(String phoneNumber) {
        if( phoneNumber==null || !Pattern.matches("\\d{8,}",phoneNumber)){
            log.error("Phone number must be at least 8 digits");
            throw new IllegalArgumentException("Phone number must be at least 8 digits");
        }
    }

    private void validateName(String name) {
        if ( name == null || name.length ()< 3){
            log.error("Name must be greater than 2 characters");
            throw new IllegalArgumentException("Name must be greater than 2 characters");
        }

    }

    @Override
    public String toString() {
        return getName() + ": " + getPhoneNumber();
    }
}


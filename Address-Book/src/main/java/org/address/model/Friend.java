package org.address.model;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String phoneNumber;

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Friend(String name, String phoneNumber) {
        validateName(name);
        validatePhoneNumber(phoneNumber);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    private void validatePhoneNumber(String phoneNumber) {
        if( phoneNumber==null || !Pattern.matches("\\d{8,}",phoneNumber)){
            throw new IllegalArgumentException("Phone number must be at least 8 digits");
        }
    }

    private void validateName(String name) {
        if ( name == null || name.length ()< 3){
            throw new IllegalArgumentException("Name must be greater than 2 characters");
        }

    }

    @Override
    public String toString() {
        return getName() + ':' + getPhoneNumber();
    }
}

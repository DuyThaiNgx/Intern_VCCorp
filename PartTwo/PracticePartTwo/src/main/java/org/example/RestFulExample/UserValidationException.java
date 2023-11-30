package org.example.RestFulExample;

import org.omg.CORBA.UserException;

public class UserValidationException extends UserException {
    public UserValidationException(String message) {
        super(message);
    }
}
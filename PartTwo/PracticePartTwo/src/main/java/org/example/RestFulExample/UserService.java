package org.example.RestFulExample;

import org.omg.CORBA.UserException;

import java.util.Collection;

public interface UserService {
    public void addUser (User user) throws UserValidationException;
    public Collection<User> getUsers ();
    public User getUser (String id);
    public User editUser (User user)
            throws UserException;
    public void deleteUser (String id);
    public boolean userExist (String id);
}



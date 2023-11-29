package RestFulExample;

import org.omg.CORBA.UserException;

import java.util.Collection;

public interface UserService {
    public void addUser(Users user);

    public Collection<Users> getUsers();

    public Users getUser(String id);

    public Users editUser(Users user)
            throws UserException;

    public void deleteUser(String id);

    public boolean userExist(String id);
}



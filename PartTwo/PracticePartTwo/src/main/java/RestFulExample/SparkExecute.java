package RestFulExample;

import com.google.gson.Gson;
import org.omg.CORBA.UserException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static spark.Spark.*;

public class SparkExecute {
    public static void main(String[] args) {
        port(8989);

        List<Users> listUser = new ArrayList<>();
        UserService userService = new UserService() {
            @Override
            public void addUser(Users user) {
                listUser.add(user);
            }

            @Override
            public Collection<Users> getUsers() {
                return null;
            }

            @Override
            public Users getUser(String id) {
                for(Users user: listUser){
                    if(user.getId().equals(id)){
                        listUser.add(user);
                    }
                }
                return null;
            }

            @Override
            public Users editUser(Users user) throws UserException {
                return null;
            }

            @Override
            public void deleteUser(String id) {

            }

            @Override
            public boolean userExist(String id) {
                return false;
            }
        };
        post("/users", (request, response) -> {
            response.type("application/json");
            Users user = new Gson().fromJson(request.body(), Users.class);
            userService.addUser(user);
            return new Gson()
                    .toJson(new StandardResponse(StatusResponse.SUCCESS));
        });
//        get("/users/:id", (request, response) -> {
//            response.type("application/json");
//            return new Gson().toJson(
//                    new StandardResponse(StatusResponse.SUCCESS,new Gson()
//                            .toJsonTree(userService.getUser(request.params(":id")))));
//        });


//        get("/users", (request, response) -> {
//            //...
//        });
//        get("/users/:id", (request, response) -> {
//            //...
//        });
//        put("/users/:id", (request, response) -> {
//            //...
//        });
//        delete("/users/:id", (request, response) -> {
//            //...
//        });
//        options("/users/:id", (request, response) -> {
//            //...
//        });
    }
}

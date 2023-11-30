package org.example.RestFulExample;

import com.google.gson.Gson;
import org.omg.CORBA.UserException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static spark.Spark.*;

public class SparkExecute {
    public static void main(String[] args) {
        port(8989);

        List<User> listUser = new ArrayList<>();
        UserService userService = new UserService() {
            @Override
            public void addUser(User user) throws UserValidationException {
                if (user == null || user.getId() == null || user.getId().isEmpty()) {
                    throw new UserValidationException("Invalid user data. ID cannot be null or empty.");
                }

                // Kiểm tra xem người dùng đã tồn tại chưa
                if (userExist(user.getId())) {
                    throw new UserValidationException("User with ID " + user.getId() + " already exists.");
                }

                // Thêm người dùng vào danh sách
                listUser.add(user);
            }

            @Override
            public Collection<User> getUsers() {
                return null;
            }

            @Override
            public User getUser(String id) {
                return null;
            }

            @Override
            public User editUser(User user) throws UserException {
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
        post("/hello", (request, response) -> {
            response.type("application/json");
            try {
                User user = new Gson().fromJson(request.body(), User.class);
                userService.addUser(user);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
            } catch (UserValidationException e) {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }
        });
        get("/users", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS,new Gson()
                            .toJsonTree(userService.getUsers())));
        });
//        get("/User/:id", (request, response) -> {
//            response.type("application/json");
//            return new Gson().toJson(
//                    new StandardResponse(StatusResponse.SUCCESS,new Gson()
//                            .toJsonTree(Userervice.getUser(request.params(":id")))));
//        });


//        get("/User", (request, response) -> {
//            //...
//        });
//        get("/User/:id", (request, response) -> {
//            //...
//        });
//        put("/User/:id", (request, response) -> {
//            //...
//        });
//        delete("/User/:id", (request, response) -> {
//            //...
//        });
//        options("/User/:id", (request, response) -> {
//            //...
//        });
    }
}

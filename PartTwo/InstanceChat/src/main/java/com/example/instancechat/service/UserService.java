package com.example.instancechat.service;

import com.example.instancechat.entity.User;
import com.example.instancechat.repository.UserRepository;
import com.example.instancechat.request.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public Optional<User> getUser(AccountRequest accountRequest) {
//        Optional<User> userOptional = userRepository.findByUsername(accountRequest.getUsername());
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (new BCryptPasswordEncoder().matches(accountRequest.getPassword(), user.getPassword())) {
//                return Optional.of(user);
//            }
//        }
//        return Optional.empty();
//    }
//
//    public void addUser(AccountRequest registerRequest){
//        String hashedPassword = new BCryptPasswordEncoder().encode(registerRequest.getPassword());
//        userRepository.addUser(new User(registerRequest.getUsername(), hashedPassword));
//    }
//
//}
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(AccountRequest request){
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if(userOptional.isPresent()){
            User user = userOptional.get();
//            return Optional.of(user);
//            System.out.println(getMD5(request.getPassword()) + "\n" + user.getPassword());
            if(getMD5(request.getPassword()).equals(user.getPassword())){
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public void addUser(AccountRequest request) {
//        String hashedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        String hashedPassword = getMD5(request.getPassword());

        userRepository.addUser(new User(request.getUsername(), hashedPassword));
    }

    public String getMD5(String input){
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lấy ra danh sách bạn bè dựa vào username
     * @param username
     * @return
     */
    public List<String> getFriends(String username){
        return userRepository.getFriends(username);
    }

    /**
     * Kiểm tra xem friendName có phải bạn của username không
     * @param username
     * @param friendName
     * @return
     */
    public boolean isFriends(String username, String friendName){
        return userRepository.getFriends(username).stream().anyMatch(friend -> friend.equals(friendName));
    }

    public static void main(String[] args) {
        UserService service = new UserService();
        //Tạo ra các
        System.out.println(service.getMD5("user1"));
        System.out.println(service.getMD5("23092002"));
    }
}
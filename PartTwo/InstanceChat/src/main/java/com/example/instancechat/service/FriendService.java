package com.example.instancechat.service;

import com.example.instancechat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    @Autowired
    private UserRepository userRepository;

    public List<String> getFriends(String username) {
        return userRepository.getFriends(username);
    }

    public boolean checkFriends(String username, String friendName) {
        List<String> friends = userRepository.getFriends(username);
        for (String friendCheck : friends) {
            if (friendCheck.equals(friendName)) {
                return true;
            }
        }
        return false;
    }
    public boolean isFriend(String username, String friendname) {
        return userRepository.getFriends(username)
                .stream()
                .anyMatch(friend -> friend.equals(friendname));
    }
}

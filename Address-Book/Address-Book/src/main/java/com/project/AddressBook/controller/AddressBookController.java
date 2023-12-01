package com.project.AddressBook.controller;

import com.project.AddressBook.model.Friend;
import com.project.AddressBook.service.impl.AddressBookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/address-book")
public class AddressBookController {

    @Autowired
    private AddressBookServiceImpl addressBookService;

    @PostMapping("/add-friend")
    public String addFriend(@RequestBody Friend friend) {
        try {
            addressBookService.addFriend(friend);
            return "Friend added successfully";
        } catch (Exception e) {
            log.error("Failed to add friend: " + e.getMessage(), e);
            return "Failed to add friend. Check logs";
        }
    }

    @GetMapping("/display-friends")
    public List<Friend> displayFriends() {
        try {
            return addressBookService.getFriends();
        } catch (Exception e) {
            log.error("Failed to display friends: " + e.getMessage(), e);
            throw e;
        }
    }

    @PostMapping("/find-unique-friends")
    public Set<String> findUniqueFriends(@RequestBody AddressBookServiceImpl addressBookService1) {
        try {
           return addressBookService.findUniqueFriends(addressBookService1);
        } catch (Exception e) {
            log.error("Failed to find unique friends: " + e.getMessage(), e);
            throw e;
        }
    }

    @PostMapping("/save-to-file/{fileName}")
    public String saveToFile(@PathVariable String fileName) {
        try {
            addressBookService.saveToFile(fileName);
            return "Address-Book saved to file successfully";
        } catch (Exception e) {
            log.error("Failed to save file: " + e.getMessage(), e);
            return "Failed to save file, check logs";
        }
    }

    @PostMapping("/load-from-file/{fileName}")
    public String loadFromFile(@PathVariable String fileName) {
        try {
            addressBookService.loadFromFile(fileName);
            return "Address-Book loaded successfully";
        } catch (Exception e) {
            log.error("Failed to load file: " + e.getMessage(), e);
            return "Failed to load file, check logs";
        }
    }



}

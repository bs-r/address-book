package com.project.AddressBook.service.impl;
import com.project.AddressBook.model.Friend;
import com.project.AddressBook.service.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Data
@Service
public class AddressBookServiceImpl implements AddressBookService, Persistence {
    private List<Friend> friends;
    private PrintStream printStream;

    public AddressBookServiceImpl() {
        this.friends = new ArrayList<>();
        this.printStream = System.out;
    }

    public AddressBookServiceImpl(PrintStream printStream) {
        this.friends = new ArrayList<>();
        this.printStream = printStream;
    }

    @Override
    public void addFriend(Friend friend) {
        try {
            friends.add(friend);
        } catch (Exception e) {
            log.error("Failed to add a friend: " + e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public void displayFriends() {
        try {
            friends.stream()
                    .sorted(Comparator.comparing(Friend::getName))
                    .forEach(friend -> printStream.println(friend));
        } catch (Exception e) {
            log.error("Failed to display friends: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Set<String> findUniqueFriends(AddressBookService otherAddressBook) {
        try {
            Set<String> uniqueFriends = friends.stream()
                    .map(Friend::getName)
                    .collect(Collectors.toSet());

            uniqueFriends.removeAll(
                    ((AddressBookServiceImpl) otherAddressBook).friends.stream()
                            .map(Friend::getName)
                            .collect(Collectors.toSet())
            );

            return uniqueFriends;
        } catch (Exception e) {
            log.error("Failed to find unique friends: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            outputStream.writeObject(friends);
        } catch (IOException e){
            log.error("Unable to save file " + e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchceked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            friends = (List<Friend>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e){
            log.error("Unable to load the friend " + e.getMessage(), e);
            e.printStackTrace();
        }
    }
}


package org.address.service.impl;

import org.address.model.Friend;
import org.address.service.AddressBookService;
import org.address.service.Persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AddressBookServiceImpl implements AddressBookService, Persistence {
    private List<Friend> friends;

    public List<Friend> getFriends() {
        return friends;
    }
    public AddressBookServiceImpl() {
        this.friends = new ArrayList<>();
    }

    @Override
    public void addFriend(Friend friend) {
        friends.add(friend);
    }

    @Override
    public void displayFriends() {
        friends.stream()
                .sorted(Comparator.comparing(Friend::getName))
                .forEach(System.out::println);
    }

    @Override
    public Set<String> findUniqueFriends(AddressBookService otherAddressBook) {
        Set<String> uniqueFriends = friends.stream()
                .map(Friend::getName)
                .collect(Collectors.toSet());

        uniqueFriends.removeAll(
                ((AddressBookServiceImpl) otherAddressBook).friends.stream()
                        .map(Friend::getName)
                        .collect(Collectors.toSet())
        );

        return uniqueFriends;
    }

    @Override
    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            outputStream.writeObject(friends);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchceked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            friends = (List<Friend>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}

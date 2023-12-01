package com.project.AddressBook.service;
import com.project.AddressBook.model.Friend;

import java.util.Set;

public interface AddressBookService {
    void addFriend(Friend friend);
    void displayFriends();

    Set<String> findUniqueFriends(AddressBookService otherAddressBook);
}

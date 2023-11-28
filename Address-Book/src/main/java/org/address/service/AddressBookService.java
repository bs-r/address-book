package org.address.service;
import java.util.Set;
import org.address.model.Friend;

public interface AddressBookService {
    void addFriend(Friend friend);
    void displayFriends();

    Set<String> findUniqueFriends(AddressBookService otherAddressBook);
}

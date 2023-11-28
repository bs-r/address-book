package org.address;

import org.address.model.Friend;
import org.address.service.AddressBookService;
import org.address.service.Persistence;
import org.address.service.impl.AddressBookServiceImpl;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Set;

public class AddressBook {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Create instances using the interfaces
        AddressBookService book1 = new AddressBookServiceImpl();
        AddressBookService book2 = new AddressBookServiceImpl();

        //Add friends to address books based on the user input
        System.out.println("Enter friends for the address book1 (Enter 'done' to finish):");
        addFriendsFromUserInput(book1, scanner);
        System.out.println("Enter friends for the address book2 (Enter 'done' to finish):");
        addFriendsFromUserInput(book2, scanner);

        //Display friends in each address book
        System.out.println("Address Book 1:");
        book1.displayFriends();
        System.out.println("Address Book 2:");
        book2.displayFriends();

        //Find unique friends between address books
        Set<String> uniqueFriendsBook1 = book1.findUniqueFriends(book2);
        Set<String> uniqueFriendsBook2 = book2.findUniqueFriends(book1);

        System.out.println("\nUnique Friends in Address Book 1: " + uniqueFriendsBook1);
        System.out.println("Unique Friends in Address Book 2: " + uniqueFriendsBook2);

        //Save and load address book data
        ((Persistence) book1).saveToFile("addressBook1.dat");
        ((Persistence) book2).saveToFile("addressBook2.dat");

        AddressBookService loadedBook1 = new AddressBookServiceImpl();
        ((Persistence) loadedBook1).loadFromFile("addressBook1.dat");

        AddressBookService loadedBook2 = new AddressBookServiceImpl();
        ((Persistence) loadedBook2).loadFromFile("addressBook2.dat");

        System.out.println("\nLoaded Address Book 1: ");
        loadedBook1.displayFriends();

        System.out.println("\nLoaded Address Book 2: ");
        loadedBook2.displayFriends();

        scanner.close();
    }

    private static void addFriendsFromUserInput(AddressBookService addressBook, Scanner scanner) {
        while(true) {
            System.out.println("Enter name (or 'done' to finish): ");
            String name = scanner.nextLine();

            if("done".equalsIgnoreCase(name)) {
                break;
            }
            System.out.println("Enter phone number: ");
            String phoneNumber = scanner.nextLine();

            addressBook.addFriend(new Friend(name, phoneNumber));
        }
    }
}
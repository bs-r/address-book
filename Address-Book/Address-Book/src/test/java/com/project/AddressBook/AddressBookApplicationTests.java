package com.project.AddressBook;

import com.project.AddressBook.service.impl.AddressBookServiceImpl;
import com.project.AddressBook.model.Friend;
import com.project.AddressBook.service.AddressBookService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class AddressBookApplicationTests {

	@Autowired
	private AddressBookServiceImpl addressBookService;

	@Test
	void testAddFriend() {
		addressBookService.addFriend(new Friend("Alice","123456789"));
		assertEquals(1, addressBookService.getFriends().size());
	}

	@Test
	void testFindUniqueFriends() {
		AddressBookService book1 = new AddressBookServiceImpl();
		AddressBookService book2 = new AddressBookServiceImpl();

		book1.addFriend(new Friend("Bob", "1234567890"));
		book1.addFriend(new Friend("Mary", "2345678901"));
		book1.addFriend(new Friend("Jane", "3456789012"));

		book2.addFriend(new Friend("Mary", "2345678901"));
		book2.addFriend(new Friend("John", "4567890123"));
		book2.addFriend(new Friend("Jane", "3456789012"));

		Set<String> uniqueFriendsBook1 = book1.findUniqueFriends(book2);
		Set<String> uniqueFriendsBook2 = book2.findUniqueFriends(book1);

		assertEquals(Set.of("Bob"), uniqueFriendsBook1);
		assertEquals(Set.of("John"), uniqueFriendsBook2);
	}

	@Test
	void testDisplayFriendsInSortedOrder() {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream mockPrintStream = new PrintStream(outputStream);
		AddressBookServiceImpl addressBookService1 = new AddressBookServiceImpl(mockPrintStream);

		Friend friend1 = new Friend("Alice","123456789");
		Friend friend2 = new Friend("Charlie","987654321");
		Friend friend3 = new Friend("Bob","567890123");

		addressBookService1.addFriend(friend1);
		addressBookService1.addFriend(friend2);
		addressBookService1.addFriend(friend3);

		addressBookService1.displayFriends();

		System.setOut(System.out);

		List<String> expectedOutput = Arrays.asList(
				"Alice: 123456789",
				"Bob: 567890123",
				"Charlie: 987654321"
		);

		List<String> actualOutput = Arrays.asList(outputStream.toString().trim().split("\r\n"));
		assertEquals(expectedOutput, actualOutput);
	}
}

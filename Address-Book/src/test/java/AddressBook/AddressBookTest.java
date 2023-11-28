package AddressBook;

import org.address.model.Friend;
import org.address.service.AddressBookService;
import org.address.service.impl.AddressBookServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookTest {

    @Test
    void testAddFriend() {
        AddressBookServiceImpl addressBook = new AddressBookServiceImpl();
        addressBook.addFriend(new Friend("Alice","123456789"));
        assertEquals(1, addressBook.getFriends().size());
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
        AddressBookServiceImpl addressBookServiceImpl = new AddressBookServiceImpl();
        addressBookServiceImpl.addFriend(new Friend("Alice","123456789"));
        addressBookServiceImpl.addFriend(new Friend("Charlie","987654321"));
        addressBookServiceImpl.addFriend(new Friend("Bob","567890123"));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        addressBookServiceImpl.displayFriends();

        System.setOut(System.out);

        String expectedOutput = "Alice:123456789\r\nBob:567890123\r\nCharlie:987654321\r\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}

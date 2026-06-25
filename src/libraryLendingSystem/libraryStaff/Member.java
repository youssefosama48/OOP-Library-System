package libraryLendingSystem.libraryStaff;

import libraryLendingSystem.customExceptions.LibraryException;
import libraryLendingSystem.libraryItems.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class Member {

    // Fields
    private String memberName;
    private String memberId;

    private static final int MAX_BORROWED_ITEMS = 3;

    private List<LibraryItem> borrowedItemsList = new ArrayList<>();

    //Parameterized Constructor
    public Member(String name, String id) throws LibraryException {
            setMemberName(name);
            setMemberId(id);
    }

    //Setters & Getters

    public void setMemberName(String name) throws LibraryException {
           if(name.trim().isEmpty()) {
               throw new LibraryException("Name cannot be empty");
           }
           else {
               this.memberName = name;
           }
    }

    public void setMemberId(String id) throws LibraryException {
            if(id.trim().isEmpty()) {
                throw new LibraryException("MemberID cannot be empty");
            }
            else {
                this.memberId = id;
            }
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberId() {
        return memberId;
    }


    // Class Methods

    public int getBorrowedCount() {
        return borrowedItemsList.size();
    }

    public boolean canBorrowItem() {
        return getBorrowedCount() < MAX_BORROWED_ITEMS;
    }

    /**
     * This method borrow an item , checks first that member can borrow more items or not
     *
     * @param newItem
     */
    public void borrowItem(LibraryItem newItem) throws LibraryException {

            if (!canBorrowItem()) {
                throw new LibraryException("Borrowed items limit is reached.");
            }
            if (borrowedItemsList.contains(newItem)) {
                throw new LibraryException("Item" + " " + newItem.getItemId() + " is already borrowed by you");
            }

            borrowedItemsList.add(newItem);
            newItem.markBorrowed();
            System.out.println("Item " + newItem.getItemId() + " is added successfully");
    }

    /**
     * This method remove item from member list , with validation that check that item is already borrowed from
     * member or not
     *
     * @param item
     */
    public void removeFromList (LibraryItem item) throws LibraryException {

            if (borrowedItemsList.contains(item)) {
                borrowedItemsList.remove(item);
                item.markReturned();
                System.out.println("Item " + item.getItemId() + " is removed successfully from member list");
            } else {
                throw new LibraryException("Item " + item.getItemId() + " is not exist in your list");
            }
    }

}

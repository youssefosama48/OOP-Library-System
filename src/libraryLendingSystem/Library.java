package libraryLendingSystem;

import libraryLendingSystem.customExceptions.LibraryException;
import libraryLendingSystem.libraryItems.LibraryItem;
import libraryLendingSystem.libraryStaff.Member;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Library {

    private int currentlyLoaned, dvds,books,magazines;

    private Map<String, LibraryItem> libraryItems = new HashMap<>();
    private Map<String, Member> libraryMembers = new HashMap<>() ;
    private Set<String> borrowedItemsIds = new HashSet<>();

    public Map<String, LibraryItem> getLibraryItems() {
        return libraryItems;
    }

    //Methods

    /**
     * This method adds item to library
     * @param item
     * @throws LibraryException
     */

    public void addLibraryItem (LibraryItem item) throws LibraryException {
        if (!libraryItems.containsKey(item.getItemId())) {
            libraryItems.put(item.getItemId(), item);
        }
        else {
            throw new LibraryException("Item with id " + item.getItemId() + " is already exists");
        }
    }

    /**
     * This method adds member to library store
     * @param member
     * @throws LibraryException
     */
    public void addMember (Member member) throws LibraryException {
        try {
            if (!libraryMembers.containsKey(member.getMemberId())) {
                libraryMembers.put(member.getMemberId(), member);
            } else {
                throw new LibraryException("Member with ID " + member.getMemberId() + " is already exists");
            }
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * This method for borrowing item from library store
     *
     * @param memberId
     * @param itemId
     * @throws LibraryException
     */
    public void borrowItem(String memberId, String itemId) throws LibraryException {

        Member member = libraryMembers.get(memberId);
        LibraryItem item = libraryItems.get(itemId);

        if (member == null) {
            throw new LibraryException("Member not found");
        }

        if (item == null) {
            throw new LibraryException("Item not found");
        }

        if (!item.isAvailable()) {
            throw new LibraryException("Item is already borrowed");
        }

        if (!member.canBorrowItem()) {
            throw new LibraryException("Member reached borrowing limit");
        }

        member.borrowItem(item);
        item.markBorrowed();
        borrowedItemsIds.add(item.getItemId());
    }

    /**
     * This method for returning item after borrowing
     *
     * @param memberId
     * @param itemId
     * @throws LibraryException
     */
    public void returnItem (String memberId, String itemId) throws LibraryException {
        Member member = libraryMembers.get(memberId);
        LibraryItem item = libraryItems.get(itemId);

        if (member == null) {
            throw new LibraryException("Member not found");
        }

        if (item == null) {
            throw new LibraryException("Item not found");
        }

        if (item.isAvailable()) {
            throw new LibraryException("Item " + " " + item.getItemId() + " is not borrowed yet");
        }

        member.removeFromList(item);
        item.markReturned();
        borrowedItemsIds.remove(item.getItemId());
        System.out.println("Item" + " " + item.getItemId() + " is available now");
    }

    /**
     * This method loops on all library items and list them
     */
    public void listCatalogs () {
        for (Map.Entry <String, LibraryItem> entry : libraryItems.entrySet()) {
            LibraryItem item = entry.getValue();
            item.displayInfo();
        }
    }

    /**
     * Method to print summary report for library
     */
    public void printReport() {

        for (Map.Entry<String, LibraryItem> entry : libraryItems.entrySet()) {
            if (!entry.getValue().isAvailable()) {
                currentlyLoaned++;
            }
            if (entry.getValue().getItemType().contains("Book")) {
                books++;
            }
            if (entry.getValue().getItemType().contains("DVD")) {
                dvds++;
            }
            if (entry.getValue().getItemType().contains("Magazine")) {
                magazines++;
            }
        }
        System.out.println("======= Library Report =======");
        System.out.println("Total library items is " + LibraryItem.getTotalItemsCreated() + "| Currently out items are "
                + currentlyLoaned + "| Borrowed items ids are" + borrowedItemsIds + " | Books count " + books
                + "| Dvds count " + dvds + "| Magazines count " + magazines);
    }

    /**
     * This method searches for item in library by item-id
     *
     * @param id
     * @throws LibraryException
     */
    public void searchItemById (String id) throws LibraryException {
        String upperCaseID = id.toUpperCase();
        for (Map.Entry <String, LibraryItem> entry : libraryItems.entrySet()) {
            if (entry.getKey().equals(upperCaseID)) {
                entry.getValue().displayInfo();
            } else {
                throw new LibraryException("Item with ID " + upperCaseID + "is not found");
            }
        }
    }

    public void searchItemTitle (String title) throws LibraryException {
        for (Map.Entry <String, LibraryItem> entry : libraryItems.entrySet()) {
            if (entry.getValue().getTitle().equals(title)) {
                entry.getValue().displayInfo();
            } else {
                throw new LibraryException("Item with Title " + title + "is not found");
            }
        }
    }
    public void showAvailableItems () {
        for (Map.Entry <String, LibraryItem> entry : libraryItems.entrySet()) {
            if (entry.getValue().isAvailable()) {
                entry.getValue().displayInfo();
                System.out.println("\n");
            }
        }
    }


}

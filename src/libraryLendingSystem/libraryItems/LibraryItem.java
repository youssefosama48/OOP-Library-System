package libraryLendingSystem.libraryItems;

import libraryLendingSystem.customExceptions.LibraryException;

public abstract class LibraryItem {

    private String ItemId;
    private String title;
    private boolean borrowed = false;


    static int totalItemsCreated;
    static int nextNumber = 1;

    public LibraryItem(String title) throws LibraryException {
        setTitle(title);

        this.ItemId = "ITEM-" + nextNumber++;
        this.borrowed = false;
        totalItemsCreated++;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Item title cannot be empty");
        }
        else {
            this.title = title;
        }
    }

    public String getItemId() {
        return ItemId;
    }

    public String getTitle() {
        return title;
    }

    public static int getTotalItemsCreated() {
        return totalItemsCreated;
    }

    // Class methods

    /**
     *
     * @return
     */
    public boolean isAvailable() {
        return !borrowed;
    }

    /**
     *
     */
    public void markBorrowed() {
        borrowed = true;
    }

    public void markReturned() {
        borrowed = false;
    }

    /**
     *
     */
    public void displayInfo() {
        System.out.println(
                getItemId() + " | " +
                        getTitle() + " | " +
                        getItemType() + " | loan: " +
                        getLoanDays() + " days | " +
                        (isAvailable() ? "Available" : "Not Available")
        );
    }

    public abstract int getLoanDays();
    public abstract String getItemType();
}
package libraryLendingSystem.libraryItems;

import libraryLendingSystem.customExceptions.LibraryException;

public class Magazine extends LibraryItem{

    private int issueNumber;

    public Magazine(String title) throws LibraryException {
        super(title);
    }

    @Override
    public int getLoanDays() {
        return 7;
    }

    @Override
    public String getItemType() {
        return "This item is a Magazine";
    }
}

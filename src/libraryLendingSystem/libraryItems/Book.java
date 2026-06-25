package libraryLendingSystem.libraryItems;

import libraryLendingSystem.customExceptions.LibraryException;

public class Book extends LibraryItem{

    private String bookAuthor;
    private int pagesNumber;

    public Book(String title, String author, int pages) throws LibraryException {
        super(title);

        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }

        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be positive");
        }

        this.bookAuthor = author;
        this.pagesNumber = pages;
    }


    @Override
    public int getLoanDays() {
        return 21;
    }

    @Override
    public String getItemType() {
        return "Book";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
    }
}

package libraryLendingSystem;

import libraryLendingSystem.customExceptions.LibraryException;
import libraryLendingSystem.libraryItems.Book;
import libraryLendingSystem.libraryItems.DVD;
import libraryLendingSystem.libraryItems.LibraryItem;
import libraryLendingSystem.libraryItems.Magazine;
import libraryLendingSystem.libraryStaff.Member;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class for Library Lending System
 */

public class Main {

    int selectedOption;

    private static Library library = new Library();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int selectedOption = 0;

        do {
            displayLibraryMenu();
            System.out.print("Choose an option: ");

            try {
                selectedOption = scanner.nextInt();
                selectFromMenu(selectedOption);

            } catch (InputMismatchException e) {
                System.out.println("Please enter a number. Characters are not accepted.");
                scanner.nextLine(); // clear invalid input

            } catch (LibraryException e) {
                System.out.println("Error: " + e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }

        } while (selectedOption != 9);
    }

    private static void displayLibraryMenu() {
        System.out.println("===== Library System ===== ");

        System.out.println("1. Add New Item");
        System.out.println("2. Add Member");
        System.out.println("3. Borrow Item");
        System.out.println("4. Return  Item");
        System.out.println("5. List Item Catalogs");

        System.out.println("=====Bouns Requirements=====");
        System.out.println("6. Print Library Report");
        System.out.println("7. Search by ID");
        System.out.println("8. Show Available Items Only");
        System.out.println("9. Terminate the program");
    }

    /**
     * This method selects the target option from menu
     *
     * @param option
     * @throws LibraryException
     */
    private static void selectFromMenu (int option) throws LibraryException {
        switch (option) {
            case 1: {
                LibraryItem item = chooseFromItems();
                library.addLibraryItem(item);
                break;
            }
            case 2: {
                Member member = addNewMember();
                library.addMember(member);
                System.out.println("Member added successfully");
                break;
            }
            case 3: {
                takeBorrowingData();
                break;
            }
            case 4: {
                returnItem();
                break;
            }
            case 5: {
                System.out.println("Listing Catalogs......");
                library.listCatalogs();
                break;
            }
            case 6: {
                library.printReport();
                break;
            }
            case 7:
                try {
                    getItemById();
                }catch (LibraryException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 8:
                library.showAvailableItems();
                break;
            case 9:
                System.out.println("See you again. !Goodbye");
                break;
            default:
                throw new LibraryException("Invalid Option, Try again");
        }

    }


    /**
     * This method for choosing between library item when adding a new item
     *
     * @returns object from LibraryItem Class
     * @throws LibraryException
     */
    private static LibraryItem chooseFromItems() throws LibraryException {

        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. DVD");


        System.out.println("Choose an item");
        String itemOption = scanner.next();

            switch (itemOption) {

                case "1": {
                    scanner.nextLine();
                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();

                    System.out.println("Enter author: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter number of pages: ");
                    int pages = scanner.nextInt();

                    LibraryItem book = new Book(title, author, pages);

                    System.out.println(
                            "Book added successfully with ID "
                                    + book.getItemId());
                    return book;
                }

                    case "2": {
                    scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();

                    LibraryItem item = new Magazine(title);
                    System.out.println(
                            "Magazine is added successfully with ID "
                                    + item.getItemId());
                    return item;
            }

                case "3": {
                    scanner.nextLine();
                    System.out.print("Enter DVD title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter duration: ");
                    int duration = scanner.nextInt();

                    LibraryItem item = new DVD(title, duration);

                    System.out.println(
                            "DVD is added successfully with ID "
                                    + item.getItemId());

                    return item;
                }

                default:
                    throw new LibraryException("Invalid item type selected.");
            }
    }

    /**
     * Method for asking a new member for his name and id
     *
     * @return object from Member class
     * @throws LibraryException
     */
    private static Member addNewMember () throws LibraryException {

        System.out.print("Enter your name please : ");
        String memberName = scanner.next();

        System.out.print("Enter your ID : ");
        String id = scanner.next();

        return new Member(memberName, id);
    }

    /**
     * Method to ask for item data for borrowing
     * @throws LibraryException
     */
    private static void takeBorrowingData () throws LibraryException {

        System.out.println("Enter your ID, please: ");
        String memberID = scanner.next();

        System.out.println("Enter Item id please: ");
        String itemID = scanner.next();

        library.borrowItem(memberID, itemID);
        System.out.println("Item is successfully borrowed");
    }

    private static void returnItem () throws LibraryException {
        System.out.println("Enter your id please: ");
        String memberID = scanner.next();

        System.out.println("Enter id for the item you need to return ");
        String itemID = scanner.next();

        library.returnItem(memberID, itemID);
        System.out.println("Item is returned successfully");
    }

    private static void getItemById () throws LibraryException {
        System.out.println("Enter Item ID please, ");
        String itemID = scanner.next();

        library.searchItemById(itemID);
    }

}
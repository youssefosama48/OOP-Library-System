# Library Lending System

A console-based Library Lending System developed in Java using Object-Oriented Programming (OOP) principles. The application allows users to manage library items, register members, borrow and return items, search the catalog, and generate library reports.

## Features

### Item Management

* Add new library items
* Support multiple item types:

  * Book
  * Magazine
  * DVD
* Automatically generate unique item IDs
* Display all library items

### Member Management

* Register new members
* Validate member information
* Prevent duplicate member IDs

### Borrowing System

* Borrow library items
* Return borrowed items
* Prevent borrowing unavailable items
* Enforce borrowing limits
* Track currently borrowed items

### Reporting

* Display library statistics
* Show total number of items
* Show currently loaned items
* Display counts by item type
* List borrowed item IDs

### Search Functionality

* Search items using their unique ID
* Display only available items

## Technologies Used

* Java
* Object-Oriented Programming (OOP)
* Java Collections Framework

  * ArrayList
  * HashMap
  * HashSet
* Custom Exception Handling

## Project Structure

```text
libraryLendingSystem
│
├── Main.java
├── Library.java
│
├── customExceptions
│   └── LibraryException.java
│
├── libraryItems
│   ├── LibraryItem.java
│   ├── Book.java
│   ├── Magazine.java
│   └── DVD.java
│
└── libraryStaff
    └── Member.java
```

## OOP Concepts Demonstrated

### Abstraction

`LibraryItem` is implemented as an abstract class that defines common behavior for all library items.

### Inheritance

```text
LibraryItem
├── Book
├── Magazine
└── DVD
```

### Polymorphism

Different item types are handled through `LibraryItem` references while providing their own implementations of:

* `getLoanDays()`
* `getItemType()`

### Encapsulation

Class attributes are private and accessed through getters and setters with validation.

## Borrowing Rules

* A member can borrow a maximum of **3 items**
* An item must exist before it can be borrowed
* An item must be available before borrowing
* An item cannot be borrowed twice
* Only borrowed items can be returned

## Loan Periods

| Item Type | Loan Days |
| --------- | --------- |
| Book      | 21        |
| Magazine  | 7         |
| DVD       | 3         |

## Menu Options

```text
===== Library System =====

1. Add New Item
2. Add Member
3. Borrow Item
4. Return Item
5. List Item Catalogs

===== Bonus Requirements =====

6. Print Library Report
7. Search by ID
8. Show Available Items Only
9. Terminate the Program
```

## Example Item IDs

```text
ITEM-1
ITEM-2
ITEM-3
...
```

## Exception Handling

The system uses a custom checked exception:

```java
public class LibraryException extends Exception {
    public LibraryException(String message) {
        super(message);
    }
}
```

The application handles:

* Invalid menu selections
* Empty titles
* Empty member names
* Empty member IDs
* Invalid page counts
* Borrowing unavailable items
* Borrowing limit violations
* Returning unborrowed items
* Invalid user input

## Future Improvements

* Save data to files
* Search by title
* Search by author
* Member borrowing history
* Due date tracking
* GUI using JavaFX or Swing
* Database integration

## Author

**Youssef Osama**

Java OOP Project – Library Lending System

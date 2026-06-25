package libraryLendingSystem.libraryItems;

import libraryLendingSystem.customExceptions.LibraryException;

public class DVD extends LibraryItem{
    private int runtimeMinutes;

    public DVD(String title, int runMinutes) throws LibraryException {
        super(title);
        if(runMinutes != 0)
        {
            this.runtimeMinutes = runMinutes;
        }
        else
        {
            System.out.println("Runtime minutes cannot be zero");
        }
    }

    @Override
    public int getLoanDays() {
        return 3;
    }

    @Override
    public String getItemType() {
        return "This is a DVD";
    }
}

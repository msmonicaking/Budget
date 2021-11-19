package FileHandler;

public class Year {

    int year;
    TList months[]; // an array of TLists where each element represents all transactions in a month

    public Year(int year) {
        
        if (year < 0) {
            throw new IllegalArgumentException();
        }

        this.year = year;

        months = new TList[13]; 

    }

    
}
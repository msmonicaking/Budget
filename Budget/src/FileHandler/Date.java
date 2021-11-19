package FileHandler;

public class Date implements Comparable<Date> {
    
    public int day;
    public int month;
    public int year;

    // Constructor for class Date
    //  int day
    //  int month
    //  int yearIn
    public Date(int day, int month, int year) {

        // test that all values are greater than 0
        if(day <= 0 || month <= 0 || year <= 0) {
            throw new IllegalArgumentException();
        }
        if(day > 31) {
            // this should probably test month-dependent
            throw new IllegalArgumentException();
        }
        if (month > 12) {
            throw new IllegalArgumentException();
        }
        if (year > 2021) {
            // I want this to test for future dates
            throw new IllegalArgumentException();
        }

        this.day = day;
        this.month = month;
        this.year = year;

    }


    // overrides Comparable interfaces compareTo function
    // returns 0   if dates are equal
    // returns 1   if this date is more recent
    // returns -1  if this date is older
    public int compareTo(Date other) {

        if(this.year == other.year) {

            if(this.month == other.month) {

                if(this.day == other.day) {
                    return 0;
                } else if (this.day > other.day) {
                    return 1;
                } else {
                    return 0;
                }

            } else if (this.month > other.month) {
                return 1;
            } else {
                return -1;
            }

        } else if (this.year > other.year) {
            return 1;
        } else {
            return -1;
        }

    }

}

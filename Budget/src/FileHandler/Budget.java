package FileHandler;

import java.util.LinkedList;

public class Budget {

    private LinkedList<Pair> categories;

    public Budget() {

        this.categories = new LinkedList<Pair>();
    }

    public void addCategory(String name, int dollars) {

        categories.add(new Pair(name, dollars));
    }

    // returns a LinkedList of all the category data 
    public LinkedList<Pair> getCategories() {
        return categories;
    }

    
    // each Pair represents one category
    public class Pair {

        String category;
        int dollars;

        Pair(String name, int dollars) {

            this.category = name;
            this.dollars = dollars;
        }

    }

}
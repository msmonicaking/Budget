// 
// Transaction.java
// Monica King
// November 14, 2021
//

public class Transaction {
    
    String category;
    String name;
    Double price;
    Date purchDate;

    // Constructor for class Transaction
    public Transaction(String category, String name, Double price, Date purchDate) {

        // add test to see if category is valid?
        setCategory(category);
        this.name = name;
        // I assume all amounts of price are good, should we change?
        this.price = price;
        this.purchDate = purchDate;

    }

    // setter for Category
    // does not currently test for validity of category
    public void setCategory(String category) {
        this.category = category;
    }

//-----------------------------------------------------------------------------

    public String getCategory() {
        return category;
    }
    public String getName() {
        return name;
    }
    public Double getPrice() {
        return price;
    }
    public Date getDate() {
        return purchDate;
    }

}

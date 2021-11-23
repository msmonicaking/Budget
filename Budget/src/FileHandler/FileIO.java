package FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONTokener;

/*
 * Editing and loading transaction from JSON file.
 */
public class FileIO {

	public FileIO() {
		// Create LoginCredentials.json if it does not exist.
		if (!checkFileExists()) {
			newFileLoginCredentials();
		}
	}
	
	public boolean checkFileExists() {
		File file = new File("LoginCredentials.json");
		return file.exists();
	}

	/* Create a new JSON file for a user.
	 * Name the file as the username.
	 */
	public void newFile(String username){
		JSONObject user = new JSONObject();

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toString(4));
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Creates a new JSON file for login credentials
	public void newFileLoginCredentials(){
		JSONObject login = new JSONObject();

		try (FileWriter file = new FileWriter("LoginCredentials.json")) {
			file.write(login.toString(4));
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void newUserLogin(String username, String password, String securityQ, String securityA) throws IOException {
		FileReader obj = new FileReader("LoginCredentials.json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject login = new JSONObject(tokener);
		JSONObject details = new JSONObject();

		login.put(username, details);
		details.put("Password", password);
		details.put("Security_q", securityQ);
		details.put("Security_a", securityA);

		try {
			// Create a new user json file.
			File userFile = new File(username + ".json");
			userFile.createNewFile();
			
			FileWriter file = new FileWriter("LoginCredentials.json");
			file.write(login.toString(4));
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			obj.close();
		}
	}

	/* Takes in a username and password
	   Checks whether username and password is correct
	 */
	public boolean checkLoginCredentials(String username, String password) throws IOException {
		FileReader obj = new FileReader("LoginCredentials.json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject loginCredentials = new JSONObject(tokener);

		JSONObject user = (JSONObject) loginCredentials.get(username);

		return user.get("Password") == password;
	}

	/* Checks whether username already exists
	 */
	public boolean checkUsername(String username) throws IOException {
		FileReader obj = new FileReader("LoginCredentials.json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject loginCredentials = new JSONObject(tokener);

		return loginCredentials.has(username);
	}

	public void addYear(Date date, String username) throws IOException {
		FileReader obj = new FileReader(username + ".json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject user = new JSONObject(tokener);
		JSONObject year = new JSONObject();

		user.put(date.year + "", year);

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toString(4));
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean checkYear(Date date, String username) throws IOException {
		FileReader obj = new FileReader(username + ".json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject user = new JSONObject(tokener);

		return user.has("" + date.year);
	}

	public void addMonth(Date date, String username) throws IOException {
		FileReader obj = new FileReader(username + ".json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject user = new JSONObject(tokener);
		JSONObject year = (JSONObject) user.get(("" + date.year));
		JSONObject month = new JSONObject();
		JSONObject transactions = new JSONObject();

		month.put("Budget", 0);
		month.put("Total Expenses", (double) 0);
		month.put("Transactions", transactions);
		year.put(date.month + "", month);

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toString(4));
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean checkMonth(Date date, String username) throws IOException {
		FileReader obj = new FileReader(username + ".json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject user = new JSONObject(tokener);

		return ((JSONObject) user.get(""+ date.year)).has("" + date.month);
	}

	/* load Transaction history from json file.
	 */

	public Year load(String username) {
		return new Year(1999);
	}


	/* Add new Transaction category
	 */
	public void addCategory(String category, Date date, String username) throws Exception {
		FileReader obj = new FileReader(username + ".json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject user = new JSONObject(tokener);

		JSONObject categoryObject = new JSONObject();
		JSONObject transactions = ((JSONObject)((JSONObject)((JSONObject) user.get("" + date.year)).get("" + date.month)).get("Transactions"));
		if(transactions.get(category) == null){
			transactions.put(category, categoryObject);
		}

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toString(4));
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Get budget
	 */
	public String getBudget(Date date, String username) throws IOException {
		FileReader obj = new FileReader(username + ".json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject user = new JSONObject(tokener);
		JSONObject month = ((JSONObject)((JSONObject) user.get("" + date.year)).get("" + date.month));

		return (String) month.get("Budget");
	}

	/* Set budget
	 */
	public void setBudget(String budget, Date date, String username) throws IOException {
		FileReader obj = new FileReader(username + ".json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject user = new JSONObject(tokener);
		JSONObject month = ((JSONObject)((JSONObject) user.get("" + date.year)).get("" + date.month));

		month.put("Budget", budget);

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toString(4));
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Add new Transaction to a category.
	 */
	public void addTransaction(Transaction transaction, String username) throws Exception {

		FileReader obj = new FileReader(username + ".json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject user = new JSONObject(tokener);

		JSONObject month = ((JSONObject)((JSONObject) user.get("" + transaction.getDate().year)).get("" + transaction.getDate().month));
		JSONObject transactionsObj = (JSONObject) month.get("Transactions");
		JSONObject category = (JSONObject) transactionsObj.get(transaction.category);

		if(category != null) {
			if(!category.has(transaction.getName())) {
				JSONArray itemArr = new JSONArray();
				JSONObject item = new JSONObject();
				item.put("Price", transaction.getPrice());
				item.put("DOP", transaction.getDate().day);
				itemArr.put(item);
				category.put(transaction.getName(), itemArr);
			} 
			else {
				JSONArray itemArr = (JSONArray) category.get(transaction.getName());
				JSONObject item = new JSONObject();
				item.put("Price", transaction.getPrice());
				item.put("DOP", transaction.getDate().day);
				itemArr.put(item);
			}
			month.put("Total Expenses", (double) month.get("Total Expenses") + transaction.getPrice());
		}
		else {
			System.out.println("Category not found"); // Prints an error
		}

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toString(4));
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Delete transaction
	 */
	public void delTransaction(Transaction transaction, String username) throws Exception {

		FileReader obj = new FileReader(username + ".json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject user = new JSONObject(tokener);

		// finds month & year needed
		JSONObject month = ((JSONObject)((JSONObject) user.get("" + transaction.getDate().year)).get("" + transaction.getDate().month));
		JSONObject transactionsObj = (JSONObject) month.get("Transactions");
		JSONObject category = (JSONObject) transactionsObj.get(transaction.category);
		JSONArray itemArr = (JSONArray)category.get(transaction.getName());

		if(itemArr != null){
			for(int i = 0; i < itemArr.length(); i++){
				if(((JSONObject)itemArr.get(i)).get("Price").equals(transaction.getPrice())){
					if((long) ((JSONObject)itemArr.get(i)).get("DOP") == (long)transaction.getDate().day){
						itemArr.remove(i);
						month.put("Total Expenses", (double) month.get("Total Expenses") - transaction.getPrice());
					}
				}
			}
		}else{
			System.out.println("Category not found"); // Prints an error
		}

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toString(4));
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	* Returns a 2D array of Transaction information for the given year and month
	*/
	public String[][] getAllTransactions(String username, int yearInt, int monthInt) throws Exception {

		// gets info associated with username
		FileReader obj = new FileReader(username + ".json");
        JSONTokener tokener = new JSONTokener(obj);
		JSONObject user = new JSONObject(tokener);

		JSONObject month = ((JSONObject)((JSONObject) user.get("" + yearInt)).get("" + monthInt));
		JSONObject transactionsObj = (JSONObject) month.get("Transactions");

		
		
		String[][] transactions = new String[10][10];

		return transactions;
	}
}

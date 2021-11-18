package Budgeter.src.FileHandler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * Editing and loading transaction from JSON file.
 */
public class FileIO {

	public FileIO() {
		
	}
	
	/* Create a new JSON file for a user.
	 * Name the file as the username.
	 */
	public void newFile(String username){
		JSONObject user = new JSONObject();
		JSONObject transactions = new JSONObject();

		user.put("Budget", "Not set");
		user.put("Transactions", transactions);

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* load Transaction history from json file.
	 */

	/*
	public Year<TStruct> load(String username) {
		
	}
	*/

	/* Add new Transaction category
	 */
	public void addCategory(String category, String username) throws Exception {
		Object obj = new JSONParser().parse(new FileReader(username + ".json"));
		JSONObject user = (JSONObject) obj;

		JSONObject categoryObject = new JSONObject();
		((JSONObject) user.get("Transactions")).put(category, categoryObject);

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Get budget
	 */
	public String getBudget(String username) throws IOException, ParseException {
		Object obj = new JSONParser().parse(new FileReader(username + ".json"));
		JSONObject user = (JSONObject) obj;

		return (String) user.get("Budget");
	}

	/* Set budget
	 */
	public void setBudget(String budget, String username) throws IOException, ParseException {
		Object obj = new JSONParser().parse(new FileReader(username + ".json"));
		JSONObject user = (JSONObject) obj;

		user.put("Budget", budget);

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* Add new Transaction to a category. 
	 */
	public void addTransaction(Transaction transaction, String username) throws Exception {
		Object obj = new JSONParser().parse(new FileReader(username + ".json"));
		JSONObject user = (JSONObject) obj;

		JSONObject category = (JSONObject)((JSONObject) user.get("Transactions")).get(transaction.category);

		if(category != null){
			JSONObject item = new JSONObject();
			item.put("Price", transaction.getPrice());
			item.put("DOP", transaction.getDate());
			category.put(transaction.getName(), item);
		}else{
			System.out.println("Category not found"); // Prints an error
		}

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* Delete transaction
	 */
	public void delTransaction(Transaction transaction, String username) throws Exception {
		Object obj = new JSONParser().parse(new FileReader(username + ".json"));
		JSONObject user = (JSONObject) obj;

		JSONObject category = (JSONObject)((JSONObject) user.get("Transactions")).get(transaction.category);

		if(category != null && ((JSONObject)category.get(transaction.getName()) != null)){
			category.remove(transaction.getName());
		}else{
			System.out.println("Category not found"); // Prints an error
		}

		try (FileWriter file = new FileWriter(username + ".json")) {
			file.write(user.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

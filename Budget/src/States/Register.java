package States;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Class Register
 *  A state for registering a new username and password
 */
public class Register extends State {
	private LoginPage page;

	public Register(LoginPage page) {
		this.page = page;
	}
	private static final long serialVersionUID = 3109968069917069255L;

	// these are the regex checks for the username and password to check against
	// (currently allows all lowcase and uppcase letters, numbers, and must be
	// length of 5 - 19 characters)
	static String userNameCode = "[a-zA-Z1-9]{5,19}";
	static String passwordCode = "[a-zA-Z1-9]{5,19}";

	// this method is the register check that checks if the username and password
	// are using the allowed characters
	public static boolean Register(String userName, String password) {

		// creating the pattern compilers for the regex string checks
		Pattern userNamePattern = Pattern.compile(userNameCode);
		Pattern passwordPattern = Pattern.compile(passwordCode);

		// this creates the matcher to use the strings that are passed into the method
		Matcher userNameMatch = userNamePattern.matcher(userName);
		Matcher passwordMatch = passwordPattern.matcher(password);

		// this checks if the username fits within the regex boundaries
		if (userNameMatch.find()) {
			System.out.println("Workedusername");
		}

		// if there is a character that is not allowed by regex it will give the user
		// this error.
		else {
			System.out.println("Couldnt create username");
		}

		// this checks if the password fits within the regex boundaries
		if (passwordMatch.find()) {
			System.out.println("Workedpassword");
		}

		// if there is a character that is not allowed by regex it will give the user
		// this error.
		else {
			System.out.println("Couldnt create password");
		}

		return true;
	}

}

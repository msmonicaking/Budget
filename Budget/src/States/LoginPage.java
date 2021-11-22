package States;

public class LoginPage extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7228111606502371323L;

	enum Screens {
		LOGIN,
		REGISTER
	}
	private Login loginScreen;
	private Register registerScreen;
	
	public LoginPage() {
		loginScreen = new Login(this);
		registerScreen = new Register(this);
		// First screen to show.
		switchPanel(loginScreen);
	}
	
	public void switchTo(Screens screen) {
		switch (screen) {
		case LOGIN:
			switchPanel(loginScreen);
		case REGISTER:
			switchPanel(registerScreen);
		}
	}

}

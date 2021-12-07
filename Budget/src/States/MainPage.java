package States;

import States.Screens;

public class MainPage extends State {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7228111606502371323L;

	protected String username;
	private static Menu menu;
	private static Home homeScreen;
	private static Main mainScreen;
	
	public MainPage(String username) {
		this.username = username;
		homeScreen = new Home(this);
		mainScreen = new Main(this);
		menu = new Menu(this);
		// First screen to show.
		switchTo(Screens.HOME);
	}
	
	public void switchTo(Screens screen) {
		switch (screen) {
		case HOME:
			switchPanel(menu, homeScreen);
			break;
		case MAIN:
			switchPanel(menu, mainScreen);
			break;
		default:
			break;
		}
	}

}
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
	protected static CategoryOverlay cw;
	
	public MainPage(String username) throws Exception {
		this.username = username;
		
		homeScreen = new Home(this);
		mainScreen = new Main(this);
		menu = new Menu(this);
		
		cw = new CategoryOverlay(mainScreen);
		cw.setVisible(false);
		
		// First screen to show.
		switchTo(Screens.HOME);
	}
	
	public void switchTo(Screens screen) {
		switch (screen) {
		case HOME:
			cw.setVisible(false);
			switchPanel(menu, homeScreen);
			break;
		case MAIN:
			cw.setVisible(false);
			switchPanel(menu, cw, mainScreen);
			break;
		default:
			break;
		}
	}
	
	public void setVisibleCW(boolean b) {
		cw.setVisible(b);
	}
	

}
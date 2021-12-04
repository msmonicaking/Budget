package States;

public class Home extends State {

	private MainPage page;
	
	public Home(MainPage page) {
		this.page = page;
		init();
	}
	
	public void init() {
		setBounds(50, 0, 1215, 680);
	}
	
}
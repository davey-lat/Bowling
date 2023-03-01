package bowling;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
@SuppressWarnings("serial")
public class ScoreKeeper extends JFrame implements ActionListener
{
	// Declares and initialize the frame size and position for the frame
	private static final int FRAME_Y_ORIGIN = 250;
	private static final int FRAME_X_ORIGIN = 150;
	private static final int FRAME_HEIGHT = 800;
	private static final int FRAME_WIDTH = 800;

	// Declares the number of players buttons
	private JButton OnePlayer;
	private JButton TwoPlayer;
	private JButton ThreePlayer;
	private JButton FourPlayer;

	// Declares the number of games buttons
	private JButton OneGame;
	private JButton TwoGame;
	private JButton ThreeGame;

	//Declares and makes an array of the max number of balls one player can throw
	private int[] p1Score = new int[26];
	private int[] p2Score = new int[26];
	private int[] p3Score = new int[26];
	private int[] p4Score = new int[26];

	// Declares the menu tabs
	private JMenu helpMenu;
	private JMenu settingsMenu;

	// Declares the labels for how many players and games with an image
	private JLabel bowltext;
	private JLabel bowltext1;
	private JLabel bowlimage;

	// Declares and makes the text field that buttons pushed will be shown
	private TextArea textArea1 = new TextArea();
	private TextArea textArea2 = new TextArea();
	private TextArea textArea3 = new TextArea();
	private TextArea textArea4 = new TextArea();
	private TextArea textArea5 = new TextArea();

	// Declares and initialize the variables to keep track of each frame
	private static int frames = 10;
	private static int frameNumber = 0;

	// Declares all the panels used for the different buttons
	private JPanel playerPanel;
	private JPanel gamePanel;
	private JPanel pinsPanel;
	private JPanel scorePanel;
	private JPanel askScore; 

	//Declares and creates the label for each players text field
	private JLabel playerOne = new JLabel("Player 1");
	private JLabel playerTwo = new JLabel("Player 2");
	private JLabel playerThree = new JLabel("Player 3");
	private JLabel playerFour = new JLabel("Player 4");
	public int numPlayers;

	//Declares and creates the button for how many pins knocked over
	private JButton onePin = new JButton("1");
	private JButton twoPin = new JButton("2");
	private JButton threePin = new JButton("3");
	private JButton fourPin = new JButton("4");
	private JButton fivePin = new JButton("5");
	private JButton sixPin = new JButton("6");
	private JButton sevenPin = new JButton("7");
	private JButton eightPin = new JButton("8");
	private JButton ninePin = new JButton("9");
	private JButton gutter = new JButton("0");
	private JButton spare = new JButton("/");
	private JButton strike = new JButton("X");

	// Creates the container
	Container bowlScore  = getContentPane();

	// Sets the actual program to be visible
	public static void main(String[] arg)  
	{
		ScoreKeeper frame = new ScoreKeeper();
		frame.setVisible(true);
	}
	public ScoreKeeper ()  {
		// Sets the size and allows it to be resized
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable (true);

		//Sets the title
		setTitle("Bowling Score Keeper");

		//Close the program when the red X is pressed
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Sets the location of the frame
		setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);

		// Creates the buttons for the number of players
		OnePlayer = new JButton("One");
		OnePlayer.addActionListener(this);
		TwoPlayer = new JButton("Two");
		TwoPlayer.addActionListener(this);
		ThreePlayer = new JButton("Three");
		ThreePlayer.addActionListener(this);
		FourPlayer = new JButton("Four");
		FourPlayer.addActionListener(this);

		//Applies action listens to each pin value to allow buttons to be pressed
		onePin.addActionListener(this);
		twoPin.addActionListener(this);
		threePin.addActionListener(this);
		fourPin.addActionListener(this);
		fivePin.addActionListener(this);
		sixPin.addActionListener(this);
		sevenPin.addActionListener(this);
		eightPin.addActionListener(this);
		ninePin.addActionListener(this);
		gutter.addActionListener(this);
		spare.addActionListener(this);
		strike.addActionListener(this);

		//Creates the label for the number of players
		bowltext = new JLabel("How many players?");

		//Sets the layout of the container
		bowlScore.setLayout(new FlowLayout());

		//Creates the menu bar and adds the tabs
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		createSettingsMenu();
		createHelpMenu();
		menu.add(settingsMenu);
		menu.add(helpMenu);

		//Creates the image
		bowlimage = new JLabel(new ImageIcon("Bowling.jpg"));

		//Creates all the panels used
		playerPanel = new JPanel();
		playerPanel.setPreferredSize(new Dimension(700, 700));
		playerPanel.setLayout(new FlowLayout());
		gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(700, 700));
		gamePanel.setLayout(new FlowLayout());
		pinsPanel = new JPanel();
		pinsPanel.setPreferredSize(new Dimension(200, 200));
		pinsPanel.setLayout(new GridLayout(3,4));
		scorePanel = new JPanel();
		scorePanel.setLayout(new FlowLayout());
		scorePanel.setPreferredSize(new Dimension(400, 800));
		scorePanel.add(textArea5);

		//Adds the playerPanel to the containers and sets it visible
		bowlScore.add(playerPanel);
		playerPanel.add(OnePlayer);
		playerPanel.add(TwoPlayer);
		playerPanel.add(ThreePlayer);
		playerPanel.add(FourPlayer);
		playerPanel.add(bowltext);
		playerPanel.add(bowlimage);
		playerPanel.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) 
	{
		JButton button;
		JMenuItem item;
		Object source = event.getSource();
		if(source instanceof JButton)
		{
			button = (JButton) source;

			//Handles the number of players each button represents
			if(button.getText().equals("One")) {
				numPlayers = 1;
				handlePlayers(numPlayers, button);
			}
			else if (button.getText().equals("Two")) {
				numPlayers = 2;
				handlePlayers(numPlayers, button);
			}
			else if(button.getText().equals("Three")) {
				numPlayers = 3;
				handlePlayers(numPlayers, button);
			}
			else if(button.getText().equals("Four")) {
				numPlayers = 4;
				handlePlayers(numPlayers, button);
			}

			else if(numberOfGames(button)) 
			{
				//Allows these panels to not be shown
				playerPanel.setVisible(false);
				pinsPanel.setVisible(false);

				//Sets the size and what is in the text fields
				Dimension dm = new Dimension(100,600);
				scorePanel.setPreferredSize(dm);
				textArea1.setText("");
				textArea2.setText("");
				textArea3.setText("");
				textArea4.setText("");

				//Handles how many text fields pop up for the number of players there are and adds their labels
				if(numPlayers == 1)
				{
					scorePanel.setLayout(new GridLayout(2,1));
					textArea1.setPreferredSize(dm);
					scorePanel.add(playerOne);
					scorePanel.add(textArea1);
				}
				else if(numPlayers == 2)
				{
					scorePanel.setLayout(new GridLayout(4,1));
					scorePanel.setPreferredSize(new Dimension(100, 600));
					textArea1.setPreferredSize(dm);
					textArea2.setPreferredSize(dm);
					scorePanel.add(playerOne);
					scorePanel.add(textArea1);
					scorePanel.add(playerTwo);
					scorePanel.add(textArea2);
				}
				else if(numPlayers == 3)
				{
					scorePanel.setLayout(new GridLayout(6,1));
					scorePanel.setPreferredSize(new Dimension(100, 600));
					textArea1.setPreferredSize(dm);
					textArea2.setPreferredSize(dm);
					textArea3.setPreferredSize(dm);
					scorePanel.add(playerOne);
					scorePanel.add(textArea1);
					scorePanel.add(playerTwo);
					scorePanel.add(textArea2);
					scorePanel.add(playerThree);
					scorePanel.add(textArea3);
				}
				else if(numPlayers == 4)
				{
					scorePanel.setLayout(new GridLayout(8,1));
					scorePanel.setPreferredSize(new Dimension(100, 600));
					textArea1.setPreferredSize(dm);
					textArea2.setPreferredSize(dm);
					textArea3.setPreferredSize(dm);
					textArea4.setPreferredSize(dm);
					scorePanel.add(playerOne);
					scorePanel.add(textArea1);
					scorePanel.add(playerTwo);
					scorePanel.add(textArea2);
					scorePanel.add(playerThree);
					scorePanel.add(textArea3);
					scorePanel.add(playerFour);
					scorePanel.add(textArea4);
				}
			}

			//How many frames are in a certain amount of games and allows certain buttons to be removed and different ones added
			if (button.getText().equals("One Game")) 
			{
				removeButtons();
				frames = 10;
				handleFrames(frames);
			}
			else if(button.getText().equals("Two Games")) {
				removeButtons();
				frames = 20;
				handleFrames(frames);
			}
			else if(button.getText().equals("Three Games")) {
				removeButtons();
				frames = 30;
				handleFrames(frames);
			}

			//Allows a strike to be worth 10 and creates new labels asking whose score to enter
			else if(pinsPanel.isVisible() && scorePanel.isVisible() && !gamePanel.isVisible() && !playerPanel.isVisible())
			{
				int player = 0;
				askScore = new JPanel(new FlowLayout());
				JLabel askP1 = new JLabel("Enter player 1 score");
				JLabel askP2 = new JLabel("Enter player 2 score");
				JLabel askP3 = new JLabel("Enter player 3 score");
				JLabel askP4 = new JLabel("Enter player 4 score");
				if(numPlayers == 1) 
				{
					bowlScore.add(askScore);
					askScore.add(askP1);
					JButton buttonScore = (JButton) source;
					String score = buttonScore.getText();
					textArea1.append(score+"\n");
					if (score.equals("X")) {
						addToArray(player, frameNumber, 10);
						addToArray(player, frameNumber, -1);
					}
					else {
						addToArray(player, frameNumber, Integer.parseInt(score));

					}
				}
				else if(numPlayers == 2) {
					bowlScore.add(askScore);
					askScore.add(askP1);
					askScore.add(askP2);
					JButton buttonScore = (JButton) source;
					String score = buttonScore.getText();
					textArea1.append(score+"\n");
					textArea2.append(score+"\n");
					if (score.equals("X")) {
						addToArray(player, frameNumber, 10);
						addToArray(player, frameNumber, -1);
					}
					else {
						addToArray(player, frameNumber, Integer.parseInt(score));
					}
				}
				else if(numPlayers == 3) {
					bowlScore.add(askScore);
					askScore.add(askP1);
					askScore.add(askP2);
					askScore.add(askP3);
					JButton buttonScore = (JButton) source;
					String score = buttonScore.getText();
					textArea1.append(score+"\n");
					textArea2.append(score+"\n");
					textArea3.append(score+"\n");
					if (score.equals("X")) {
						addToArray(player, frameNumber, 10);
						addToArray(player, frameNumber, -1);
					}
					else {
						addToArray(player, frameNumber, Integer.parseInt(score));
					}
				}
				else if(numPlayers == 4) {
					bowlScore.add(askScore);
					askScore.add(askP1);
					askScore.add(askP2);
					askScore.add(askP3);
					askScore.add(askP4);
					JButton buttonScore = (JButton) source;
					String score = buttonScore.getText();
					textArea1.append(score+"\n");
					textArea2.append(score+"\n");
					textArea3.append(score+"\n");
					textArea4.append(score+"\n");
					if (score.equals("X")) {
						addToArray(player, frameNumber, 10);
						addToArray(player, frameNumber, -1);
					}
					else {
						addToArray(player, frameNumber, Integer.parseInt(score));
					}
				}
			}
		}
		
		//Says what happens when the menu buttons are pushed
		if(source instanceof JMenuItem) 
		{
			item = (JMenuItem) source;
			//Starts from the beginning
			if(item.getText().equals("Start Over")) 
				handleStartOver();
			//Tells you how to use the program
			else if (item.getText().equals("about"))
				handleAbout();
			//Closes the program
			else if (item.getText().equals("Exit"))
				System.exit(0);
		}
	}
	
	//Allows to enter score for which player is currently up
	private void addToArray(int player, int frameNumber1, int score) {
		if(player == 0) {
			p1Score[frameNumber] = score;
		}else if(player == 1) {
			p2Score [frameNumber] = score;
		}else if(player == 2) {
			p3Score [frameNumber] = score;
		}else if (player == 3) {
			p4Score [frameNumber] = score;
		}
		frameNumber++;
		System.out.println(calculateScore(p1Score));
	}
	
	//Calculations used in actual bowling
	public int calculateScore(int[] playerScores) {
		for(int i=0; i<playerScores.length; i++)
		System.out.print(playerScores[i]);
		System.out.println();
		int totalScore = 0;
		for(int i = 0; i < playerScores.length; i++) 
		{	
			if(i%2 == 0 && playerScores[i] == 10 ) {
				if (playerScores[i+4]==10) //2 or more strikes
					totalScore +=  10 + playerScores[i + 2] + playerScores[i + 4] ;
				else if (playerScores[i+2] == 10) // 1 more strike
					totalScore += 10 + playerScores[i + 2] + playerScores[i + 4];	
				else if (playerScores[i+2] != 10) //0 more strike
					totalScore += playerScores[i] + playerScores[i + 2] + playerScores[i + 4];
				i++;
			}
			else if(i%2 == 0 && i<26 && playerScores[i]+playerScores[i+1] == 10) {
				totalScore += 10 + playerScores[i + 2] + playerScores[i + 4];
				i++;
			}
			else {
				totalScore += playerScores[i];
			}
		}
		return totalScore;
		
	}
	
	//Sets the gamePanel to not be shown
	private void removeButtons() {
		gamePanel.setVisible(false);
	}
	
	//Allows the pinsPanel and scorePanel to be shown and added to the container
	private void handleFrames(int frames) {
		pinsPanel.setVisible(true);
		scorePanel.setVisible(true);
		bowlScore.add(scorePanel);
		bowlScore.add(pinsPanel);
		pinsPanel.add(onePin);
		pinsPanel.add(twoPin);
		pinsPanel.add(threePin);
		pinsPanel.add(fourPin);
		pinsPanel.add(fivePin);
		pinsPanel.add(sixPin);
		pinsPanel.add(sevenPin);
		pinsPanel.add(eightPin);
		pinsPanel.add(ninePin);
		pinsPanel.add(gutter);
		pinsPanel.add(spare);
		pinsPanel.add(strike);
	}

	//Allows the switch from the playerPanel to gamePanel after the user pushes how many players there are
	private void handlePlayers(int numPlayers, JButton button) 
	{
		//Sets playerPanel not visible and sets the layout of the container
		playerPanel.setVisible(false);
		bowlScore  = getContentPane();
		bowlScore.setLayout(new FlowLayout());
		//Creates the stuff for the gamePanel
		bowltext1 = new JLabel("How many games?");
		OneGame = new JButton("One Game");
		OneGame.addActionListener(this);
		TwoGame = new JButton("Two Games");
		TwoGame.addActionListener(this);
		ThreeGame = new JButton("Three Games");
		ThreeGame.addActionListener(this);
		//Adds the stuff to gamePanel and adds that to the container
		bowlScore.add(gamePanel);
		gamePanel.add(OneGame);
		gamePanel.add(TwoGame);
		gamePanel.add(ThreeGame);
		gamePanel.add(bowltext1);
		gamePanel.add(bowlimage);
		gamePanel.setVisible(true);
	}
	
	//Returns true once you pick how many games you are bowling
	private boolean numberOfGames(JButton button){

		return  (button.getText().equals("One Game") 
				|| button.getText().equals("Two Games") 
				|| button.getText().equals("Three Games"));
	}
	
	//Creates the settings menu
	private void createSettingsMenu() 
	{
		JMenuItem item;
		settingsMenu = new JMenu("Settings");
		item = new JMenuItem("Start Over");
		item.addActionListener(this);
		settingsMenu.add(item);
		settingsMenu.addSeparator();
		item = new JMenuItem("Exit");
		item.addActionListener(this);
		settingsMenu.add(item);	
	}
	
	//Creates the help menu
	private void createHelpMenu() 
	{
		JMenuItem item;
		helpMenu = new JMenu("Help");
		item = new JMenuItem("about");
		item.addActionListener(this);
		helpMenu.add(item);
	}
	
	//What happens when you hit the about button
	private void handleAbout() 
	{
		//Creates a message box
		JOptionPane.showMessageDialog(null, "Pick how many players and games and keep track of score as you bowl", "Bowling Score Keeper", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//What happens when you hit the start over button
	private void handleStartOver() 
	{
		//Resets everything back to how you started the program
		p1Score = new int[26];
		p2Score = new int[26];
		p3Score = new int[26];
		p4Score = new int[26];
		numPlayers = 0;
		scorePanel.setVisible(false);
		scorePanel.removeAll();
		pinsPanel.setVisible(false);
		pinsPanel.removeAll();
		gamePanel.setVisible(false);
		gamePanel.removeAll();
		askScore.setVisible(false);
		askScore.removeAll();
		playerPanel.setVisible(true);
		playerPanel.add(bowlimage);
	}
}



package baseballTracker;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.eclipse.wb.swing.FocusTraversalOnArray;


public class jFrameWindow extends JFrame {

	private JPanel contentPane;
	private JTextField nameBox;
	final int MAX_PLAYERS = 30;
	ArrayList<player> playerList = new ArrayList<player>();
	ArrayList<player> homePlayers = new ArrayList<player>();
	ArrayList<player> awayPlayers = new ArrayList<player>();
	ArrayList<player> homePlayersInOrder = new ArrayList<player>(10);
	ArrayList<player> awayPlayersInOrder = new ArrayList<player>(10);	
	player homePitcher;
	player awayPitcher;
	player currPitcher;
	public int homeOrder = 1;
	public int awayOrder = 1;
	private JTextField outCaughtBy;
	private JTextField errorCommittedBy;
	String homeString = "";
	String awayString = "";
	player currAwayBatter;
	player currHomeBatter;
	player currBatter;
	public int outs;
	public int strikes;
	public int balls;
	String count = balls + " - " + strikes;
	public int pitchCount;
	public int inning = 1;
	public boolean top = true;
	public String fullInning = "Top 1";
	private JTextField posBox;
	private JTextField battingOrderBox;
	private JTextField homeOrAwayBox;
	private JTextField numberBox;
	private JTable table;
	DefaultTableModel model;
	public int homeRuns;
	public int awayRuns;
	public int inningRuns;
	public int homeHits;
	public int awayHits;
	public int inningHits;
	public int homeErrors;
	public int awayErrors;
	public int innningErrors;
	final JTextArea home = new JTextArea();
	final JTextArea away = new JTextArea();
	private JTextField numPlayersBox;
	public String numPlayersPlaceHolder;
	public int numPlayers;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jFrameWindow frame = new jFrameWindow();
					frame.setVisible(true);
					frame.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jFrameWindow() {
		setBackground(new Color(102, 153, 204));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 930, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		JLabel lblHomeTeam = new JLabel("Home Team:");
		lblHomeTeam.setBounds(566, 6, 104, 16);
		contentPane.add(lblHomeTeam);

		JLabel lblAwayTeam = new JLabel("Away Team:");
		lblAwayTeam.setBounds(270, 6, 104, 16);
		contentPane.add(lblAwayTeam);

		home.setEditable(false);
		home.setBackground(new Color(192, 192, 192));
		home.setBounds(566, 25, 258, 177);
		contentPane.add(home);

		away.setEditable(false);
		away.setBackground(Color.LIGHT_GRAY);
		away.setBounds(270, 25, 258, 177);
		contentPane.add(away);



		errorCommittedBy = new JTextField();
		errorCommittedBy.setBounds(122, 284, 117, 28);
		contentPane.add(errorCommittedBy);
		errorCommittedBy.setColumns(10);

		final JLabel countBox = new JLabel("New label");
		countBox.setBounds(353, 408, 203, 39);
		contentPane.add(countBox);
		countBox.setText("Count: ");

		//Batter label
		final JLabel batter = new JLabel("Batter");
		batter.setBounds(6, 269, 310, 16);
		contentPane.add(batter);

		//Pitcher label
		final JLabel pitcher = new JLabel("Pitcher");
		pitcher.setBounds(353, 313, 405, 16);
		contentPane.add(pitcher);

		JLabel lblWelcomeToball = new JLabel("Welcome to *ball Tracker!");
		lblWelcomeToball.setBounds(6, 6, 161, 16);
		contentPane.add(lblWelcomeToball);

		JButton btnPlayersteam = new JButton("Players/Team");
		btnPlayersteam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player placeHolderPlayer = new player("Placeholder", 0, 0, "", 0);
				numPlayersPlaceHolder = numPlayersBox.getText();
				int numPlayers = Integer.parseInt(numPlayersPlaceHolder);
				homePlayersInOrder.add(0, placeHolderPlayer);
				awayPlayersInOrder.add(0, placeHolderPlayer);
				for (int i = 1; i <= numPlayers; i++){
					homePlayersInOrder.add(i, null);
					awayPlayersInOrder.add(i, null);
				}
				numPlayersBox.setText("");
			}
		});
		btnPlayersteam.setBounds(6, 20, 117, 29);
		contentPane.add(btnPlayersteam);

		numPlayersBox = new JTextField();
		numPlayersBox.setForeground(Color.BLACK);
		numPlayersBox.setBounds(122, 19, 87, 28);
		contentPane.add(numPlayersBox);
		numPlayersBox.setColumns(10);

		nameBox = new JTextField();
		nameBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				nameBox.setForeground(Color.BLACK);
				nameBox.setText("");
			}
		});	
		nameBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (nameBox.getText().isEmpty()){
					nameBox.setForeground(Color.LIGHT_GRAY);
					nameBox.setText("Name");
				}
			}
		});
		nameBox.setForeground(Color.LIGHT_GRAY);
		nameBox.setText("Name");
		nameBox.setBounds(6, 59, 203, 28);
		contentPane.add(nameBox);
		nameBox.setColumns(10);

		posBox = new JTextField();
		posBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				posBox.setForeground(Color.BLACK);
				posBox.setText("");
			}
		});
		posBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (posBox.getText().isEmpty()){
					posBox.setForeground(Color.LIGHT_GRAY);
					posBox.setText("Position (1 - 9/10)");
				}
			}
		});
		posBox.setForeground(Color.LIGHT_GRAY);
		posBox.setText("Position (1 - 9/10)");
		posBox.setColumns(10);
		posBox.setBounds(6, 84, 203, 28);
		contentPane.add(posBox);

		battingOrderBox = new JTextField();
		battingOrderBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				battingOrderBox.setForeground(Color.BLACK);
				battingOrderBox.setText("");

			}
		});
		battingOrderBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (battingOrderBox.getText().isEmpty()){
					battingOrderBox.setForeground(Color.LIGHT_GRAY);
					battingOrderBox.setText("Batting Order (1 - 9/10)");
				}
			}
		});
		battingOrderBox.setForeground(Color.LIGHT_GRAY);
		battingOrderBox.setText("Batting Order (1 - 9/10)");
		battingOrderBox.setBounds(6, 109, 203, 28);
		contentPane.add(battingOrderBox);
		battingOrderBox.setColumns(10);

		homeOrAwayBox = new JTextField();
		homeOrAwayBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				homeOrAwayBox.setForeground(Color.BLACK);
				homeOrAwayBox.setText("");
			}
		});
		homeOrAwayBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (homeOrAwayBox.getText().isEmpty()){
					homeOrAwayBox.setForeground(Color.LIGHT_GRAY);
					homeOrAwayBox.setText("Home or Away");
				}
			}
		});
		homeOrAwayBox.setForeground(Color.LIGHT_GRAY);
		homeOrAwayBox.setText("Home or Away");
		homeOrAwayBox.setBounds(6, 134, 203, 28);
		contentPane.add(homeOrAwayBox);
		homeOrAwayBox.setColumns(10);

		numberBox = new JTextField();
		numberBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				numberBox.setForeground(Color.BLACK);
				numberBox.setText("");
			}
		});
		numberBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (numberBox.getText().isEmpty()){
					numberBox.setForeground(Color.LIGHT_GRAY);
					numberBox.setText("Number");
				}
			}
		});
		numberBox.setForeground(Color.LIGHT_GRAY);
		numberBox.setText("Number");
		numberBox.setBounds(6, 159, 203, 28);
		contentPane.add(numberBox);
		numberBox.setColumns(10);

		outCaughtBy = new JTextField();
		outCaughtBy.setBounds(122, 310, 117, 28);
		contentPane.add(outCaughtBy);
		outCaughtBy.setColumns(10);

		final JButton btnFlyoutTo = new JButton("Out to -->");
		btnFlyoutTo.setBackground(new Color(192, 192, 192));
		btnFlyoutTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outPlayAB("Out to " + outCaughtBy.getText());
				updateBatterAndPitcher();
				batter.setText(currBatter.getStatsBatter());
				pitcher.setText(currPitcher.getStatsPitcher());
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
			}
		});
		btnFlyoutTo.setBounds(6, 311, 117, 29);
		contentPane.add(btnFlyoutTo);

		JButton btnb = new JButton("1B");
		btnb.setBackground(new Color(192, 192, 192));
		btnb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				safePlayAB("Single");
				addHit();
				System.out.println(currBatter.getAtBats());
				currBatter.addSingle();
				updateBatterAndPitcher();
				batter.setText(currBatter.getStatsBatter());
				pitcher.setText(currPitcher.getStatsPitcher());
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
			}
		});
		btnb.setBounds(6, 338, 117, 29);
		contentPane.add(btnb);


		JButton btnb_1 = new JButton("2B");
		btnb_1.setBackground(new Color(192, 192, 192));
		btnb_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				safePlayAB("Double");
				addHit();
				currBatter.addDouble();
				updateBatterAndPitcher();
				batter.setText(currBatter.getStatsBatter());
				pitcher.setText(currPitcher.getStatsPitcher());
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
			}
		});
		btnb_1.setBounds(6, 370, 117, 29);
		contentPane.add(btnb_1);


		JButton btnb_2 = new JButton("3B");
		btnb_2.setBackground(new Color(192, 192, 192));
		btnb_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				safePlayAB("Triple");
				addHit();
				currBatter.addTriple();
				updateBatterAndPitcher();
				batter.setText(currBatter.getStatsBatter());
				pitcher.setText(currPitcher.getStatsPitcher());
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
			}
		});
		btnb_2.setBounds(6, 400, 117, 29);
		contentPane.add(btnb_2);


		JButton btnHr = new JButton("HR");
		btnHr.setBackground(new Color(192, 192, 192));
		btnHr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inningRuns++;
				safePlayAB("HOME RUN!");
				addHit();
				currBatter.addHomeRun();
				currBatter.addRBI();
				updateBatterAndPitcher();
				batter.setText(currBatter.getStatsBatter());
				pitcher.setText(currPitcher.getStatsPitcher());
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
				//TODO CALCULATE RBIs
			}
		});
		btnHr.setBounds(6, 429, 117, 29);
		contentPane.add(btnHr);


		JButton btnNewButton = new JButton("Error to -->");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				safePlayNoAB("Error to " + errorCommittedBy.getText());
				if (currBatter.getHomeOrAway().equalsIgnoreCase("Home")){
					awayErrors++;
				}
				else if (currBatter.getHomeOrAway().equalsIgnoreCase("Away")){
					homeErrors++;
				}
				currBatter.addAtBat();
				updateBatterAndPitcher();
				batter.setText(currBatter.getStatsBatter());
				pitcher.setText(currPitcher.getStatsPitcher());
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
			}
		});
		btnNewButton.setBounds(6, 285, 117, 29);
		contentPane.add(btnNewButton);


		//Click RBI button for more than 1 RBI
		JButton btnSac = new JButton("SAC");
		btnSac.setBackground(new Color(192, 192, 192));
		btnSac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inningRuns++;
				outPlayNoAB("Sacrifice");
				currBatter.addSac();
				currBatter.addRBI();
				updateBatterAndPitcher();
				batter.setText(currBatter.getStatsBatter());
				pitcher.setText(currPitcher.getStatsPitcher());
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
			}
		});
		btnSac.setBounds(122, 370, 117, 29);
		contentPane.add(btnSac);

		JButton btnFieldersChoice = new JButton("FC");
		btnFieldersChoice.setBackground(new Color(192, 192, 192));
		btnFieldersChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outPlayAB("Fielder's Choice: ");
				updateBatterAndPitcher();
				batter.setText(currBatter.getStatsBatter());
				pitcher.setText(currPitcher.getStatsPitcher());
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
			}
		});
		btnFieldersChoice.setBounds(122, 400, 117, 29);
		contentPane.add(btnFieldersChoice);

		JButton btnRbi = new JButton("RBI");
		btnRbi.setBackground(new Color(192, 192, 192));
		btnRbi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inningRuns++;
				currBatter.setAtBatResults("RBI");
				currBatter.addRBI();
			}
		});
		btnRbi.setBounds(122, 429, 117, 29);
		contentPane.add(btnRbi);

		JButton btnStrike = new JButton("Strike");
		btnStrike.setBackground(new Color(192, 192, 192));
		btnStrike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (strikes < 2){
					currPitcher.addPitch();
					currPitcher.addStrike();
					strikes++;
					updateCount();
				}
				else {
					currPitcher.addStrikeout();
					currPitcher.addStrike();
					outPlayAB("Strikeout");
					currBatter.addK();
					strikes = 0;
					resetCount();
					updateBatterAndPitcher();
				}
				batter.setText(currBatter.getStatsBatter());
				pitcher.setText(currPitcher.getStatsPitcher());
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
			}
		});
		btnStrike.setBounds(353, 329, 117, 29);
		contentPane.add(btnStrike);

		JButton btnBall = new JButton("Ball");
		btnBall.setBackground(new Color(192, 192, 192));
		btnBall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (balls < 3){
					currPitcher.addPitch();
					currPitcher.addBall();
					balls++;
					updateCount();
				}
				else if (balls == 3){
					currPitcher.addBall();
					currPitcher.addWalk();
					currBatter.addBB();
					safePlayNoAB("Walk");
					currBatter.addBB();
					resetCount();
					updateBatterAndPitcher();
					batter.setText(currBatter.getStatsBatter());
					pitcher.setText(currPitcher.getStatsPitcher());
					countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
				}
				batter.setText(currBatter.getStatsBatter());
				pitcher.setText(currPitcher.getStatsPitcher());
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
			}
		});
		btnBall.setBounds(482, 329, 117, 29);
		contentPane.add(btnBall);

		JButton btnFoul = new JButton("Foul");
		btnFoul.setBackground(new Color(192, 192, 192));
		btnFoul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currPitcher.addPitch();
				if (strikes < 2){
					strikes++;
				}
				updateCount();
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
			}
		});
		btnFoul.setBounds(353, 355, 117, 29);
		contentPane.add(btnFoul);

		JButton btnWildPitch = new JButton("Wild Pitch");
		btnWildPitch.setBackground(new Color(192, 192, 192));
		btnWildPitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currPitcher.addWildPitch();
				currPitcher.addPitch();
			}
		});
		btnWildPitch.setBounds(482, 355, 117, 29);
		contentPane.add(btnWildPitch);

		JButton btnHbp = new JButton("HBP");
		btnHbp.setBackground(new Color(192, 192, 192));
		btnHbp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				safePlayNoAB("Hit By Pitch");
				currBatter.addHBP();
				currPitcher.addHitBatter();
				updateBatterAndPitcher();
				batter.setText(currBatter.getStatsBatter());
				pitcher.setText(currPitcher.getStatsPitcher());
				countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
			}
		});
		btnHbp.setBounds(122, 338, 117, 29);
		contentPane.add(btnHbp);

		//Adding player
		JButton btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.setBackground(new Color(192, 192, 192));
		btnAddPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				player player = null;
				String name = nameBox.getText().trim();
				int position = Integer.parseInt(posBox.getText().trim());
				int battingOrder = Integer.parseInt(battingOrderBox.getText().trim());
				String homeOrAway = homeOrAwayBox.getText().trim();

				//Add player with #
				if(!numberBox.getText().isEmpty()){
					int number = Integer.parseInt(numberBox.getText().trim());
					player = new player(name, position, battingOrder, homeOrAway, number);
					playerList.add(player);
					clearBoxes();
				}
				//Add player without #
				else{
					player = new player(name, position, battingOrder, homeOrAway);
					playerList.add(player);
					clearBoxes();
				}

				if (player.getHomeOrAway().equalsIgnoreCase("Home")){
					homePlayers.add(player);
					int spotInOrder = player.getBattingOrder();
					homePlayersInOrder.set(spotInOrder, player);
					homeString+= player.getAll() + "\n";
					home.setText(homeString);
					//Gets home pitcher
					if (player.getPosition() == 1) {
						homePitcher = player;
					}
				}
				//Gets away team
				else if (player.getHomeOrAway().equalsIgnoreCase("away")){
					awayPlayers.add(player);
					int spotInOrder = player.getBattingOrder();
					awayPlayersInOrder.set(spotInOrder, player);
					awayString+= player.getAll() + "\n";
					away.setText(awayString);
					//Gets away pitcher
					if (player.getPosition() == 1) {
						awayPitcher = player;
					}
				}
				away.setText(awayString);
				home.setText(homeString);
			}
		});
		btnAddPlayer.setBounds(50, 187, 117, 29);
		contentPane.add(btnAddPlayer);



		//Start game click
		JButton btnStartGame = new JButton("Start game!!");
		btnStartGame.setBackground(new Color(192, 192, 192));
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				for (int i = 1; i < 10; i++ ){
//					int j = i;
//					if (i == 1){
//						player t = new player("*Home Player"+ j, j, j, "Home", j);
//					}
//					player t = new player("Home Player"+ j, j, j, "Home", j);
//					homePlayers.add(t);
//					homeString = homeString + t.getName() + "\n";
//					if (t.getPosition() == 1) {
//						homePitcher = t;
//					}
//				}
//				for (int i = 1; i < 10; i++ ){
//					int j = i;
//					if (i == 1){
//						player t = new player("*Away Player"+ j, j, j, "Home", j);
//					}
//					player t = new player("Away Player"+ j, j, j, "Away", j);
//					awayPlayers.add(t);
//					awayString = awayString + t.getName() + "\n";
//					if (t.getPosition() == 1) {
//						awayPitcher = t;
//					}
//				}
				awayString = "";
				homeString = "";
				for (int i = 1; i <= homePlayersInOrder.size() - 1; i++){
					homeString+= homePlayersInOrder.get(i).getStatsBatter() + "\n";
					home.setText(homeString);
				}
				for (int i = 1; i <= awayPlayersInOrder.size() - 1; i++){
					awayString+= awayPlayersInOrder.get(i).getStatsBatter() + "\n";
					away.setText(awayString);
				}

				//TODO Fix conditional
				if (1==1){ 
					//Gets first batter from Away team
					currAwayBatter = awayPlayersInOrder.get(awayOrder);
					currHomeBatter = homePlayersInOrder.get(homeOrder);
					currBatter = currAwayBatter;
					currPitcher = homePitcher;
					pitcher.setText(currPitcher.getStatsPitcher());
					batter.setText(currBatter.getStatsBatter());
					countBox.setText(count + ", " + outs + " outs" + ", " + fullInning);
				}

				away.setText(awayString);
			}	
		});
		btnStartGame.setBounds(50, 228, 117, 29);
		contentPane.add(btnStartGame);


		String col[] = {"1","2","3","4","5","6","7","8","9","R","H","E"};
		model = new DefaultTableModel(col, 3);
		table = new JTable(model);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setBackground(new Color(192, 192, 192));
		table.setBounds(270, 214, 474, 48);
		table.setValueAt("1", 0, 0);
		table.setValueAt("2", 0, 1);
		table.setValueAt("3", 0, 2);
		table.setValueAt("4", 0, 3);
		table.setValueAt("5", 0, 4);
		table.setValueAt("6", 0, 5);
		table.setValueAt("7", 0, 6);
		table.setValueAt("8", 0, 7);
		table.setValueAt("9", 0, 8);
		table.setValueAt("R", 0, 9);
		table.setValueAt("H", 0, 10);
		table.setValueAt("E", 0, 11);
		contentPane.add(table);

		JRadioButton homePlate = new JRadioButton("");
		homePlate.setBounds(423, 571, 28, 23);
		contentPane.add(homePlate);

		JRadioButton radioButton = new JRadioButton("");
		radioButton.setBounds(484, 518, 28, 23);
		contentPane.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.setBounds(423, 468, 28, 23);
		contentPane.add(radioButton_1);

		JRadioButton radioButton_2 = new JRadioButton("");
		radioButton_2.setBounds(363, 518, 28, 23);
		contentPane.add(radioButton_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setBounds(663, 281, 248, 177);
		textPane.setText("Key: \n1. Pitcher (P)\n 2. Catcher (C)\n 3. 1st Base (1B)\n 4. 2nd Base (2B)\n 5. 3rd Base (3B)\n 6. Shortstop (SS)\n 7. Left Field (LF)\n 8. Center Field (CF)\n 9. Right Field (RF)\n 10. Extra Outfielder (OF)");
		contentPane.add(textPane);




	}

	public void outPlayNoAB(String play){
		currBatter.setAtBatResults(play);
		outs++;
		currPitcher.addPitch();
		resetCount();
		if (currBatter.getHomeOrAway().equalsIgnoreCase("Home")){
			table.setValueAt(inningRuns, 2, inning - 1);
			table.setValueAt(homeRuns, 2, 9);
		}
		else if (currBatter.getHomeOrAway().equalsIgnoreCase("Away")){
			table.setValueAt(inningRuns, 1, inning - 1);
			table.setValueAt(awayRuns, 1, 9);
		}	
	}

	public void outPlayAB(String play){
		currBatter.addAtBat();
		currBatter.setAtBatResults(play);
		outs++;
		currPitcher.addPitch();
		resetCount();
		if (currBatter.getHomeOrAway().equalsIgnoreCase("Home")){
			table.setValueAt(inningRuns, 2, inning - 1);
			table.setValueAt(homeRuns, 2, 9);
		}
		else if (currBatter.getHomeOrAway().equalsIgnoreCase("Away")){
			table.setValueAt(inningRuns, 1, inning - 1);
			table.setValueAt(awayRuns, 1, 9);
		}
	}

	public void safePlayAB(String play){
		currBatter.addAtBat();
		currBatter.setAtBatResults(play);
		currPitcher.addPitch();
		resetCount();
		if (currBatter.getHomeOrAway().equalsIgnoreCase("Home")){
			table.setValueAt(inningRuns, 2, inning - 1);
			table.setValueAt(homeRuns, 2, 9);
		}
		else if (currBatter.getHomeOrAway().equalsIgnoreCase("Away")){
			table.setValueAt(inningRuns, 1, inning - 1);
			table.setValueAt(awayRuns, 1, 9);
		}
	}

	public void safePlayNoAB(String play){
		currBatter.setAtBatResults(play);
		currPitcher.addPitch();
		resetCount();
		if (currBatter.getHomeOrAway().equalsIgnoreCase("Home")){
			table.setValueAt(inningRuns, 2, inning - 1);
			table.setValueAt(homeRuns, 2, 9);
		}
		else if (currBatter.getHomeOrAway().equalsIgnoreCase("Away")){
			table.setValueAt(inningRuns, 1, inning - 1);
		}
	}

	public void updateCount(){
		count = balls + " - " + strikes; 
	}

	public void resetCount(){
		strikes = 0;
		balls = 0;
		count = balls + " - " + strikes; 
	}

	public void clearBoxes(){
		nameBox.setForeground(Color.LIGHT_GRAY);
		posBox.setForeground(Color.LIGHT_GRAY);
		battingOrderBox.setForeground(Color.LIGHT_GRAY);
		homeOrAwayBox.setForeground(Color.LIGHT_GRAY);
		numberBox.setForeground(Color.LIGHT_GRAY);
		nameBox.setText("Name");
		posBox.setText("Position (1 - 9/10");
		battingOrderBox.setText("Batting Order (1 - 9/10)");
		homeOrAwayBox.setText("Home or Away");
		numberBox.setText("Number");
	}

	public void updateBatterAndPitcher(){
		if (outs == 3 && currBatter.getHomeOrAway().equalsIgnoreCase("away")){
			awayOrder++;
			if (homeOrder % homePlayersInOrder.size() == 0){
				currHomeBatter = homePlayersInOrder.get(1);
			}
			else{
				currHomeBatter = homePlayersInOrder.get(homeOrder % homePlayersInOrder.size());
			}
			currHomeBatter = homePlayersInOrder.get(homeOrder % homePlayersInOrder.size());
			currBatter = currHomeBatter;
			awayRuns = awayRuns + inningRuns;
			outs = 0;
			inningRuns = 0;
			currPitcher = awayPitcher;
			top = false;
			fullInning = "Bottom " + inning;
			awayRuns = awayRuns + inningRuns;
			table.setValueAt(awayRuns, 1, 9);
			table.setValueAt(awayErrors, 1, 11);
			table.setValueAt(awayHits, 1, 10);
//			if (homeString.contains(currBatter.getName())){
//				String newHomeString = homeString.replace(currBatter.getName(), "*" + currBatter.getName());
//				home.setText(newHomeString);
//			}

		}
		else if (outs == 3 && currBatter.getHomeOrAway().equalsIgnoreCase("home")){
			homeOrder++;
			if (awayOrder % awayPlayersInOrder.size() == 0){
				currAwayBatter = awayPlayersInOrder.get(1);
			}
			else{
				currAwayBatter = awayPlayersInOrder.get(awayOrder % awayPlayersInOrder.size());
			}
			currBatter = currAwayBatter;
			homeRuns = homeRuns + inningRuns;
			outs = 0;
			inningRuns = 0;
			currPitcher = homePitcher;
			top = true;
			inning++;
			fullInning = "Top " + inning;
			homeRuns = homeRuns + inningRuns;
			table.setValueAt(homeRuns, 2, 9);
			table.setValueAt(homeErrors, 2, 11);
			table.setValueAt(homeHits, 2, 10);
//			if (awayString.contains(currBatter.getName())){
//				String newAwayString = awayString.replace(currBatter.getName(), "*" + currBatter.getName());
//				away.setText(newAwayString);
//			}
		}
		//TODO look into adding runs to final score during inning
		else if (outs < 3 && currBatter.getHomeOrAway().equalsIgnoreCase("away")){
			awayOrder++;
			if (awayOrder % awayPlayersInOrder.size() == 0){
				currAwayBatter = awayPlayersInOrder.get(1);
			}
			else{
				currAwayBatter = awayPlayersInOrder.get(awayOrder % awayPlayersInOrder.size());
			}
			currBatter = currAwayBatter;
//			if (awayString.contains(currBatter.getName())){
//				String newAwayString = awayString.replace(currBatter.getName(), "*" + currBatter.getName());
//				away.setText(newAwayString);
//			}
		}
		else if (outs < 3 && currBatter.getHomeOrAway().equalsIgnoreCase("home")){
			homeOrder++;
			if (homeOrder % homePlayersInOrder.size() == 0){
				currHomeBatter = homePlayersInOrder.get(1);
			}
			else{
				currHomeBatter = homePlayersInOrder.get(homeOrder % homePlayersInOrder.size());
			}
			currBatter = currHomeBatter;
//			if (homeString.contains(currBatter.getName())){
//				String newHomeString = homeString.replace(currBatter.getName(), "*" + currBatter.getName());
//				home.setText(newHomeString);
//			}
		}
	}

	public void addHit(){
		if (currBatter.getHomeOrAway().equalsIgnoreCase("Home")){
			homeHits++;
		}
		else if (currBatter.getHomeOrAway().equalsIgnoreCase("Away")){
			awayHits++;
		}
	}
}

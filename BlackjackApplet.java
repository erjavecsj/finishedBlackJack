import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;

public class BlackjackApplet extends Applet implements ActionListener{

	private Deck table;
	private Hand player;
	private Hand dealer;
	private int totalCards;
	private JButton hit, stay, doubleDown, reset, bet, confirmBet;
	private JLabel label, winner, busted;
	private String answer;
	private int playerSum;
	private int dealerSum;
	private int wallet;
	private int pot;
	private boolean front = false;
	private boolean first = true;
	private boolean playerWin;
	private boolean playerTie;
	private boolean dealerWin;



	public void init() {
		int total = 0;
		table = new Deck();
		player = new Hand();
		dealer = new Hand();
		table.shuffle();
		player.addACard(table.deal());
		dealer.addACard(table.deal());
		player.addACard(table.deal());
		dealer.addACard(table.deal());

		wallet = 100;
		label = new JLabel("You have $" + wallet + " the pot is $" + pot);
		label.setFont(new Font("Sansserif", Font.BOLD, 32));
		this.add(label);
	
		String title = "Hit";
		hit = new JButton(title);
		hit.setActionCommand(title);
		hit.addActionListener(this);
		this.add(hit);

		title = "Stay";
		stay = new JButton(title);
		stay.setActionCommand(title);
		stay.addActionListener(this);
		this.add(stay);

		title = "New Game";
		reset = new JButton(title);
		reset.setActionCommand(title);
		reset.addActionListener(this);
		this.add(reset);

		title = "Bet $10";
		bet = new JButton(title);
		bet.setActionCommand(title);
		bet.addActionListener(this);
		this.add(bet);

		title = "Confirm Bet";
		confirmBet = new JButton(title);
		confirmBet.setActionCommand(title);
		confirmBet.addActionListener(this);
		this.add(confirmBet);;

		title = "Double Down";
		doubleDown = new JButton(title);
		doubleDown.setActionCommand(title);
		doubleDown.addActionListener(this);
		this.add(doubleDown);

	}

	public  void initialDeal() {
		this.player = new Hand();
		this.dealer = new Hand();
		player.addACard(table.deal());
		dealer.addACard(table.deal());
		player.addACard(table.deal());
		dealer.addACard(table.deal());
		playerSum = player.getValue();
		dealerSum = dealer.getValue();
		this.playerSum = player.getValue();
		this.dealerSum = dealer.getValue();
		hit.setEnabled(true);
		bet.setEnabled(false);
		confirmBet.setEnabled(false);
		stay.setEnabled(true);
		doubleDown.setEnabled(true);
	}


	public Deck getTable() {
		return this.table;
	}

	public Hand getPlayer() {
		return this.player;
	}

	public void setTable(Deck table) {
		this.table = table;
	}

	public void setPlayer(Hand player) {
		this.player = player;

	}

	public void actionPerformed(ActionEvent ae) {
		playerSum = player.getValue();
		dealerSum = dealer.getValue();
		answer = "";
		if ("Hit".equals(ae.getActionCommand())) {
			hit();
			
		} else if ("Stay".equals(ae.getActionCommand())){
			stay();
		} 

		if ("New Game".equals(ae.getActionCommand())) {
			dealerSum = 0;
			front = false;
			first = true;
			dealer.amountOfCards = 2;
			player.amountOfCards = 2;
			winner.setText("");
			first = true;
			playerWin = false;
			playerTie = false;
			dealerWin = false;
			repaint();
		}

		if ("Bet $10".equals(ae.getActionCommand())) {
			pot += 20;
			wallet -=10;
			label.setText("you have $" + wallet + " the pot is $" + pot); 
		}

		if ("Confirm Bet".equals(ae.getActionCommand())) {
			first = false;
			initialDeal();
			repaint();
			label.setText(""); 
		}

		if ("Double Down".equals(ae.getActionCommand())) {
			wallet -= pot/2;
			pot += pot;
			hit();
			stay();
			doubleDown.setEnabled(false);
			label.setText("you have $" + wallet + " the pot is $" + pot); 
		}


	}

	public void hit() {
		doubleDown.setEnabled(false);
		player.addACard(table.deal());
		playerSum = player.getValue();
		if (playerSum < 21) {
			System.out.println(this.table);
			System.out.println(this.player);

			repaint();
		} else {
			stay();
		}
	}

	public void stay() {
		front = true;
		hit.setEnabled(false);
		bet.setEnabled(false);
		stay.setEnabled(false);
		confirmBet.setEnabled(false);
		doubleDown.setEnabled(false);
		reset.setEnabled(true);
		while (dealerSum < 17 && dealerSum < playerSum && playerSum <22) {
			dealer.addACard(table.deal());
			dealerSum = dealer.getValue();
			repaint();
		}
		repaint();
		calculateWinner();
		if (playerWin == true) {
			wallet += pot;
		} else if (playerTie == true) {
			wallet += pot/2;
		}
		pot = 0;
		label.setText("You have $" + wallet + " the pot is $" + pot);
		repaint();
	}
		
	public void paint(Graphics g) {
		super.paint(g);
		if (first == true) {
			hit.setEnabled(false);
			stay.setEnabled(false);
			doubleDown.setEnabled(false);
			reset.setEnabled(false);
			bet.setEnabled(true);
			confirmBet.setEnabled(true);
			player.drawBacksPlayer(g);
			dealer.drawBacksDealer(g);
		} else if (front == true) {
			player.drawBacksPlayer(g);
			dealer.drawBacksDealer(g);
		} else {
			player.drawPlayer(g);
			dealer.drawDealerFirst(g);
		}

		if (playerWin == true) {
			g.drawString("You Win!", 40, 370);
		} else if (dealerWin == true) {
			g.drawString("You Lost", 40, 370);
		} else if (playerTie == true) {
			g.drawString("Push", 40, 370);
		}
	}

	public void calculateWinner() {
		if ((player.getValue() > dealer.getValue() && player.getValue() < 22) || (dealer.getValue() > 21 && player.getValue() < 22)) {
			playerWin = true;
		} else if (player.getValue() == dealer.getValue() && player.getValue() < 22) {
			playerTie = true;
		} else {
			dealerWin = true;
		}
	}



	

}
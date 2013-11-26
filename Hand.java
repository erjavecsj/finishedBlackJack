import java.awt.*;

public class Hand {
	
	public int amountOfCards;
	private Card[] maxCards;

	public Hand() {
		int total = 0;
		this.maxCards = new Card[11];


	}

	public void addACard(Card newCard) {
		maxCards[amountOfCards] = newCard;
		amountOfCards++;
	}

	public void drawPlayer(Graphics g) {
		int xOffset = 25;
		for (int i = 0; i <amountOfCards; i++) {
			maxCards[i].draw(g, new Rectangle(xOffset, 50, 200, 300));
			xOffset += 50;
		}

		g.drawString("Total: " + this.getValue(), 40, 370);
	}

	public void drawBacksPlayer (Graphics g) {
		int xOffset = 25;
		for (int i = 0; i <amountOfCards; i++) {
			maxCards[i].draw(g, new Rectangle(xOffset, 50, 200, 300));
			xOffset += 50;
		}
	}

	public void drawBacksDealer(Graphics g) {
		int xOffset = 25;
		for (int i = 0; i <amountOfCards; i++) {
			maxCards[i].draw(g, new Rectangle(xOffset, 500, 200, 300));
			xOffset += 50;
		}
	}
 
	public void drawDealerFirst(Graphics g) {
		int xOffset = 25;
		for (int i = 0; i <amountOfCards; i++) {
			if (i ==0) {
				maxCards[i].drawBackOfCard(g, new Rectangle(xOffset, 500, 200, 300));
			}else	{
				maxCards[i].draw(g, new Rectangle(xOffset, 500, 200, 300));
			}

			xOffset += 50;
		}

	
	}

	public void drawDealer(Graphics g) {
		int xOffset = 25;
		for (int i = 0; i <amountOfCards; i++) {
			maxCards[i].draw(g, new Rectangle(xOffset, 500, 200, 300));
			xOffset += 50;
		}

		g.drawString("Total: " + this.getValue(), 40, 820);
	}

	public int getValue() {
		int total = 0;
		for (int i = 0; i<amountOfCards; i++) {
			total += maxCards[i].getValue2();
		}

		if (total > 21) {
			total = 0;
			for (int i = 0; i <amountOfCards; i++) {
				total += maxCards[i].getValue();
			}
		}
		return total;
	}

	
}
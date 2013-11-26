import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Deck {
	
	Card[] cards = new Card[52];
	Random random = new Random();
	int loc = 0;
	int total = 0;
	int totalcards = 0;

	public Deck() {
		for (int h=1; h<=13; h++) {
			int i = h;
			if (h>10) {
				i = 10;
			}
			Card newCard= new Card(i, "Hearts", h);
			cards[h-1] = newCard;
	
		}

		for (int d=1; d<=13; d++) {
			int i = d;
			if (d>10) {
				i = 10;
			}
			Card newCard= new Card(i, "Diamonds", d);
			cards[d+12] = newCard;
			
		}

		for (int c=1; c<=13; c++) {
			int i = c;
			if (c>10) {
				i = 10;
			}
			Card newCard= new Card(i, "Clubs", c);
			cards[c+25] = newCard;
			
		}

		for (int s=1; s<=13; s++) {
			int i = s;
			if (s>10) {
				i = 10;
			}
			Card newCard= new Card(i, "Spades", s);
			cards[s+38] = newCard;
			
		}

		// shuffle();

		
	}

	public void shuffle() {
		
		for (int i = cards.length - 1; i>0; i--) {
			Card a = cards[i];
			int temp = random.nextInt(i);
			cards[i] = cards[temp];
			cards[temp] = a;
		}
	}

	public void print() {
		for (int i =0; i<52; i++) {
			cards[i].print();
		}
	}

	public void draw(Graphics g, int xOffSet, int yOffSet, int amount) {
		
		for (int i =0; i<amount; i++) {
			if (total > 21) {
				System.out.println(total);
			} else {
				cards[loc].draw(g, new Rectangle(xOffSet, yOffSet, 200, 300));
				total += cards[loc].getValue();
				xOffSet += 25;
				loc++;
				System.out.println(total);
			}
		}

		if (loc > 41) {
			shuffle();
			loc = 0;
		}
	}

	public Card deal(){
		totalcards++;
		return cards[totalcards-1];
	}


}
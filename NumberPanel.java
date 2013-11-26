import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class NumberPanel extends JPanel implements ActionListener, MouseListener {
	
	private JLabel label;
	private JButton hit, stay, doubleDown;
	private int value = 0;
	private Hand player;
	private Deck table;
	private BlackjackApplet bja;

	public NumberPanel() {
		super();
		this.bja = new BlackjackApplet();
		this.player = bja.getPlayer();
		this.table = bja.getTable();


		// label = new JLabel(value+"");
		// label.setFont(new Font("sansserif", Font.BOLD, 32));
		// this.add(label);

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

		this.addMouseListener(this);
		
	} 

	public void actionPerformed(ActionEvent ae) {
		if ("Hit".equals(ae.getActionCommand())) {
			player.addACard(table.deal());
			
			repaint();
		} else if ("Stay".equals(ae.getActionCommand())){
			
			repaint();
		}	
	}

	public void mousePressed(MouseEvent e) {
		value++;
		label.setText(value+"");
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}
}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Sidebar extends JPanel {
	public Sidebar() {
		setBackground(Pallet.nearBlack);
		setPreferredSize(new Dimension(200, HEIGHT));
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Pallet.mistBlack));

		add(new SidebarButton("Home"));
		add(new SidebarButton("Files"));
	}

	private static class SidebarButton extends JButton implements MouseListener, ActionListener {
		private String title;

		public SidebarButton(String title) {
			this.title = title;

			setFocusPainted(false);
			setBorder(null);

			Font currentFont = new Font(Font.SANS_SERIF, Font.PLAIN, 22);
			setFont(currentFont);
			setText(title);

			setPreferredSize(new Dimension(195, 40));
			setBackground(Pallet.nearBlack);
			setForeground(Pallet.bitterSweet);
			addActionListener(this);
			addMouseListener(this);
		}

		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}

		public void mouseEntered(MouseEvent e) {
			setBackground(Pallet.bitterSweet);
			setForeground(Pallet.nearBlack);
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setBackground(Pallet.nearBlack);
			setForeground(Pallet.bitterSweet);
			repaint();
		}

		public void actionPerformed(ActionEvent e) {
			if(title.equals("Home")) {
				Library.instace.refresh();
			} else if(title.equals("Files")) {
				//
			}
		}
	}
}

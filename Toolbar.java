import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Toolbar extends JPanel {
	private final int height = 80;
	private JLabel title = new JLabel("GENERIC MANGA VIEWER");

	public Toolbar() {
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 20, 20);
		Border border = BorderFactory.createMatteBorder(0, 0, 5, 0, Color.BLACK);

		setBorder(border);
		setLayout(layout);

		setPreferredSize(new Dimension(WIDTH, height));
		setBackground(Pallet.nearBlack);

		title.setFont(FontManager.COMIC_FONT.deriveFont(Font.BOLD, 35f));
		title.setForeground(Pallet.bitterSweet);

		add(title);
		add(new UtilButton("Add Manga", UtilButton.UPLOAD_CBZ));
		add(new UtilButton("Export Manga", UtilButton.EXPORT_CBZ));
	}
}

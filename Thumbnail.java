import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Thumbnail extends JButton implements ActionListener {
    private Image cover;
    private String name;
    private Manga book;
    private static int coverWidth = (int)(150 * 2);
    private static int coverHeight = (int)(225 * 2);

    public Thumbnail(String name, Image cover, Manga book) {
        this.book = book;
        this.name = name;
        this.cover = cover;

        setPreferredSize(new Dimension(coverWidth, coverHeight + 50));
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		addActionListener(this);
		setBorder(null);
    }

    public static int getCoverWidth() {
        return coverWidth;
    }

    public static int getCoverHeight() {
        return coverHeight;
    }

    public void openBook() {
		if(Library.instace.getCenter().getClass() == DetailView.class) {
			new Reader(book).run();
			return;
		}

		Library.instace.open(new DetailView(book));
    }

    public void paint(Graphics g) {
        super.paint(g);

		Graphics2D graphics2D = (Graphics2D)g;

        Dimension original = new Dimension(1500, 2250);
        Dimension scaled = Tools.getScaledDimension(original, getSize());

		graphics2D.setRenderingHint(
			RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON
		);

        int startX = (int)((getSize().getWidth() - scaled.getWidth()) / 2);

		graphics2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		graphics2D.setColor(Color.WHITE);
		graphics2D.drawString(name, startX, (int)scaled.getHeight() + 22);

		graphics2D.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		graphics2D.setColor(Color.WHITE);
		graphics2D.drawString(book.getPages() + " Pages", startX, (int)scaled.getHeight() + 45);

        graphics2D.drawImage(cover, startX, 0, (int)scaled.getWidth(), (int)scaled.getHeight(), getFocusCycleRootAncestor());
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public Image getCover() {
        return cover;
    }

	public void actionPerformed(ActionEvent e) {
		openBook();
	}
}

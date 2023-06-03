import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Reader extends JFrame implements KeyListener {
    private Manga book;
    //private int lastPage = -1;
    private int currentPage = 1;
    private Page pageImage = null;

    public Reader(Manga book) {
        this.book = book;
        //lastPage = book.length();
        
        Toolkit tooklit = Toolkit.getDefaultToolkit();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		updateTitle();
        setSize(1280, (int)tooklit.getScreenSize().getHeight());

        try {
            pageImage = new Page(book.getPage(currentPage));
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(pageImage, BorderLayout.CENTER);
    }

	private void updateTitle() {
		setTitle(book.getName() + " - Page " + currentPage + " - Manga Viewer");
	}

	public void run() {
		addKeyListener(this);

        setVisible(true);
	}

    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {

		boolean isRight = e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D;
		boolean isLeft = e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A;

        if(isRight && currentPage + 1 < book.length()) {
            currentPage += 1;
			pageImage.updatePage(book.getPage(currentPage));
			updateTitle();
        }

		if(isLeft && currentPage - 1 > 0) {
			currentPage -= 1;
			pageImage.updatePage(book.getPage(currentPage));
			updateTitle();
		}
    }

    public void keyReleased(KeyEvent e) {}
}
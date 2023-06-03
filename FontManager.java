import java.awt.*;
import java.io.*;

public class FontManager {
	public static Font COMIC_FONT = null;

	public static void setup() {
		try {
			COMIC_FONT = Font.createFont(Font.TRUETYPE_FONT, new File("./Comic_Book.ttf"));

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			ge.registerFont(COMIC_FONT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

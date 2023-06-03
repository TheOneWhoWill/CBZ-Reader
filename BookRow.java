import java.awt.*;
import java.util.*;
import javax.swing.*;

public class BookRow extends JPanel {

	public BookRow(ArrayList<Manga> library) {
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 20, 20);
		//BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);

		setLayout(layout);
		setBackground(Pallet.wornRed);
		
		for(Manga cbz : library) {
			Thumbnail thumbnail = new Thumbnail(cbz.getName(), cbz.getPage(1), cbz);

			add(thumbnail);
		}

		int preferredHeight = 0;
        for (Component component : getComponents()) {
            preferredHeight += component.getPreferredSize().height + 25;
        }

		setPreferredSize(new Dimension(WIDTH, preferredHeight));

	}
}

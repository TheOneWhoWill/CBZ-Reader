import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Export extends JFrame {
	private JPanel panel = new JPanel();
	private static JList<String> list = null;
	private ArrayList<File> mangaList = new ArrayList<File>();
	private String[] mangaNames = null;

	public Export() {
		add(panel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500, 600);
		panel.setBackground(Pallet.nearBlack);
		
		JLabel title = new JLabel("Export Manga");
		setTitle("Export Manga");
		title.setFont(FontManager.COMIC_FONT.deriveFont(Font.BOLD, 35f));
		title.setForeground(Pallet.bitterSweet);
		panel.add(title);

		mangaList = Library.getFiles(new File(Library.mangaDepot));
		mangaNames = new String[mangaList.size()];

		for (int i = 0; i < mangaList.size(); i++) {
			mangaNames[i] = mangaList.get(i).getName();
		}

		list = new JList<String>(mangaNames);
		list.setSelectedIndex(0);
		list.setBackground(Pallet.nearBlack);
		list.setForeground(Pallet.bitterSweet);
		list.setPreferredSize(new Dimension(275, 220));

		panel.add(list);
		panel.add(new UtilButton("Export", UtilButton.EXPORT_CBZ_REQUEST));

		setVisible(true);
	}

	public static String currentSelection() {
		return list.getSelectedValue();
	}

}

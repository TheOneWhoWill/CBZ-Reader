import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class UtilButton extends JButton implements ActionListener, MouseListener {
	public static int UPLOAD_CBZ = 0;
	public static int EXPORT_CBZ = 1;
	public static int EXPORT_CBZ_REQUEST = 2;
	private int type;

	public UtilButton(String title, int type) {
		this.type = type;

		setText(title);
		setFocusPainted(false);
		setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Pallet.mistBlack));
		setPreferredSize(new Dimension(130, 40));
		setBackground(Pallet.bitterSweet);
		addActionListener(this);
		addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(type == UPLOAD_CBZ) {
			Library instace = Library.instace;

			instace.setTitle("Uploading Manga... [Don't close the window]");

			JFileChooser chooser = new JFileChooser();

			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setMultiSelectionEnabled(true);
			// /chooser.setFileFilter(new CBZFilter());

			int result = chooser.showOpenDialog(this);

			if(result == JFileChooser.APPROVE_OPTION) {
				File[] files = chooser.getSelectedFiles();

				for(File file : files) {
					Library.addManga(file.getPath(), removeExtensionName(file.getName()));
				}
			}

			instace.refresh();
			instace.setTitle("Manga Viewer");
		} else if(type == EXPORT_CBZ) {
			new Export();
		} else if(type == EXPORT_CBZ_REQUEST) {
			String mangaName = Export.currentSelection();

			if(mangaName != null && !mangaName.equals("")) {
				JFileChooser chooser = new JFileChooser();

				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setMultiSelectionEnabled(false);

				int result = chooser.showSaveDialog(this);

				if(result == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();

					Library.exportManga(mangaName, file.getPath());
				}
			}
		}
	}

	private String removeExtensionName(String fileName) {

		int dotIndex = fileName.lastIndexOf('.');
		if(dotIndex == -1) {
			return fileName;
		} else {
			return fileName.substring(0, dotIndex);
		}
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {
		setBackground(Pallet.nearBlack);
		setForeground(Pallet.bitterSweet);
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(Pallet.bitterSweet);
		setForeground(Pallet.nearBlack);
		repaint();
	}
}

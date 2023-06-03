import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.zip.*;

import javax.swing.*;
import java.awt.*;

public class Library extends JFrame {
	private Component center;
	private JPanel panel = new JPanel();
	public static Library instace = null;
	public static String mangaDepot = "./Manga";
	private ArrayList<Manga> library = new ArrayList<Manga>();
	private Toolbar toolbar = new Toolbar();
	private Sidebar sidebar = new Sidebar();
	
	public Library() {
		instace = this;

		add(panel);
		setTitle("Manga Viewer");

		Toolkit tooklit = Toolkit.getDefaultToolkit();
		BorderLayout layout = new BorderLayout();

		panel.setLayout(layout);

		double scale = 1.3;
		Dimension size = new Dimension(
			(int)(tooklit.getScreenSize().getWidth() / scale),
			(int)(tooklit.getScreenSize().getHeight() / scale)
		);

		
		retrieveManga();

		center = getBookRow();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.add(toolbar, BorderLayout.NORTH);
		panel.add(sidebar, BorderLayout.WEST);
		panel.add(center, BorderLayout.CENTER);
		setSize(size);
		panel.setBackground(new Color(168, 108, 52));
		setVisible(true);
	}

	public Component getBookRow() {
		BookRow bookRow = new BookRow(library);
		JScrollPane scrollPane = new JScrollPane(bookRow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane.setBorder(null);
	
		return scrollPane;
	}

	public void refresh()  {
		library.clear();

		retrieveManga();

		open(getBookRow());
	}

	//public void setTitle(String title) {
	//	setTitle(title);
	//}

	private void retrieveManga() {
		try {
			ArrayList<File> cbzs = Library.getFiles(new File(mangaDepot));
			
			for(File cbz : cbzs) {
				library.add(new Manga(cbz, Manga.RIGHT_TO_LEFT));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Component getCenter() {
		return center;
	}

	public void open(Component main) {
		panel.remove(center);
		center = main;
		panel.add(center, BorderLayout.CENTER);
		panel.revalidate();
	}

	public static ArrayList<File> getFiles(File folder) {
		if(!folder.exists()) {
			folder.mkdirs();
		}

		ArrayList<File> files = new ArrayList<File>();

		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				files.add(file);
			}
		}

		return files;
	}

	public static void exportManga(String mangaName, String outputPath) {
		// Get the folder mangaName inside the mangaDepot folder
		File mangaFolder = new File(mangaDepot + File.separator + mangaName);

		// Create a ZipOutputStream to write the zipped file to
		ZipOutputStream zipOutputStream = null;
		try {
			zipOutputStream = new ZipOutputStream(new FileOutputStream(outputPath + File.separator + mangaName + ".cbz"));
			
			// Iterate over the files in the mangaFolder and add them to the zip file
			for (File file : mangaFolder.listFiles()) {
				zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
				byte[] bytes = Files.readAllBytes(file.toPath());
				zipOutputStream.write(bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				zipOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void addManga(String zipLocation, String mangaName) {
		try {
			ZipFile zip = new ZipFile(zipLocation);
			Enumeration<? extends ZipEntry> entries = zip.entries();

			File manga = new File(mangaDepot + File.separator + mangaName);
			
			if(!manga.exists()) {
				manga.mkdir();
			}

			while(entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				File file = new File(manga, entry.getName());

				if(entry.isDirectory()) {
					file.mkdir();
					continue;
				}

				InputStream input = zip.getInputStream(entry);
				FileOutputStream output = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int length;

				while((length = input.read(buffer)) > 0) {
					output.write(buffer, 0, length);
				}

				input.close();
				output.close();
			}

			zip.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.print("Finished");
	}
}

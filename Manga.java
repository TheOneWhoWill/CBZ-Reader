import javax.imageio.*;
import java.io.*;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.*;
import java.awt.*;

public class Manga {
    private int orientation = 0;
    public static int LEFT_TO_RIGHT= 0;
    public static int RIGHT_TO_LEFT = 1;
    private String fileName = "";
	private ArrayList<String> chapters = new ArrayList<String>();
    private ArrayList<String> pages = new ArrayList<String>();
	private static final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };

    public Manga(File file, int orientation) {
        this.fileName = file.getName();
        this.orientation = orientation;

        pages = getPages(file);
    }

	public String getSize() {
		long size = 0;

		for(String page : pages) {
			size += new File(page).length();
		}

		int digitGroups = (int) (Math.log10(size)/Math.log10(1024));

		return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}

	public int getPages() {
		return pages.size();
	}

	public ArrayList<String> getChapters() {
		return chapters;
	}

	private ArrayList<String> getPages(File file) {
		ArrayList<String> pages = new ArrayList<String>();

		if(file.isDirectory()) {

			for(File current : file.listFiles()) {

				if(current.isDirectory()) {
					chapters.add(current.getName());
					pages.addAll(getPages(current));
					continue;
				}

				if(isImage(current)) {
					pages.add(current.getAbsolutePath());
				}

			}
		}

		return pages;
	}

	private boolean isImage(File file) {
		try {
			String mimetype = Files.probeContentType(file.toPath());
			String type = mimetype.split("/")[0];

			return mimetype != null && type.equals("image");
		} catch (Exception e) {
			return false;
		}
	}

	// Create a recursive method for getting the paths of all files in a direcory including subfolders
    public String getName() {
        return fileName;
    }

    // Assume the first page is 1
    public Image getPage(int page) {
        Image result = null;

        if(page == 0) {
            System.out.println(
				"You cannot access the 0th page of a book,a page must be between 1 and the length of the book"
			);
        }

        String pageName = "";

        if(orientation == LEFT_TO_RIGHT) {
            pageName = pages.get(pages.size() - page - 1);
        } else {
            pageName = pages.get(page - 1);
        }

		for(String pagePath : pages) {
			if(pagePath.equals(pageName)) {
				try {
					result = ImageIO.read(new File(pagePath));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

        return result;
    }

    public int length() {
        return pages.size();
    }
}
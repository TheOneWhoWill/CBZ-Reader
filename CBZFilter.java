import java.io.*;
import java.nio.file.*;
import javax.swing.filechooser.FileFilter;

public class CBZFilter extends FileFilter {
	public boolean accept(File f) {
		String mimetype;

		try {
			mimetype = Files.probeContentType(f.toPath());
		} catch (IOException e) {
			return false;
		}

		return mimetype != null && mimetype.equals("application/zip");
	}

	public String getDescription() {
		return "Comic Book Archive (.cbz)";
	}

}

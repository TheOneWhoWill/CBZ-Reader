import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class DetailView extends JPanel {
	private JPanel right = new JPanel();

	public DetailView(Manga manga) {
		Font data = new Font(Font.SANS_SERIF, Font.PLAIN, 22);
		Font listData = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 20, 20);
		
        setLayout(layout);
		setBackground(Pallet.sunGlow);

		BoxLayout rightLayout = new BoxLayout(right, BoxLayout.Y_AXIS);

		right.setLayout(rightLayout);
		right.setOpaque(false);
		//right.setPreferredSize(new Dimension(400, 600));
		
		JLabel name = new JLabel(manga.getName());
		
		name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
		
		JLabel size = new JLabel("Size: " + manga.getSize());
		ArrayList<String> chapters = manga.getChapters();
		int chapterCount = chapters.size();
		JLabel chaptersCountLabel = new JLabel("Chapters: " + chapterCount);
		JScrollPane scrollFrame = new JScrollPane(right);
		GridLayout chapterListLayout = new GridLayout(chapterCount, 1);

		
		size.setFont(data);
		chaptersCountLabel.setFont(data);
		chapterListLayout.setVgap(10);
		// add vertical spacing
		scrollFrame.setBackground(Pallet.empty);
		scrollFrame.setOpaque(false);
		scrollFrame.setBorder(null);
		scrollFrame.getViewport().setOpaque(false);
		scrollFrame.setPreferredSize(new Dimension(400, 500));

		scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		right.add(Box.createVerticalGlue());
		right.add(name);
		right.add(size);
		right.add(chaptersCountLabel);
		
		if(chapterCount <= 1) {
			chaptersCountLabel.setText("Uncatogized Chapter Format");
		} else {
			
			for(String chapter : chapters) {
				JLabel chapterLabel = new JLabel(chapter);
				
				chapterLabel.setFont(listData);
				
				right.add(chapterLabel);
			}
			
		}

		right.add(Box.createVerticalGlue());


		add(new Thumbnail(manga.getName(), manga.getPage(1), manga));
		add(scrollFrame);
    }
}

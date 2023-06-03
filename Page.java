import javax.swing.JPanel;
import java.awt.*;

public class Page extends JPanel {
    private Image currentPage;

    public Page(Image page) {
        this.currentPage = page;

        setSize(page.getWidth(null), getHeight());
        setBackground(Color.LIGHT_GRAY);

        ((FlowLayout) getLayout()).setVgap(0);
    }

    public void updatePage(Image page) {
        currentPage = page;
        paint(getGraphics());
    }

    public void paint(Graphics g) {
        super.paint(g);

        Dimension original = new Dimension(currentPage.getWidth(getFocusCycleRootAncestor()), currentPage.getHeight(getFocusCycleRootAncestor()));
        Dimension scaled = Tools.getScaledDimension(original, getSize());

        int startX = (int)((getSize().getWidth() - scaled.getWidth()) / 2);

        g.drawImage(currentPage, startX, 0, (int)scaled.getWidth(), (int)scaled.getHeight(), getFocusCycleRootAncestor());
    }
}

import java.awt.*;

public class Tools {
    public static Dimension getScaledDimension(Dimension imageSize, Dimension boundary) {

        double widthRatio = boundary.getWidth() / imageSize.getWidth();
        double heightRatio = boundary.getHeight() / imageSize.getHeight();
        double ratio = Math.min(widthRatio, heightRatio);
    
        return new Dimension((int) (imageSize.width  * ratio), (int) (imageSize.height * ratio));
    }
}

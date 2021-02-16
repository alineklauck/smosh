import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class SpriteSheet
{
    private BufferedImage spritesheet;
    private File file;
    
    public SpriteSheet(final String path) {
        this.file = null;
        try {
            this.file = new File(path);
            this.spritesheet = ImageIO.read(this.getClass().getClassLoader().getResource(path));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public BufferedImage getSprite(final int x, final int y, final int w, final int h) {
        return this.spritesheet.getSubimage(x, y, w, h);
    }
}
package objects;

import main.GamePanel;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject {

    GamePanel gp;

    public OBJ_Boots(GamePanel gp) {
        name = "Boots";
        this.gp = gp;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

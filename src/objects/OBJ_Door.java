package objects;

import main.GamePanel;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {

    GamePanel gp;
    public OBJ_Door( GamePanel gp){
        name = "Door";
        this.gp = gp;

        try {
           image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}

package entity;

import main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {

        super(gp);
        direction = "down";
        speed = 1;
        getImage();

    }

    public void getImage() {

        up1     = setUp("/NPC/oldman_up_1");
        up2     = setUp("/NPC/oldman_up_2");
        left1   = setUp("/NPC/oldman_left_1");
        left2   = setUp("/NPC/oldman_left_2");
        right1  = setUp("/NPC/oldman_right_1");
        right2  = setUp("/NPC/oldman_right_2");
        down1   = setUp("/NPC/oldman_down_1");
        down2   = setUp("/NPC/oldman_down_2");

    }
}
package entity;

import main.GamePanel;

import java.util.Random;

public class Princess extends Entity {

    public Princess(GamePanel gp) {

        super(gp);
        direction = "down";
        speed = gp.player.speed;
        getImage();
        setDialogue();

    }

    public void getImage() {

        up1     = setUp("/NPC/princess/peach_up_1");
        up2     = setUp("/NPC/princess/peach_up_2");
        left1   = setUp("/NPC/princess/peach_left_1");
        left2   = setUp("/NPC/princess/peach_left_2");
        right1  = setUp("/NPC/princess/peach_right_1");
        right2  = setUp("/NPC/princess/peach_right_2");
        down1   = setUp("/NPC/princess/peach_down_1");
        down2   = setUp("/NPC/princess/peach_down_2");

    }

    public void setDialogue() {

        dialogues[0] = "Finally!";
        dialogues[1] = "What took you so long?";
        dialogues[2] = "Dude, I was like SO annoyed...";

    }

    @Override
    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick a number between 1 and 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 & i <= 50) {
                direction = "down";
            }
            if (i > 25 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    @Override
    // do character-specific stuff, like saying sth else if the player
    // is in possession of a special artefact
    public void speak() {

        super.speak();
    }
}
package main;

import entity.NPC_OldMan;
import objects.OBJ_Boots;
import objects.OBJ_Chest;
import objects.OBJ_Door;
import objects.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {

        // KEY
        // hide two keys in the forest
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 52 * gp.tileSize;
        gp.obj[0].worldY = 52 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 97 * gp.tileSize;
        gp.obj[1].worldY = 97 * gp.tileSize;

        // hide two keys along the river
        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 97 * gp.tileSize;
        gp.obj[2].worldY = 2 * gp.tileSize;

        gp.obj[3] = new OBJ_Key(gp);
        gp.obj[3].worldX = 52 * gp.tileSize;
        gp.obj[3].worldY = 47 * gp.tileSize;

        // DOOR
        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 22 * gp.tileSize;
        gp.obj[4].worldY = 48 * gp.tileSize;

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 23 * gp.tileSize;
        gp.obj[5].worldY = 48 * gp.tileSize;

        gp.obj[6] = new OBJ_Door(gp);
        gp.obj[6].worldX = 22 * gp.tileSize;
        gp.obj[6].worldY = 49 * gp.tileSize;

        gp.obj[7] = new OBJ_Door(gp);
        gp.obj[7].worldX = 23 * gp.tileSize;
        gp.obj[7].worldY = 49 * gp.tileSize;

        // CHEST
        // hide chest in the middle of walled-off area
        gp.obj[8] = new OBJ_Chest(gp);
        gp.obj[8].worldX = gp.map.chestx * gp.tileSize;
        gp.obj[8].worldY = gp.map.chesty * gp.tileSize;
        System.out.println("chest at row: " + gp.map.chestx + ", col: " + gp.map.chesty);

        // BOOTS
        // place boots close to player's starting position
        gp.obj[9] = new OBJ_Boots(gp);
        gp.obj[9].worldX = gp.map.bootsx * gp.tileSize;
        gp.obj[9].worldY = gp.map.bootsy * gp.tileSize;
        System.out.println("boots at row: " + gp.map.bootsx + ", col: " + gp.map.bootsy);

    }

    public void setNPC() {

        // place old man close to doors to walled-off area
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 23;
        gp.npc[0].worldY = gp.tileSize * 50;

    }
}

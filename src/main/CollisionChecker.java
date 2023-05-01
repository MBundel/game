package main;

import entity.Entity;

public class CollisionChecker {

  private GamePanel gp;

  public CollisionChecker(GamePanel gp) {
    this.gp = gp;
  }

  public void checkTile(Entity entity) {
    int entityLeft = entity.worldX + entity.solidArea.x;
    int entityRight = entity.worldX + entity.solidArea.x + entity.solidArea.width;
    int entityTop = entity.worldY + entity.solidArea.y;
    int entityBottom = entity.worldY + entity.solidArea.y + entity.solidArea.height;

    int entityLeftCol = entityLeft / gp.tileSize;
    int entityRightCol = entityRight / gp.tileSize;
    int entityTopRow = entityTop / gp.tileSize;
    int entityBottomRow = entityBottom / gp.tileSize;

    int tileNum1, tileNum2;

    switch(entity.direction) {
      case "up" -> {
        entityTopRow = (entityTop - entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
        tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
      }
      case "down" -> {
        entityBottomRow = (entityBottom + entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
        tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
      }
      case "left" -> {
        entityLeftCol = (entityLeft - entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
        tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
      }
      case "right" -> {
        entityRightCol = (entityRight + entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
        tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
      }
    }
  }

  // moving islands are colliding with anything BUT water
  public void checkTileOnTile(Entity entity) {
    int entityLeft = entity.worldX + entity.solidArea.x;
    int entityRight = entity.worldX + entity.solidArea.x + entity.solidArea.width;
    int entityTop = entity.worldY + entity.solidArea.y;
    int entityBottom = entity.worldY + entity.solidArea.y + entity.solidArea.height;

    int entityLeftCol = entityLeft / gp.tileSize;
    int entityRightCol = entityRight / gp.tileSize;
    int entityTopRow = entityTop / gp.tileSize;
    int entityBottomRow = entityBottom / gp.tileSize;

    int tileNum1, tileNum2;

    switch(entity.direction) {
      case "up" -> {
        entityTopRow = (entityTop - entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
        tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
        if (tileNum1 != 2 || tileNum2 != 2) {
          entity.collisionOn = true;
        }
      }
      case "down" -> {
        entityBottomRow = (entityBottom + entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
        tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
        if (tileNum1 != 2 || tileNum2 != 2) {
          entity.collisionOn = true;
        }
      }
      case "left" -> {
        entityLeftCol = (entityLeft - entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
        tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
        if (tileNum1 != 2 || tileNum2 != 2) {
          entity.collisionOn = true;
        }
      }
      case "right" -> {
        entityRightCol = (entityRight + entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol + 1];
        tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol + 1];
        if (tileNum1 != 2 || tileNum2 != 2) {
          entity.collisionOn = true;
        }
      }
    }
  }

  public int checkObject(Entity entity, boolean player) {
    int index = 999;

    for (int i = 0; i < gp.obj.length; i++) {
      if (gp.obj[i] != null) {
        // Get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        // Get the object's solid area position
        gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
        gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

        switch(entity.direction){
            case "up"   :  entity.solidArea.y   -= entity.speed; if(entity.solidArea.intersects(gp.obj[i].solidArea)){ if(gp.obj[i].collision){entity.collisionOn = true;}if(player){ index = i;}}     break;
            case "down" :  entity.solidArea.y   += entity.speed; if(entity.solidArea.intersects(gp.obj[i].solidArea)){ if(gp.obj[i].collision){entity.collisionOn = true;}if(player){ index = i;}}     break;
            case "left" :  entity.solidArea.x   -= entity.speed; if(entity.solidArea.intersects(gp.obj[i].solidArea)){ if(gp.obj[i].collision){entity.collisionOn = true;}if(player){ index = i;}}     break;
            case "right":  entity.solidArea.x   += entity.speed; if(entity.solidArea.intersects(gp.obj[i].solidArea)){ if(gp.obj[i].collision){entity.collisionOn = true;}if(player){ index = i;}}     break;
        }
        entity.solidArea.x    = entity.solidAreaDefaultX;
        entity.solidArea.y    = entity.solidAreaDefaultY;
        gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
        gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
      }
    }
    return index;
  }

  // NPC OR MONSTER
  public int checkEntity(Entity entity, Entity[] target) {
    int index = 999;

    for (int i = 0; i < target.length; i++) {
      if (target[i] != null) {
        // Get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        // Get the object's solid area position
        target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
        target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

        switch (entity.direction) {
          case "up" -> {
            entity.solidArea.y -= entity.speed;
            if (entity.solidArea.intersects(target[i].solidArea)) {
              entity.collisionOn = true;
              index = i;
            }
          }
          case "down" -> {
            entity.solidArea.y += entity.speed;
            if (entity.solidArea.intersects(target[i].solidArea)) {
              entity.collisionOn = true;
              index = i;
            }
          }
          case "left" -> {
            entity.solidArea.x -= entity.speed;
            if (entity.solidArea.intersects(target[i].solidArea)) {
              entity.collisionOn = true;
              index = i;
            }
          }
          case "right" -> {
            entity.solidArea.x += entity.speed;
            if (entity.solidArea.intersects(target[i].solidArea)) {
              entity.collisionOn = true;
              index = i;
            }
          }
        }
        entity.solidArea.x    = entity.solidAreaDefaultX;
        entity.solidArea.y    = entity.solidAreaDefaultY;
        target[i].solidArea.x = target[i].solidAreaDefaultX;
        target[i].solidArea.y = target[i].solidAreaDefaultY;
      }
    }
    return index;
  }

  public void checkPlayer(Entity entity) {

    // Get entity's solid area position
    entity.solidArea.x = entity.worldX + entity.solidArea.x;
    entity.solidArea.y = entity.worldY + entity.solidArea.y;
    // Get the object's solid area position
    gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
    gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

    switch (entity.direction) {
      case "up" -> {
        entity.solidArea.y -= entity.speed;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
          entity.collisionOn = true;
        }
      }
      case "down" -> {
        entity.solidArea.y += entity.speed;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
          entity.collisionOn = true;
        }
      }
      case "left" -> {
        entity.solidArea.x -= entity.speed;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
          entity.collisionOn = true;
        }
      }
      case "right" -> {
        entity.solidArea.x += entity.speed;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
          entity.collisionOn = true;
        }
      }
    }
    entity.solidArea.x    = entity.solidAreaDefaultX;
    entity.solidArea.y    = entity.solidAreaDefaultY;
    gp.player.solidArea.x = gp.player.solidAreaDefaultX;
    gp.player.solidArea.y = gp.player.solidAreaDefaultY;
  }
}


package objects;

import objects.itemAttrs.TypeOfMaterial;

import static objects.itemAttrs.TypeOfMaterial.*;

public class Equipment extends Item {

    boolean isEquippable = false;
    int durability = 1;
    int attackDmg = 0;
    int defenceMelee = 0;
    int defenceRange = 0;

    TypeOfMaterial materialType = DEFAULT;


    String description;

    public Equipment(String name, boolean isEquippable) {
        super(name);
        this.isEquippable = isEquippable;





    }

    public Equipment(String name, int goldValue, double randomSpawnProb, int weight, int[] inventorySize, boolean isPortable, String filePath, boolean isEquippable, int durability, int attackDmg, int defenceMelee, int defenceRange, TypeOfMaterial materialType, String description) {
        super(name, goldValue, randomSpawnProb, weight, inventorySize, isPortable, filePath);
        this.isEquippable = isEquippable;
        this.durability = durability;
        this.attackDmg = attackDmg;
        this.defenceMelee = defenceMelee;
        this.defenceRange = defenceRange;
        this.materialType = materialType;

        this.description = description;
    }
}

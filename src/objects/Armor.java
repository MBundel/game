package objects;

import objects.itemAttrs.TypeOfArmor;
import objects.itemAttrs.TypeOfMaterial;

public class Armor extends Equipment {

    TypeOfArmor typeOfArmor;


    public Armor(String name, boolean isEquippable, TypeOfArmor typeOfArmor) {
        super(name, isEquippable);
        this.typeOfArmor =typeOfArmor;
    }

    public Armor(String name, int goldValue, double randomSpawnProb, int weight, int[] inventorySize, boolean isPortable, String filePath, boolean isEquippable, int durability, int attackDmg, int defenceMelee, int defenceRange, TypeOfMaterial materialType, String description, TypeOfArmor typeOfArmor) {
        super(name, goldValue, randomSpawnProb, weight, inventorySize, isPortable, filePath, isEquippable, durability, attackDmg, defenceMelee, defenceRange, materialType, description);
        this.typeOfArmor =typeOfArmor;
    }
}




package objects;

import objects.itemAttrs.TypeOfMaterial;
import objects.itemAttrs.TypeOfWeapon;

public class Weapon extends Equipment {

    TypeOfWeapon typeOfWeapon;

    public Weapon(String name, boolean isEquippable, TypeOfWeapon typeOfWeapon) {
        super(name, isEquippable);
        this.typeOfWeapon = typeOfWeapon;
    }

    public Weapon(String name, int goldValue, double randomSpawnProb, int weight, int[] inventorySize, boolean isPortable, String filePath, boolean isEquippable, int durability, int attackDmg, int defenceMelee, int defenceRange, TypeOfMaterial materialType, String description, TypeOfWeapon typeOfWeapon) {
        super(name, goldValue, randomSpawnProb, weight, inventorySize, isPortable, filePath, isEquippable, durability, attackDmg, defenceMelee, defenceRange, materialType, description);
        this.typeOfWeapon = typeOfWeapon;
    }
}

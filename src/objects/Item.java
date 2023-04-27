package objects;

public class Item {
    int goldValue = 0;
    double randomSpawnProb = 0;
    int weight = 1;
    int[] inventorySize = {1,1};
    boolean isPortable = true;
    String name;

    String filePath = "default";


    // CONSTRUCTOR

    // DEFAULT CONSTRUCTOR
    public  Item(String name){
        this.name = name;



    }

    public Item(String name, int goldValue, double randomSpawnProb, int weight, int[] inventorySize, boolean isPortable,  String filePath) {
        this.goldValue = goldValue;
        this.randomSpawnProb = randomSpawnProb;
        this.weight = weight;
        this.inventorySize = inventorySize;
        this.isPortable = isPortable;
        this.name = name;
        this.filePath = filePath;
    }
}

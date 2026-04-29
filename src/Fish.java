public class Fish extends Animal {
    public static final int DEFAULT_ENERGY = 3;
    public static final int MAX_ENERGY = 5;
    public static final int TIME_TO_BIRTH = 3;
    public static int nbFish = 0;
    
    public Fish(){
        super(DEFAULT_ENERGY);
        nbFish++;
    }

    public void die(){
        super.die();
        nbFish--;
    }
}

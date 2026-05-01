public class Fish extends Animal {
    public static final int DEFAULT_ENERGY = 3;
    public static final int MAX_ENERGY = 6;
    public static final int TIME_TO_BIRTH = 4;
    private static int nbFish = 0;
    
    public Fish(){
        super(DEFAULT_ENERGY);
        nbFish++;
    }

    public Fish(boolean alive, int energy, int timeAlive){
        super(alive, energy, timeAlive);
        nbFish++;
    }

    public void die(){
        super.die();
        nbFish--;
    }

    public static int nbFish(){
        return nbFish;
    }
}

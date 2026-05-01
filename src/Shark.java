public class Shark extends Animal {
    public static final int DEFAULT_ENERGY = 5;
    public static final int MAX_ENERGY = 10;
    public static final int TIME_TO_BIRTH = 6;
    private static int nbShark = 0;
    
    public Shark(){
        super(DEFAULT_ENERGY);
        nbShark++;
    }

    public Shark(boolean alive, int energy, int timeAlive){
        super(alive, energy, timeAlive);
        nbShark++;
    }

    public void die(){
        super.die();
        nbShark--;
    }

    public static int nbShark(){
        return nbShark;
    }
}

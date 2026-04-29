public class Shark extends Animal {
    public static final int DEFAULT_ENERGY = 2;
    public static final int MAX_ENERGY = 4;
    public static final int TIME_TO_BIRTH = 3;
    public static int nbShark = 0;
    
    public Shark(){
        super(DEFAULT_ENERGY);
        nbShark++;
    }

    public void die(){
        super.die();
        nbShark--;
    }
}

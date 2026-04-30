public class Animal {
    private boolean alive;
    private int energy;
    private int timeAlive;
    private boolean movedThisStep = false;
    private static int nbAnimals = 0;

    public Animal(int energy){
        alive = energy > 0 ? true : false;
        this.energy = energy;
        timeAlive = 0;
        nbAnimals++;
    }

    public Animal(boolean alive, int energy, int timeAlive){
        this(energy);

        this.alive = alive;
        this.timeAlive = timeAlive;
    }

    public void die(){
        alive = false;
        nbAnimals--;
    }

    public void step(){
        if(energy == 0){
            die();
            return ;
        }

        energy--;
        timeAlive++;
    }

    public boolean checkBirth(int timeToBirth){
        return timeAlive != 0 && timeAlive % timeToBirth == 0;
    }

    public Animal clone(){
        if(this instanceof Fish) return new Fish(alive, energy, timeAlive);
        if(this instanceof Shark) return new Shark(alive, energy, timeAlive);
        return new Animal(0);
    }

    public void eat(int maxEnergy){
        if(energy < maxEnergy) energy++;
    }

    public boolean isAlive(){
        return alive;
    }

    public int timeAlive(){
        return timeAlive;
    }

    public void setMoved(boolean movedThisStep){
        this.movedThisStep = movedThisStep;
    }

    public boolean movedThisStep(){
        return movedThisStep;
    }

    public static int nbAnimals(){
        return nbAnimals;
    }
}

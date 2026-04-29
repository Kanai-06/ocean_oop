public abstract class Animal {
    private boolean alive;
    private int energy;
    private int timeAlive;
    private static int nbAnimals = 0;

    public Animal(int energy){
        alive = energy > 0 ? true : false;
        this.energy = energy;
        timeAlive = 0;
        nbAnimals++;
    }

    public void die(){
        alive = false;
        nbAnimals--;
    }

    public void movement(){
        if(energy == 0){
            die();
            return ;
        }

        energy--;
        timeAlive++;
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

    public static int nbAnimals(){
        return nbAnimals;
    }
}

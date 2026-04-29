public class Algae {
    private boolean val;
    private static int nbAlgae = 0;

    public Algae(boolean val){
        this.val = val;

        nbAlgae += val ? 1 : 0;
    }

    public void set(boolean val){
        if(this.val != val){
            nbAlgae += this.val ? -1 : 1;
        }

        this.val = val;
    }

    public boolean get(){
        return val;
    }

    public static int nbAlgae(){
        return nbAlgae;
    }
}

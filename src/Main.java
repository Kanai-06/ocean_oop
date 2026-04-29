public class Main {
    public static void main(String[] args) throws InterruptedException{
        int length = 160;
        int width = 450;

        Grid algaeGrid = new AlgaeGrid(length, width, 0.1);
        Grid.clear();

        while(true){
            algaeGrid.compute();
            System.out.print(String.format("%c[%d;%df",0x1b,0,0));
            System.out.print(algaeGrid);
            System.out.print(Algae.nbAlgae);
        }
        
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException{
        int length = 320;
        int width = 900;

        Grid algaeGrid = new AlgaeGrid(length, width, 0.02);
        Grid.clear();

        while(true){
            algaeGrid.compute();
            System.out.print(String.format("%c[%d;%df",0x1b,0,0));
            System.out.print(algaeGrid);
            // Thread.sleep(33);
        }
        
    }
}

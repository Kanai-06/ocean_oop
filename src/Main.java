public class Main {
    public static void main(String[] args) throws InterruptedException{
        int length = 50;
        int width = 50;
        
        Grid.clear();

        Grid algaeGrid = new AlgaeGrid(length, width, 0.2);
        Grid animalGrid = new AnimalGrid(length, width, 0.3, 0.1, (AlgaeGrid)(algaeGrid));

        while(true){
            algaeGrid.compute();
            animalGrid.compute();
            System.out.print(String.format("%c[%d;%df",0x1b,0,0));
            System.out.print(algaeGrid);
            System.out.print(String.format("%c[%d;%df",0x1b,0,0));
            System.out.print(animalGrid);
            Thread.sleep(50);
        }
        
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException{
        int length = 80;
        int width = 225;
        
        Grid.clear();

        AlgaeGrid algaeGrid = new AlgaeGrid(length, width, 0.2);
        AnimalGrid animalGrid = new AnimalGrid(length, width, 0.3, 0.1, (AlgaeGrid)(algaeGrid));

        while(true){
            algaeGrid.compute();
            animalGrid.compute();
            Grid merge = algaeGrid.merge(animalGrid);
            System.out.print(String.format("%c[%d;%df",0x1b,0,0));
            System.out.print(merge);
            
            Thread.sleep(50);
        }
        
    }
}

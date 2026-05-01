import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int length = 80;
        int width = 225;
        
        Grid.clear();

        AlgaeGrid algaeGrid = new AlgaeGrid(length, width, 0.2);
        AnimalGrid animalGrid = new AnimalGrid(length, width, 0.3, 0.1, algaeGrid);

        try (PrintWriter writer = new PrintWriter(new FileWriter("data.csv", false))) {
            writer.println("i,Algae,Animals,Fish,Sharks,Water");

            int i = 0;

            while(true){
                algaeGrid.compute();
                animalGrid.compute();
                Grid merge = algaeGrid.merge(animalGrid);
                
                System.out.print(String.format("%c[%d;%df",0x1b,0,0));
                System.out.print(merge);

                
                writer.printf("%d, %d, %d, %d, %d, %d\n", i, Algae.nbAlgae(), Animal.nbAnimals(), Fish.nbFish(), Shark.nbShark(), Grid.nbWater());
                writer.flush();
                
                Thread.sleep(33);
                i++;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
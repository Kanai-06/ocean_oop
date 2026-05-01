import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        int[] dimensions = getDimensions();
        int[] probs = getProbas();
        
        Grid.clear();

        AlgaeGrid algaeGrid = new AlgaeGrid(dimensions[0], dimensions[1], probs[0]/100.0);
        AnimalGrid animalGrid = new AnimalGrid(dimensions[0], dimensions[1], probs[1]/100.0, probs[2]/100.0, algaeGrid);

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

    public static boolean getBoolean(String m){
        boolean validValue = false;
        boolean input = false;

        while(!validValue){
            try {
                System.out.print(m);

                input = scan.nextBoolean();

                validValue = true;
            } catch (Exception e) {
                    try {
                        throw new InvalidValueException("Input must be either \"true\" or \"false\"");
                    } catch (InvalidValueException e1) {
                        System.err.println(e1.getMessage());
                        scan.nextLine();
                    }
                }
        }

        return input;
    }

    public static int[] getDimensions(){
        if(getBoolean("Do you want to use default dimensions 225x80 (true/false) : ")) return new int[] {225, 80};

        int[] res = new int[2];

        for(int i = 0; i < res.length; i++){
            boolean validValue = false;
            int input;

            while(!validValue){
                try {
                    if(i == 0) System.out.print("Input length : ");
                    if(i == 1) System.out.print("Input width : ");

                    input = scan.nextInt();
                    if(input < 0) throw new InvalidValueException("Input must be a positive integer");

                    validValue = true;
                    res[i] = input;
                } catch (Exception e) {
                    try {
                        throw new InvalidValueException("Input must be a positive integer");
                    } catch (InvalidValueException e1) {
                        System.err.println(e1.getMessage());
                        scan.nextLine();
                    }
                }
            }
        }
        
        return res;
    }

    public static int[] getProbas(){
        if(getBoolean("Do you want to use default percentages 20% Algae 30% Fish 10% Shark (true/false) : ")) return new int[] {20, 30, 10};

        int[] res = new int[3];

        for(int i = 0; i < res.length; i++){
            boolean validValue = false;
            int input;

            while(!validValue){
                try {
                    if(i == 0) System.out.print("Input Algae probability : ");
                    if(i == 1) System.out.print("Input Fish probability : ");
                    if(i == 2) System.out.print("Input Shark probability : ");

                    input = scan.nextInt();
                    if(input < 0 || input > 100) throw new InvalidValueException("Input must be an integer between 0 and 100");

                    validValue = true;
                    res[i] = input;
                } catch (Exception e) {
                    try {
                        throw new InvalidValueException("Input must be an integer between 0 and 100");
                    } catch (InvalidValueException e1) {
                        System.err.println(e1.getMessage());
                        scan.nextLine();
                    }
                }
            }
        }
        
        return res;
    }
}
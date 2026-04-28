public class Main {
    public static void main(String[] args) throws InterruptedException{

        while(true){
            Grid.clear();
            Grid grid = new Grid(10, 0.5);
            grid.print();
            Thread.sleep(500);
        }
    }
}

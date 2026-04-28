public class Main {
    public static void main(String[] args) throws InterruptedException{
        Grid grid = new Grid(62, 157, 0.2);
        Grid.clear();

        while(true){
            grid.compute();
            System.out.print(String.format("%c[%d;%df",0x1b,0,0));
            System.out.print(grid);
            Thread.sleep(33);
        }
        
    }
}

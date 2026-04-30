public class Grid {
    protected int length;
    protected int width;
    protected Object[][] grid;

    private static final String RED = "\u001B[41m";
    private static final String GREEN = "\u001B[42m";
    private static final String BLUE = "\u001B[44m";
    private static final String PURPLE = "\u001B[45m";
    private static final String RESET = "\u001B[0m";
    private static final String CELL = "ㅤ";

    private static final String WATER_CELL = BLUE + CELL + RESET; 
    private static final String ALGAE_CELL = GREEN + CELL + RESET;
    private static final String FISH_CELL = PURPLE + CELL + RESET; 
    private static final String SHARK_CELL = RED + CELL + RESET; 

    public Grid(int length, int width){
        this.length = length;
        this.width = width;

        grid = new Object[length][width];
    }

    public Grid merge(Grid animalGrid){
        Grid res = new Grid(length, width);

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                if(((Animal)(animalGrid.grid[i][j])).isAlive()){
                    res.grid[i][j] = animalGrid.grid[i][j];
                } else if(((Algae)(grid[i][j])).get()){
                    res.grid[i][j] = grid[i][j];
                }
            }
        }

        return res;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                boolean present = false;
                String cellType = WATER_CELL;

                if(grid[i][j] instanceof Algae){
                    present = ((Algae)(grid[i][j])).get();
                    cellType = ALGAE_CELL;
                }

                if(grid[i][j] instanceof Fish){
                    present = ((Fish)(grid[i][j])).isAlive();
                    cellType = FISH_CELL;
                }

                if(grid[i][j] instanceof Shark){
                    present = ((Shark)(grid[i][j])).isAlive();
                    cellType = SHARK_CELL;
                }


                s.append(present ? cellType : WATER_CELL);
            }
            s.append("\n");
        }

        return s.toString();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

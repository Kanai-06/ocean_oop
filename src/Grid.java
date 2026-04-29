public abstract class Grid {
    protected int length;
    protected int width;
    protected Object[][] grid;

    protected static final String RED = "\u001B[41m";
    protected static final String GREEN = "\u001B[42m";
    protected static final String BLUE = "\u001B[44m";
    protected static final String PURPLE = "\u001B[45m";
    protected static final String RESET = "\u001B[0m";
    protected static final String CELL = "ㅤ";

    private static final String WATER_CELL = BLUE + CELL + RESET; 
    private static final String ALGAE_CELL = GREEN + CELL + RESET;
    private static final String FISH_CELL = PURPLE + CELL + RESET; 
    private static final String SHARK_CELL = RED + CELL + RESET; 

    public Grid(int length, int width){
        this.length = length;
        this.width = width;

        grid = new Object[length][width];
    }

    protected int nbNeighors(int x, int y){
        int res = 0;

        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == 0 && j == 0) continue;

                int checkX = x + i;
                int checkY = y + j;
                
                if(checkX > length - 1) checkX = checkX % length;
                if(checkY > width - 1) checkY = checkY % width;

                if(checkX < 0) checkX = length + checkX;
                if(checkY < 0) checkY = width + checkY;

                res += (boolean)(grid[checkX][checkY]) ? 1 : 0;
            }
        }

        return res;
    }

    protected abstract Object computeCell(int x, int y);

    public abstract void compute();

    public String toString(){
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                s.append((boolean)(grid[i][j]) ? ALGAE_CELL : WATER_CELL);
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

public class Grid {
    private boolean[][] grid;
    private final int length;
    private final int width;
    private final double lifeProbability;
    private static final String GREEN = "\u001B[42m";
    private static final String BLUE = "\u001B[44m";
    private static final String RESET = "\u001B[0m";
    private static final String CELL = "ㅤ";
    private static final String DEAD_CELL = BLUE + CELL + RESET; 
    private static final String LIVE_CELL = GREEN + CELL + RESET;   

    public Grid(int length, int width, double lifeProbability){
        this.length = length;
        this.width = width;
        this.lifeProbability = lifeProbability;

        grid = new boolean[length][width];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                grid[i][j] = Math.random() < lifeProbability;
            }
        }
    }

    private int nbNeighors(int x, int y){
        int res = 0;

        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(x + i < 0 || x + i > length - 1 || y + j < 0 || y + j > width - 1 || (i == 0 && j == 0)){
                    res += Math.random() < lifeProbability ? 1 : 0;
                    continue;
                }

                res += grid[x + i][y + j] ? 1 : 0;
            }
        }

        return res;
    }

    private boolean computeCell(int x, int y){
        int nbNeighors = nbNeighors(x, y);

        if(grid[x][y] && (nbNeighors < 2 || nbNeighors > 3)) return false;
        if((!grid[x][y] && nbNeighors == 3) || (grid[x][y] && (nbNeighors == 2 || nbNeighors == 3))) return true;
        return false;
    }

    public void compute(){
        boolean[][] nextGeneration = new boolean[length][width];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                nextGeneration[i][j] = computeCell(i, j);
            }
        }

        grid = nextGeneration;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                s.append(grid[i][j] ? LIVE_CELL : DEAD_CELL);
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

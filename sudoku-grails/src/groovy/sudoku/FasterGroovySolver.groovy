package sudoku


/**
 * Groovified version of http://www.colloquial.com/games/sudoku/java_sudoku.html,
 * with some speed improvements over the groovy version.
 */
class FasterGroovySolver {
    int[][] grid;
    long legalCount  = 0;

    Grid solve(Grid grid) {
        this.grid = grid.getGrid();
        if (solve(0,0)) {
            return new Grid(grid.getGrid());
        } else {
            return null;
        }
    }
    
    private boolean solve(int i, int j) {
        if (grid[i][j]) {
            return solveNext(i,j)
        }
        boolean hasSolution = findFirstLegal(i,j)
        if (!hasSolution) {
            grid[i][j] = 0 // reset on backtrack
        }
        return hasSolution
    }

    private boolean solveNext(int i, int j) {
        if (i==8 && j==8) return true   // a solution is found
        i==8 ? solve(0,j+1) : solve(i+1,j)
    }
    
    private boolean findFirstLegal(int i, int j) {
        def start = grid[i][j] ?: 1
        (start..9).find { val->
            if (legal(i,j,val)) {
                grid[i][j] = val
                return solveNext(i,j)
            } else {
                return false
            }
        }
    }

    boolean legal(int i, int j, int val) {
        legalCount++;
        for (int k = 0; k < 9; ++k)  // row
            if (val == grid[k][j])
                return false;

        for (int k = 0; k < 9; ++k) // col
            if (val == grid[i][k])
                return false;

        int boxRowOffset = (i.intdiv(3))*3;
        int boxColOffset = (j.intdiv(3))*3;
        for (int k = 0; k < 3; ++k) // box
            for (int m = 0; m < 3; ++m)
                if (val == grid[boxRowOffset+k][boxColOffset+m])
                    return false;

        return true; // no violations, so it's legal
    }


}

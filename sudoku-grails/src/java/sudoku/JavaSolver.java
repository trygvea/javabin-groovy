package sudoku;

/**
 * Slightly modified version of http://www.colloquial.com/games/sudoku/java_sudoku.html
 */
public class JavaSolver {
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
    
    boolean solve(int i, int j) {
        if (i == 9) {
            i = 0;
            if (++j == 9)
                return true;
        }
        if (grid[i][j] != 0)  // skip filled cells
            return solve(i+1,j);

        for (int val = 1; val <= 9; ++val) {
            if (legal(i,j,val)) {
                grid[i][j] = val;
                if (solve(i+1,j))
                    return true;
            }
        }
        grid[i][j] = 0; // reset on backtrack
        return false;
    }

    boolean legal(int i, int j, int val) {
        legalCount++;
        for (int k = 0; k < 9; ++k)  // row
            if (val == grid[k][j])
                return false;

        for (int k = 0; k < 9; ++k) // col
            if (val == grid[i][k])
                return false;

        int boxRowOffset = (i / 3)*3;
        int boxColOffset = (j / 3)*3;
        for (int k = 0; k < 3; ++k) // box
            for (int m = 0; m < 3; ++m)
                if (val == grid[boxRowOffset+k][boxColOffset+m])
                    return false;

        return true; // no violations, so it's legal
    }


}
package sudoku


/**
 * Groovified version of http://www.colloquial.com/games/sudoku/java_sudoku.html
 * with 10x speed improvements over the GroovySolver.
 */
class FasterGroovySolver {
    int[][] grid

    Grid solve(Grid grid) {
        this.grid = grid.grid
        solve(0,0) ? new Grid(this.grid) : null
    }

    private boolean solve(int i, int j) {
        grid[i][j] ? solveNext(i,j) : solveThis(i,j)
    }

    private boolean solveNext(int i, int j) {
        if (i==8 && j==8) return true   // a solution is found
        i==8 ? solve(0,j+1) : solve(i+1,j)
    }
    
    private boolean solveThis(int i, int j) {
        boolean hasSolution = findNextLegalValue(i,j)
        if (!hasSolution) {
            grid[i][j] = 0 // reset on backtrack
        }
        hasSolution
    }
 
    private boolean findNextLegalValue(int i, int j) {
        def start = grid[i][j] ?: 1
        for(int val=start; val<=9; val++) { // Speed improvement: replaced .find with a for-loop
            if (legal(i,j,val)) {
                grid[i][j] = val
                if (solveNext(i,j)) {
                    return true
                }
            }
        }
    }

    private boolean legal(int i, int j, int val) {
        legalRow(j, val) && legalCol(i, val) && legalBox(i,j,val) 
    }
    
    private boolean legalRow(int i, int val) {
        for (int k = 0; k < 9; ++k) // Speed improvement: replaced .find with a for-loop
            if (val == grid[k][i])
                return false;
        return true
    }
    
    private boolean legalCol(int i, int val) {
        for (int k = 0; k < 9; ++k) // Speed improvement: replaced .find with a for-loop
            if (val == grid[i][k])
                return false
        return true
    }
    
    private boolean legalBox(int i, int j, int val) {
        def (boxRowOffset, boxColOffset) = boxOffset(i,j)
        for (int k = 0; k < 3; ++k)
            for (int m = 0; m < 3; ++m)
                if (val == grid[boxRowOffset+k][boxColOffset+m])
                    return false
        return true
    }

    private def boxOffset(int i, int j) {
        [((int)i.intdiv(3))*3, ((int)j.intdiv(3))*3]

    }    
}

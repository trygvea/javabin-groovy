package sudoku


/**
 * Groovified version of http://rosettacode.org/wiki/Sudoku#Java
 */
class AlternateFastGroovySolver {
    int[][] grid
    boolean[][] rowSubset = new boolean[9][9] 
    boolean[][] colSubset = new boolean[9][9]
    boolean[][] boxSubset = new boolean[9][9]
    
    Grid solve(Grid grid) {
        this.grid = grid.grid
        initSubsets()
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
        return hasSolution
    }
 
    private boolean findNextLegalValue(int i, int j) {
        def start = grid[i][j] ?: 1
        for(int val=start; val<=9; val++) {
            if (legal(i,j,val)) {
                grid[i][j] = val
                setSubsetValue(i, j, val, true);
                if (solveNext(i,j)) {
                    return val
                } else { 
                    setSubsetValue(i, j, val, false);
                }
            }
        }
    }

    private boolean legal(int i, int j, int val) {
        !rowSubset[i][val-1] && !colSubset[j][val-1] && !boxSubset[getBoxNo(i,j)][val-1]
    }
    
    private void initSubsets() {
        (0..8).each { i ->
            (0..8).each { j ->
                int val = grid[i][j]
                if (val) {
                    setSubsetValue(i, j, val, true)
                }
            }
        }
    }

        
    private void setSubsetValue(int i, int j, int val, boolean present) {
        rowSubset[i][val-1] = present
        colSubset[j][val-1] = present
        boxSubset[getBoxNo(i,j)][val-1] = present
    }    
    
    private int getBoxNo(int i, int j) {
        i.intdiv(3) * 3 + j.intdiv(3)
    }
    
}

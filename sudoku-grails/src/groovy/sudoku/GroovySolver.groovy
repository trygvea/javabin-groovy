package sudoku

/**
 * Groovified version of http://www.colloquial.com/games/sudoku/java_sudoku.html
 */
class GroovySolver {
    int[][] grid

    Board solve(Board board) {
        grid = board.grid
        solve(0,0) ? new Board(grid) : null
    }

    private boolean solve(i, j) {
        if (grid[i][j]) {
            solveNext(i,j) 
        } else { 
            boolean hasSolution = hasSolution(i,j)
            if (!hasSolution) {
                grid[i][j] = 0 // reset on backtrack
            }
            hasSolution
        }
    }

    private boolean solveNext(i, j) {
        if (i==8 && j==8) 
            return true   // a solution is found
        if (i==8) {
            solve(0,j+1) 
        } else { 
            solve(i+1,j)
        }
    }
        
    private boolean hasSolution(i,j) {
        (1..9).any { val->
            if (!legal(i,j,val)) {
                return false
            }
            grid[i][j] = val
            return solveNext(i,j)
        }
    }
 
    private boolean legal(i, j, int val) {
        legalRow(j, val) && legalCol(i, val) && legalBox(i,j,val) 
    }
    
    private boolean legalRow(i, int val) {
        !grid.any { row-> row[i] == val }
    }
    
    private boolean legalCol(i, int val) {
        !grid[i].any { col-> col == val }
    }
    
    private boolean legalBox(i, j, int val) {
        def (boxRowOffset, boxColOffset) = [i.intdiv(3)*3, j.intdiv(3)*3]
        !(0..2).any { row ->
            (0..2).any { col ->
                val == grid[boxRowOffset+row][boxColOffset+col]
            }
        }
    }
    
}

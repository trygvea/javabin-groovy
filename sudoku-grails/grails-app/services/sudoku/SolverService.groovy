package sudoku

/**
 * Slightly modified version of http://www.colloquial.com/games/sudoku/java_sudoku.html
 */
class SolverService {
    
    List solve(List cells) {
        def grid = toGrid(cells)
        solve(0,0,grid) ? toCells(grid) : []
    }
    
    private List<List> toGrid(List cells) {
        (0..8).collect { i ->
            (0..8).collect { j->
                cells[i*9+j]
            }
        }
    }
    
    private List toCells(List<List> grid) {
        (0..80).collect { i ->
           grid[i.intdiv(9)][i % 9]
        }
    }

    private boolean solve(int i, int j, List<List> grid) {
        if (i == 9) {
            i = 0
            if (++j == 9)
                return true
        }
        if (!grid[i][j].allWhitespace)  // skip filled cells
            return solve(i+1,j,grid)

        for (char val = '1'; val <= '9'; ++val) {
            if (legal(i,j,val,grid)) {
                grid[i][j] = val
                if (solve(i+1,j,grid))
                    return true
            }
        }
        grid[i][j] = ' ' // reset on backtrack
        return false
    }

    private boolean legal(int i, int j, char val, List<List> grid) {
        for (int k = 0; k < 9; ++k)  // row
            if (val == grid[k][j])
                return false

        for (int k = 0; k < 9; ++k) // col
            if (val == grid[i][k])
                return false

        int boxRowOffset = ((int)i.intdiv(3))*3;
        int boxColOffset = ((int)j.intdiv(3))*3;
        for (int k = 0; k < 3; ++k) // box
            for (int m = 0; m < 3; ++m)
                if (val == grid[boxRowOffset+k][boxColOffset+m])
                    return false

        return true // no violations, so it's legal
    }

}

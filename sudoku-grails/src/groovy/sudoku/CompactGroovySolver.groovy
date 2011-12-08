package sudoku

/**
 * Heavily modified version of http://groovy.codehaus.org/Solving+Sudoku 
 */
class CompactGroovySolver {
    
    Board solve(Board board) {
        def solution = solve(board.toString())
        solution ? new Board(solution) : null
    }
    
    def isSameFor(i1, i2, Closure c) { 
        c(i1)==c(i2)
    }
        
    def isSameRowOrColumnOrBox(i1, i2) {
        isSameFor(i1,i2) { it.intdiv(9) } ||    // same row 
        isSameFor(i1,i2) { it%9 } ||            // same column
        isSameFor(i1,i2) { it.intdiv(27) } &&   // same box
        isSameFor(i1,i2) { (it%9).intdiv(3) }   
    }

    private List possibleValues(cells, i) {
        def filteredCells = (0..80).collect { i2 ->
            isSameRowOrColumnOrBox(i,i2) ? cells[i2] : ' '
        }
        ('1'..'9')-filteredCells
    }   
         
    private String solve(String cells) {
        def i=cells.indexOf(' ')    // get next unsolved
        if ( i<0 ) return cells     // a solution found !
        possibleValues(cells, i).findResult { // get first solution, if any
            solve(cells[0..<i]+it+cells[i+1..-1])   // try to solve with this value
        }
    }

}

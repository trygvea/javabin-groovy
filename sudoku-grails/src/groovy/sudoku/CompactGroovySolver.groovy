package sudoku

import groovy.lang.Closure;

import java.util.List;

/**
 * Heavily modified version of http://groovy.codehaus.org/Solving+Sudoku 
 */
class CompactGroovySolver {
    
    def solution = new StringBuilder()
    
    Board solve(Board board) {
        solve(board.toString())
        solution ? new Board(solution.toString()) : null 
    }
    
    def isSameFor(i, j, Closure c) { 
        c(i)==c(j)
    }
        
    def isSameRowOrColumnOrBox(i, j) {
        isSameFor(i,j) { it.intdiv(9) } ||    // same row 
        isSameFor(i,j) { it%9 } ||            // same column
        isSameFor(i,j) { it.intdiv(27) } &&   // same box
        isSameFor(i,j) { (it%9).intdiv(3) }   
    }
        
    private def solve(String cells) {
        def i=cells.indexOf(' ');   // get next unsolved
        if ( i<0 ) {
            solution << cells       // a solution found !
        } else {
            def filteredCells = (0..80).collect { j ->
                isSameRowOrColumnOrBox(i,j) ? cells[j] : ' '
            }
            (('1'..'9')-filteredCells).each {           // for all remaining values
                solve(cells[0..<i]+it+cells[i+1..-1])   // try to solve with this value
            }
        }
    }

}

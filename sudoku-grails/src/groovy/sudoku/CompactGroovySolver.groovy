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
    
    private def solve(String s) {
        def isSameFor = {i,j,c->c(i)==c(j)}
        
        def isSameRowOrColumnOrBox = { i,j ->
            isSameFor(i,j) { it.intdiv(9) } ||    // samme rad 
            isSameFor(i,j) { it%9 } ||            // samme kolonne
            isSameFor(i,j) { it.intdiv(27) } &&   // samme boks 
            isSameFor(i,j) { (it%9).intdiv(3) }   
        }
        
        def i=s.indexOf(' ');   // Next unsolved
        if ( i<0 ) {
            solution << s // Solution found
        } else {
            def dependentCells = (0..80).collect {j->
                isSameRowOrColumnOrBox(i,j) ? s[j] : ' ' 
             }
            (('1'..'9')-dependentCells).each {  // for remaining values 
                solve(s[0..<i]+it+s[i+1..-1])   // try to solve with this value 
            }
        }
    }

}

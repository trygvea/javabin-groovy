package sudoku

class SuperCompactGroovySolver {
    Board solve(Board board) {
        def solution = solve(board.toString())
        solution ? new Board(solution) : null
    }
         
    private String solve(String cells) {
        def isSameFor = {i1,i2,c -> c(i1)==c(i2)}
        def i=cells.indexOf(' ')
        if ( i<0 ) return cells
        
        (('1'..'9') - ((0..80).collect { i2 -> 
            isSameFor(i,i2) { it.intdiv(9) } || isSameFor(i,i2) { it%9 } ||
            isSameFor(i,i2) { it.intdiv(27) } &&  isSameFor(i,i2) { (it%9).intdiv(3) } ? cells[i2] : ' '
        })).findResult { solve(cells[0..<i]+it+cells[i+1..-1]) }
    }

}

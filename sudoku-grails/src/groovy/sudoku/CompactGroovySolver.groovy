package sudoku

import groovy.lang.Closure;

import java.util.List;

/**
 * Slightly modified version of http://groovy.codehaus.org/Solving+Sudoku 
 */
class CompactGroovySolver {
    
    def solution = new StringBuilder()
    
    Board solve(Board board) {
        solve(board.toString())
        new Board(solution.toString())
    }
    
//    def r(a){def i=a.indexOf(' ');if(i<0)solution<<a else(('1'..'9')-(0..80).collect{j->
//        def g={(int)it(i)==(int)it(j)};
//        g{it.intdiv(9)}||g{it%9}||g{it.intdiv(27)}&&g{(it%9).intdiv(3)}?a[j]:' '}).each{
//        r(a[0..<i]+it+a[i+1..-1])}}
    
    def solve(String s) {
        def g={i,j,c->c(i) == c(j)}
        
        def i=s.indexOf(' ');
        if ( i<0 ) {
            solution << s 
        } else {
            def x = (0..80).collect{j->
                g(i,j) {
                    it.intdiv(9)
                } || 
                g(i,j) {
                    it%9
                } ||
                g(i,j) {
                    it.intdiv(27)
                } && 
                g(i,j) {
                    (it%9).intdiv(3)
                } ? s[j] : ' ' 
             }
           def y = ('1'..'9')-x
           y.each{
                solve(s[0..<i]+it+s[i+1..-1])
            }
        }
        
    }
}

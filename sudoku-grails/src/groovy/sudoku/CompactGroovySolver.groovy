package sudoku

import groovy.lang.Closure;

import java.util.List;

/**
 * Slightly modified version of http://groovy.codehaus.org/Solving+Sudoku 
 */
class CompactGroovySolver {
    
    def solution = new StringBuilder()
    
    Grid solve(Grid grid) {
        def list = r(grid.toString())
        new Grid(solution.toString())
    }
    
//    def r(a){def i=a.indexOf(' ');if(i<0)solution<<a else(('1'..'9')-(0..80).collect{j->
//        def g={(int)it(i)==(int)it(j)};
//        g{it.intdiv(9)}||g{it%9}||g{it.intdiv(27)}&&g{(it%9).intdiv(3)}?a[j]:' '}).each{
//        r(a[0..<i]+it+a[i+1..-1])}}
    
    def r(a) {
        def g={i,j,c->c(i) == c(j)}
        
        def i=a.indexOf(' ');
        if ( i<0 ) {
            solution << a 
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
                } ? a[j] : ' ' 
             }
           def y = ('1'..'9')-x
           y.each{
                r(a[0..<i]+it+a[i+1..-1])
            }
        }
        
    }
}

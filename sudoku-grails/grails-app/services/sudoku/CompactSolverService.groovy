package sudoku

/**
 * Slightly modified version of http://groovy.codehaus.org/Solving+Sudoku
 * NOTE: doesnt work
 * NOTE: takes forever unless puzzle is nearly solved
 */
class CompactSolverService {
     
    List solve(List cells) {
        r(cells)
    }
//    def r(a){def i=a.indexOf(' ');if(i<0)print a else(('1'..'9')-(0..80).collect{j->
//        def g={(int)it(i)==(int)it(j)};
//        g{it.intdiv(9)}||g{it%9}||g{it.intdiv(27)}&&g{(it%9).intdiv(3)}?a[j]:' '}).each{
//        r(a[0..<i]+it+a[i+1..-1])}}
    
    boolean g(int i, int j, Closure c) {
        c(i) == c(j)
    }

    def r(a) {
        def i=a.indexOf(' ');
        if ( i<0 ) {
            return a 
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

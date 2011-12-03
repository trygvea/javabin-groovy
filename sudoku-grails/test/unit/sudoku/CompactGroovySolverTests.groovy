package sudoku

import grails.test.mixin.TestFor

import org.junit.Test

import sudoku.CompactGroovySolver;
import sudoku.Grid;


class CompactGroovySolverTests {
    
    def solver = new CompactGroovySolver()
    
    @Test
    void "should solve correctly"() {
        def problem =          new Grid('2  37   9  92    7  1  4  2 5    8    8   9    6    4 9  1  5  8    76  4   89  1')
        def expectedSolution = new Grid('284375169639218457571964382152496873348752916796831245967143528813527694425689731')
        
        def solution = solver.solve(problem)
        assert solution == expectedSolution
    }
    
}

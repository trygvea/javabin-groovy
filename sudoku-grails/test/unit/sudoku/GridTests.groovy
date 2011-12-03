package sudoku

import org.junit.Test;

class GridTests {
    
    def problem = "2  37   9  92    7  1  4  2 5    8    8   9    6    4 9  1  5  8    76  4   89  1"
    def problemAsListOfString = problem as List
    def problemAsListOfInteger = problem.collect{it == " " ? 0 : it as Integer}
    
    @Test
    void "should convert grids symmetrically to and from strings"() {
        assert new Grid(problem).toString() == problem
    }
    
    @Test
    void "should convert grids symmetrically to and from lists of integer"() {
        assert new Grid(problemAsListOfInteger).toListOfInteger() == problemAsListOfInteger
    }

    @Test
    void "should convert grids symmetrically to and from lists of string"() {
        assert new Grid(problemAsListOfString).toListOfString() == problemAsListOfString
    }

}

package sudoku

import org.junit.Test


class GroovySolverTests {
    
    def solver = new GroovySolver()
    
    @Test
    void "should solve correctly"() {
        def problem =                   new Board('2  37   9  92    7  1  4  2 5    8    8   9    6    4 9  1  5  8    76  4   89  1')
        assert solver.solve(problem) == new Board('284375169639218457571964382152496873348752916796831245967143528813527694425689731')
    }
 
    @Test
    void "should discover unsolvable problem"() {
        def problem =          new Board('1  37   9  92    7  1  4  2 5    8    8   9    6    4 9  1  5  8    76  4   89  1')
        assert solver.solve(problem) == null 
    }

//    @Test
//    void "should solve real hard sudoku"() {
//        // The following should be one of the hardest sudokus, ref bottom of
//        // http://en.wikipedia.org/wiki/Sudoku_algorithms#Exceptionally_difficult_Sudokus_.28hardest_Sudokus.29
//        def problem =                   new Board('  3      4   8  36  8   1   4  6  73   9          2  5  4 7  686        7  6  5  ')
//        assert solver.solve(problem) == new Board('123456789457189236968327154249561873576938412831742695314275968695814327782693541')
//    }

}

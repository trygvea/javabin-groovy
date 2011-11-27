package sudoku

import grails.test.mixin.TestFor

import org.junit.Test


@TestFor(SolverController)
class SolverControllerTests {

    @Test
    void testSolve() {
        controller.solverService = new SolverService()
        params.cells = '2  37   9  92    7  1  4  2 5    8    8   9    6    4 9  1  5  8    76  4   89  1' as List
        controller.solve( )
        assert model.cells == '284375169639218457571964382152496873348752916796831245967143528813527694425689731' as List
    }
}

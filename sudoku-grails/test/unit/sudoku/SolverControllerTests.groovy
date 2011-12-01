package sudoku

import grails.test.mixin.TestFor

import org.junit.Test


@TestFor(SolverController)
class SolverControllerTests {

    @Test
    void "solve should show solved cells"() {
        def mockedCells = (0..9*9-1).collect {new Random().nextInt(9)+1} // Just a random list of cells
        controller.solverService = [solve:{cells->cells}] as SolverService
        
        params.cells = mockedCells
        controller.solve( )
        assert view == "/solver/show"
        assert model.cells == mockedCells
    }
}

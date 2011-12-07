package sudoku

import grails.test.mixin.TestFor

import org.junit.Test


@TestFor(SolverController)
class SolverControllerTests {

    @Test
    void "should show solved cells"() {
        List<String> mockedCells = (0..80).collect {(new Random().nextInt(9)+1).toString()} // Just a random list of cells of type string
        controller.solverService = [solve:{cells->cells}] as SolverService 
        
        params.cells = mockedCells
        controller.solve( )
        assert view == "/solver/index"
        assert model.cells == mockedCells
    }
}

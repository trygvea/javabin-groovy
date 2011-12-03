package sudoku

class SolverService {
    
    Grid solve(Grid grid) {
        def solver = new FasterGroovySolver()
        solver.solve(grid)
    }

}

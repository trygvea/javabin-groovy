package sudoku

class SolverService {
    
    Grid solve(Grid grid) {
        new FasterGroovySolver().solve(grid)
//        new CompactGroovySolver().solve(grid)
//        new GroovySolver().solve(grid)
//        new JavaSolver().solve(grid)
    }

}

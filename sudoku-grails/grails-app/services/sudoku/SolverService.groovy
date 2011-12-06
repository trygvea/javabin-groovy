package sudoku

class SolverService {
    
    Board solve(Board board) {
        new FasterGroovySolver().solve(board)
//        new CompactGroovySolver().solve(board)
//        new GroovySolver().solve(board)
//        new JavaSolver().solve(board)
    }

}

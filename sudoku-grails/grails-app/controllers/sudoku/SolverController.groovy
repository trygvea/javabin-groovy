package sudoku

class SolverController {
    def solverService
    
    def index() { 
        [cells:randomProblem]
    }
    
    def solve() {
        if (!validate(params)) {
            flash.error = "Noen ruter inneholder bokstaver eller symboler"
            return render(view: "index", model: [cells:params.cells])
        } 
        
        def solution = solverService.solve(new Grid(params.cells))
        if (!solution) { 
            flash.message = "Oppgaven er uløselig"
        }
        render(view: "index", model: [cells:solution?.toListOfString() ?: params.cells])
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private boolean validate(params) {
        flash.clear()
        !params.cells.any { 
            !(it.integer || it.allWhitespace)
        }
    }
    
    private getRandomProblem() {
        problems[new Random().nextInt(problems.size())]
    }
    
    private problems = [
        "   1    5  7 48  2 2 9    7 3   29  5       4  68   1 8    1 4 6  28 5  1    4   ",
        " 6  3     459   28  8   73     9  5 9  8 6  7 8  5     36   9  42   938     2  1 ",
        "2  37   9  92    7  1  4  2 5    8    8   9    6    4 9  1  5  8    76  4   89  1",
        "18    4     8       9 345   4 96    52  8  76    53 1   251 7       2     7    92",
        "  1  74      2  966  3      57  89  93     51  69  27      6  582  7      52  8  ",
        " 7  4  63  2  9 4 5     8      7 3  9  8 6  7  8 5      7     2 1 4  7  69  2  3 ",
        "  73  2  3       18  62     734    5         5    849     67  42       6  9  43  ",
        "   897     9     1  6 1  9  3     2    574    1     6  8  2 7  5     9     763   ",
        "5   1 9  73 2    5    6  7    5  8  8       3  4  7    1  8    2    1 69  6 7   4",
        "57  9 18  3      4 8    6    24 5               6 95    5    9 3      2  91 3  75",
         "7 5 2   3  35     4  7    6    3 82           62 9    3    8  9     41  1   7 3 2",
         "  14         786 1    5 9   8     23 13   56 95     7   5 4    3 918         73  ",
       //"  3      4   8  36  8   1   4  6  73   9          2  5  4 7  686        7  6  5  "    // One of the hardest sudokus, ref. http://en.wikipedia.org/wiki/Sudoku_algorithms#Exceptionally_difficult_Sudokus_.28hardest_Sudokus.29
      ]
    
}

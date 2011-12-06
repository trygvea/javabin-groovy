package sudoku

import groovy.transform.EqualsAndHashCode;

@EqualsAndHashCode
class Board {
    int [][] grid = new int[9][9]
    
    Board(int[][] array) {
        grid = array
    }
    
    Board(String s) {
        this(s as List)
    }
    
    Board(String[] array) {
        this(array as List)
    }
    
    Board(List list) {
        list.eachWithIndex { val, i ->
            grid[i.intdiv(9)][i%9] = (val instanceof Integer ? val : val.trim() ? val.trim() as Integer : 0 )
        }
    }
    
    List<String> toListOfString() {
        toListOfInteger().collect {it ? it.toString() : " " }
    }

    String toString() {
        toListOfString().join("")
    }
    
    List<Integer> toListOfInteger() {
        def list = []
        grid.each { row ->
            row.each { col ->
                list << col
            }
        }
        list
    }

    private boolean legal(int i, int j, int val) {
        legalRow(j, val) && legalCol(i, val) && legalBox(i,j,val) 
    }
    
    private boolean legalRow(int i, int val) {
        !grid.find { row-> row[i] == val }
    }
    
    private boolean legalCol(int i, int val) {
        !grid[i].find { col-> col == val }
    }
    
    private boolean legalBox(int i, int j, int val) {
        def (boxRowOffset, boxColOffset) = boxOffset(i,j)
        for (int k = 0; k < 3; ++k)
            for (int m = 0; m < 3; ++m)
                if (val == grid[boxRowOffset+k][boxColOffset+m])
                    return false
        return true
    }

    private def boxOffset(int i, int j) {
        [((int)i.intdiv(3))*3, ((int)j.intdiv(3))*3]

    }    

}

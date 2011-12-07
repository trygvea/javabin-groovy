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

}

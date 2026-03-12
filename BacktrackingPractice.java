import java.lang.reflect.Array;

public class BacktrackingPractice {

    public static void printArray (int[] array) {
        for(int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void print2DArray (char[][] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length; j++) {
                System.out.print((char)array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void print2DArray (int[][] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length; j++) {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void backtrackingArray (int[] array, int i) {
        if(i == array.length) {
            return;
        }
        array[i] = i + 1;
        backtrackingArray(array, i+1);
        array[i] -= 2;
    }

    public static void printSubsets (String word, int i, String str) {
        if(i == word.length()) {
            System.out.println(str);
            return;
        }
        printSubsets(word, i+1, str);
        printSubsets(word, i+1, str + (word.charAt(i)));
    }

    public static void permutations (String word, String str) {
        if(word.length() == 0) {
            System.out.println(str);
            return;
        }
        for(int i = 0; i < word.length(); i++ ) {
            String newWord = word.substring(0, i) + word.substring((i+1));
            permutations(newWord, str + word.charAt(i));
        }
    }

    public static boolean isSafeQueens(char[][] board, int row, int col) {
        //vertically up
        for(int i = row-1; i >= 0; i--) {
            if(board[i][col] == 'Q') {
                return false;
            }
        }
        //daig left
        for(int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        //diag right
        for(int i = row-1, j = col+1; i >= 0 && j < board.length; i--,j++) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static boolean isSafeKnight(char[][] board, int row, int col) {
        if(row >= 2 ) {
            if(col >= 1 && board[row-2][col-1] == 'K') {
                return false;
            }
            if(col < board.length-1 && board[row-2][col+1] == 'K'){
                return false;
            }
        }
        
        if(row >= 1) {
            if(col >= 2 && board[row-1][col-2] == 'K') {
                return false;
            }
            if(col < board.length-2 && board[row-1][col+2] == 'K') {
                return false;
            }
        }
        return true;
    }

    public static void nQueens (char[][] board, int row) {
        if(row == board.length) {
            print2DArray(board);
            System.out.println("========================");
            return;
        }
        for(int col = 0; col < board.length; col++) {
            if(isSafeQueens(board, row, col)) {
                board[row][col] = 'Q';
                nQueens(board, row+1);
                board[row][col] = '.';
            }
        }
    }

    public static void nKnights (char[][] board, int row) {
        if(row == board.length) {
            print2DArray(board);
            System.out.println("========================");
            return;
        }
        for(int col = 0; col < board.length; col++) {
            if(isSafeKnight(board, row, col)) {
                board[row][col] = 'K';
                nKnights(board, row+1);
                board[row][col] = '.';
            }
        }
    }

    static int count = 0;
    public static void nQueensCount(char[][] board, int row) {
        if(row == board.length) {
            count++;
            return;
        }
        for(int col = 0; col < board.length; col++) {
            if(isSafeQueens(board, row, col)) {
                board[row][col] = 'Q';
                nQueensCount(board, row+1);
                board[row][col] = '.';
            }
        }
    }

    public static boolean nQueensSingleSolution (char[][] board, int row) {
        if(row == board.length) {
            return true;
        }
        for(int col = 0; col < board.length; col++) {
            if(isSafeQueens(board, row, col)) {
                board[row][col] = 'Q';
                if(nQueensSingleSolution(board, row+1)){
                    return true;
                }
                board[row][col] = '.';
            }
        }
        return false;
    }

    public static int gridWays (int[][] array, int row, int col) {
        int n = array.length; //row
        int m = array[0].length; //col
        if(row == n-1 && col == m-1) {
            return 1;
        }
        else if(row == n || col == m) {
            return 0;
        }
        int w1 = gridWays(array, row + 1, col);
        int w2 = gridWays(array, row, col+1);
        return w1 + w2;
    }
 
    public static int factorial(int n) {
        if(n==0){
            return 1;
        }
        return n * factorial(n-1);
    }

    public static int gridWaysPerm (int[][] maze, int row, int col) {
        int n = maze.length;
        int m = maze[0].length;
        return (factorial(n+m-2)/(factorial(n-1) * factorial(m-1)));
    }

    public static boolean isSafeSuduko(int[][] matrix, int num, int row, int col) {
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][col] == num || matrix[row][i] == num) {
                return false;
            }
        }
        int checkBoxRow = (row/3) * 3;
        int checkBoxCol = (col/3) * 3;
        
        for(int m = checkBoxRow; m < checkBoxRow + 3; m++) {
            for(int n = checkBoxCol; n < checkBoxCol + 3; n++) {
                if(matrix[m][n] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean sudukoSolver (int[][] suduko, int row, int col) {
        if(col == suduko[0].length) {
            col = 0;
            row++;
        }
        if(row == suduko.length) {
            return true;
        }
        if(suduko[row][col] != 0) {
            return sudukoSolver(suduko, row, col + 1);
        }
        for(int num = 1; num <= 9; num++) {
            if(isSafeSuduko(suduko, num, row, col)) {
                suduko[row][col] = num;
                if(sudukoSolver(suduko, row, col+1)) {
                    return true;
                }
                suduko[row][col] = 0;
            }
        }
        return false;
    }

    //poor attempt
    public static int ratMaze (int[][] maze, int row, int col) {
        if(row == maze.length-1 && col == maze[0].length) {
            return 1;
        }
        if(row < 0 || row == maze.length || col < 0 || col == maze[0].length) {
            return 0;
        }
        int w1 = ratMaze(maze, row+1, col);
        int w2 = ratMaze(maze, row, col+1);
        int w3 = ratMaze(maze, row-1, col);
        int w4 = ratMaze(maze, row, col-1);
        return w1 + w2 + w3 + w4;
    }

    public static void RatMazePractice(int[][] maze, int lastMove, int row, int col) {
        //1 down, 2 right, 3 up, 4 left
        if(row == maze.length - 1 && col == maze[0].length-1) {
            maze[row][col] = 2;
            print2DArray(maze);
            System.out.println("============");
            maze[row][col] = 1; // backtracking 
            return;
        }

        if(maze[row][col] == 2) {
            return;
        }
        //down
        if(row < maze.length - 1 && maze[row+1][col] != 0 && lastMove != 3) {
            maze[row][col] = 2;
            RatMaze(maze, 1, row+1, col);
            maze[row][col] = 1; //backtracking
        }
        //right
        if(col < maze[0].length - 1 && maze[row][col+1] != 0 && lastMove != 4) {
            maze[row][col] = 2;
            RatMaze(maze, 2, row, col+1);
            maze[row][col] = 1; //backtracking
        }
        //up
        if(row > 0 && maze[row-1][col] != 0 && lastMove != 1) {
            maze[row][col] = 2;
            RatMaze(maze, 3, row-1, col);
            maze[row][col] = 1; //backtracking
        }
        //left
        if(col > 0 && maze[row][col-1] != 0 && lastMove != 2) {
            maze[row][col] = 2;
            RatMaze(maze, 4, row, col-1);
            maze[row][col] = 1; //backtracking
        }
    }

    public static void RatMaze(int[][] maze, int lastMove, int row, int col) {
        //1 down, 2 right, 3 up, 4 left
        if(row == maze.length - 1 && col == maze[0].length-1) {
            maze[row][col] = 2;
            print2DArray(maze);
            System.out.println("============");
            maze[row][col] = 1; // backtracking 
            return;
        }

        if(maze[row][col] != 1) {
            return;
        }
        maze[row][col] = 2;
        //down
        if(row < maze.length - 1) {
            RatMaze(maze, 1, row+1, col);
        }
        //right
        if(col < maze[0].length - 1) {
            RatMaze(maze, 2, row, col+1);
        }
        //up
        if(row > 0) {
            RatMaze(maze, 3, row-1, col);
        }
        //left
        if(col > 0) {
        RatMaze(maze, 4, row, col-1);
        }
        maze[row][col] = 1; //backtracking
    }

    static String[] phoneNumbers = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public static void allPhoneCombinations(String numbers, int i, String str)  {
        if(i == numbers.length()) {
            System.out.println(str);
            return;
        }
        String currString = phoneNumbers[(int)(numbers.charAt(i) - '2')];
        for(int j = 0; j < currString.length(); j++) {
            char currentChar = currString.charAt(j);
            allPhoneCombinations(numbers, i+1, str + currentChar);
        }
    }
    public static void knightsTour (int[][] board, int row, int col, int count1) {
        if(board[row][col] != 0) {
            return;
        }
        if(count1 >= 64) {
            print2DArray(board);
            System.out.println("=========");
            board[row][col] = 0;
            return;

        }
        board[row][col] = count1;
        //rows and colum conditions
        if(row > 2) {
            if(col > 1) {
                knightsTour(board, row-2, col-1, count1+1);
            }
            if(col < board.length - 1) {
                knightsTour(board, row-2, col+1, count1+1);
            }
        } 
        if(row > 1) {
            if(col > 2) {
                knightsTour(board, row-1, col-2, count1+1);
            }
            if(col < board.length - 2) {
                knightsTour(board, row-1, col+2, count1+1);
            }
        }
        if(row < board.length - 1) {
            if(col > 2) {
                knightsTour(board, row+1, col-2, count1+1);
            }
            if(col < board.length - 2) {
                knightsTour(board, row+1, col+2, count1+1);
            }
        }
        if(row < board.length - 2) {
            if(col > 1) {
                knightsTour(board, row+2, col-1, count1+1);
            }
            if(col < board.length - 1) {
                knightsTour(board, row+2, col+1, count1+1);
            }
        }
        board[row][col] = 0; //BACKTRACKING
    }

    //new day

    public static boolean nQueens1 (char[][] board,int row) {
        if(row == board.length) {
            print2DArray(board);
            System.out.println("==========");
            return true;
        }
        for(int i = 0; i < board[row].length; i++) {
            if(isSafeQ(board, row, i)) {
                board[row][i] = 'Q';
                if(nQueens1(board, row+1)){
                    return true;
                }
                board[row][i] = '.';
            }
        }
        return false;
    }

    public static boolean isSafeQ (char[][] board, int row, int col) {
        for(int i = 0; i < row; i++) {
            if(board[i][col] == 'Q') {
                return false;
            }
        }
        for(int i = row, j = col; i >= 0 && j >= 0; i--,j--) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        for(int i = row, j = col; i >= 0 && j < board[0].length; i--,j++) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static boolean KNightsTour (int board[][], int row, int col, int[] x, int [] y, int count) {

        board[row][col] = count;
        
        if(count == board.length * board[0].length) {
            print2DArray(board);
            return true;
        }

        for(int i = 0; i < 8; i++) {
            int newrow = row + y[i];
            int newcol = col + x[i];
            if(isSafeK(board, newrow, newcol)) {
                if(KNightsTour(board, newrow, newcol, x, y, count+1)) {
                    return true;
                }
            }
        }

        board[row][col] = 0; //backtracking
        return false;
    }

    public static boolean isSafeK (int[][] board, int row,int col) {
        return (row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] == 0);
    }

    public static boolean KNightsTour1 (int board[][], int row, int col, int[] x, int [] y, int count) {

        board[row][col] = count;
        
        if(count == board.length * board[0].length) {
            print2DArray(board);
            return true;
        }

        int[] bestMove = bestMove(board, row, col, x, y);
        if(KNightsTour1(board, bestMove[0], bestMove[1], x, y, count+1)) {
            return true;
        }
        board[row][col] = 0; //backtracking
        return false;
    }

    public static int[] bestMove (int board[][], int row, int col, int[] x, int [] y) {
        int minMoves = 9;
        int[] result = {-1,-1};
        for(int i = 0; i < 8; i++) {
            int newrow = row + y[i];
            int newcol = col + x[i];
            //total moves
            if(isSafeK(board, newrow, newcol)) {
                int totalMoves = totalMoves(board, newrow, newcol, x, y);
                if(minMoves > totalMoves) {
                    minMoves = totalMoves;
                    result[0] = newrow;
                    result[1] = newcol;
                }
            }
            
        }
        return result;
    }

    public static int totalMoves(int board[][], int row, int col, int[] x, int [] y) {
        int count = 0;
        for(int i = 0; i < 8; i++) {
            int newrow = row + y[i];
            int newcol = col + x[i];
            if(isSafeK(board, newrow, newcol)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int[] x = {1, -1, 2, -2, 2, -2, 1, -1};
        int[] y = {2, 2, 1, 1, -1, -1, -2, -2};
        int[][] board = new int[8][8];
        KNightsTour1(board, 0, 0, x, y, 1);
    }
}
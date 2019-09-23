public class Sudoku {
	private int rank;
    private int[][] matrix;
 
    public Sudoku(int[][] matrix, int rank) {
        this.matrix = matrix;
        this.rank = rank;
    }
 
    public static void main(String[] args) {
        int[][] sudoku = {
        		{0,2,0,0},
        		{0,0,0,4},
        		{2,0,0,0},
        		{0,0,3,0},
                };
        Sudoku s = new Sudoku(sudoku, 4);
        s.backTrace(0, 0);
    }
 
    /**
     * 回溯算法
     *
     * @param i 行号
     * @param j 列号
     */
    public void backTrace(int i, int j) {
        if (i == rank-1 && j == rank) {
            //已经成功了，打印数组即可
            System.out.println("获取正确解");
            printArray();
            return;
        }
 
        //已经到了列末尾了，还没到行尾，就换行
        if (j == rank) {
            i++;
            j = 0;
        }
 
        //如果i行j列是空格，那么才进入给空格填值的逻辑
        if (matrix[i][j] == 0) {
            for (int k = 1; k <= rank; k++) {
                //判断给i行j列放1-9中的任意一个数是否能满足规则
                if (check(i, j, k)) {
                    //将该值赋给该空格，然后进入下一个空格
                    matrix[i][j] = k;
                    backTrace(i, j + 1);
                    //初始化该空格
                    matrix[i][j] = 0;
                }
            }
        } else {
            //如果该位置已经有值了，就进入下一个空格进行计算
            backTrace(i, j + 1);
        }
    }
 
    /**
     * 判断给某行某列赋值是否符合规则
     *
     * @param row    被赋值的行号
     * @param line   被赋值的列号
     * @param number 赋的值
     * @return
     */
    public boolean check(int row, int line, int number) {
        //判断该行该列是否有重复数字
        for (int i = 0; i < rank; i++) {
            if (matrix[row][i] == number || matrix[i][line] == number) {
                return false;
            }
        }

        
        //当盘面为4，6，8，9时需要对小宫格进行判断
        int tempRow = 0;
        int tempLine = 0;
        switch(rank) {
        case 4:
        	tempRow = row / 2;
        	tempLine = line / 2;
        	for(int i = 0; i < 2; i++) {
        		for(int j = 0; j < 2; j++) {
        			if(matrix[tempRow * 2 + i][tempLine * 2 + j] == number) {
        				return false;
        			}
        		}
        	}
        	break;
        case 6:
        	tempRow = row / 2;
        	tempLine = line / 3;
        	for(int i = 0; i < 2; i++) {
        		for(int j = 0; j < 3; j++) {
        			if(matrix[tempRow * 2 + i][tempLine * 3 + j] == number) {
        				return false;
        			}
        		}
        	}
        	break;
        case 8:
        	tempRow = row / 4;
        	tempLine = line / 2;
        	for(int i = 0; i < 4; i++) {
        		for(int j = 0; j < 2; j++) {
        			if(matrix[tempRow * 4 + i][tempLine * 2 + j] == number) {
        				return false;
        			}
        		}
        	}
        	break;
        case 9:
        	tempRow = row / 3;
            tempLine = line / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[tempRow * 3 + i][tempLine * 3 + j] == number) {
                        return false;
                    }
                }
            }
        	break;
        default:
        	break;
        } 
        return true;
    }
 
    /**
     * 打印矩阵
     */
    public void printArray() {
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

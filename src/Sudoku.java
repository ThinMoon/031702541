import java.util.LinkedList;

public class Sudoku {
	private int rank;
    private int[][] matrix;
 
    public Sudoku(int[][] matrix, int rank) {
        this.matrix = matrix;
        this.rank = rank;
    }
 
    public static void main(String[] args) {
    	
    	int rank = 0;
    	int suCnt = 0;
    	String inputFilename = "";
    	String outputFilename = "";
    	if(args.length > 0 && args != null) {
    		for(int i = 0; i < args.length; i++) {
    			switch(args[i]) {
					case "-m":
						rank = Integer.valueOf(args[++i]);
						break;
					case "-n":
						suCnt = Integer.valueOf(args[++i]);
						break;
					case "-i":
						inputFilename = args[++i];
						break;
					case "-o":
						outputFilename = args[++i];
				}
    			
    		}
    			
    	}else {
    		System.out.println("未输入参数！");
    		System.exit(1);
    	}
    	
    	
    	
    	
    	TxtIO txtIO = new TxtIO();
    	int[] array = txtIO.readFile(inputFilename);
    	LinkedList<int[][]> sudokus = txtIO.splitArray(array, rank, suCnt);
    	LinkedList<int[][]> outSudokus = new LinkedList<int[][]>();

    	
    	for(int i = 0; i < sudokus.size(); i++) {
		    Sudoku s = new Sudoku(sudokus.get(i), rank);
		    s.backTrace(0, 0, outSudokus);
    	}
    	
    	txtIO.writeFile(outputFilename, outSudokus, rank);
    	
    	
    }
    
    
 
    /**
     * 回溯算法
     *
     * @param i 行号
     * @param j 列号
     */
    public void backTrace(int i, int j, LinkedList<int[][]> outSudokus) {
        if (i == rank-1 && j == rank) {
        	int[][] array = new int[9][9];
        	for(int ai = 0; ai < rank; ai++) {
        		for(int aj = 0; aj < rank; aj++) {
        			array[ai][aj] = matrix[ai][aj];
        		}
        	}
        	outSudokus.add(array);
        	
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
                    backTrace(i, j + 1, outSudokus);
                    //初始化该空格
                    matrix[i][j] = 0;
                    
                }
            }
        } else {
            //如果该位置已经有值了，就进入下一个空格进行计算
            backTrace(i, j + 1, outSudokus);
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
    
    
    
    
 
}

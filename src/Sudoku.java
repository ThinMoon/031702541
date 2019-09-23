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
    		System.out.println("δ���������");
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
     * �����㷨
     *
     * @param i �к�
     * @param j �к�
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
 
        //�Ѿ�������ĩβ�ˣ���û����β���ͻ���
        if (j == rank) {
            i++;
            j = 0;
        }
 
        //���i��j���ǿո���ô�Ž�����ո���ֵ���߼�
        if (matrix[i][j] == 0) {
            for (int k = 1; k <= rank; k++) {
                //�жϸ�i��j�з�1-9�е�����һ�����Ƿ����������
                if (check(i, j, k)) {
                    //����ֵ�����ÿո�Ȼ�������һ���ո�
                    matrix[i][j] = k;
                    backTrace(i, j + 1, outSudokus);
                    //��ʼ���ÿո�
                    matrix[i][j] = 0;
                    
                }
            }
        } else {
            //�����λ���Ѿ���ֵ�ˣ��ͽ�����һ���ո���м���
            backTrace(i, j + 1, outSudokus);
        }
    }
 
    /**
     * �жϸ�ĳ��ĳ�и�ֵ�Ƿ���Ϲ���
     *
     * @param row    ����ֵ���к�
     * @param line   ����ֵ���к�
     * @param number ����ֵ
     * @return
     */
    public boolean check(int row, int line, int number) {
        //�жϸ��и����Ƿ����ظ�����
        for (int i = 0; i < rank; i++) {
            if (matrix[row][i] == number || matrix[i][line] == number) {
                return false;
            }
        }

        
        //������Ϊ4��6��8��9ʱ��Ҫ��С��������ж�
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

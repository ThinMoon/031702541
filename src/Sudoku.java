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
     * �����㷨
     *
     * @param i �к�
     * @param j �к�
     */
    public void backTrace(int i, int j) {
        if (i == rank-1 && j == rank) {
            //�Ѿ��ɹ��ˣ���ӡ���鼴��
            System.out.println("��ȡ��ȷ��");
            printArray();
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
                    backTrace(i, j + 1);
                    //��ʼ���ÿո�
                    matrix[i][j] = 0;
                }
            }
        } else {
            //�����λ���Ѿ���ֵ�ˣ��ͽ�����һ���ո���м���
            backTrace(i, j + 1);
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
 
    /**
     * ��ӡ����
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

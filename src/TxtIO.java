import java.io.*;
import java.util.LinkedList;

public class TxtIO {
    /**
     * 读入TXT文件
     * 返回一个整型数组包含里面所有数字
     */
    public int[] readFile( String pathName) {
    	int[] array = new int[5000];
    	int count = 0;
    	
        try {
        	FileReader reader = new FileReader(pathName);
            BufferedReader br = new BufferedReader(reader);
            int number = 0;
   
            while ((number = br.read()) != -1) {
            	if((number-48) >= 0) {
            		array[count++] = number - 48;
            	}
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    /**
     * 写入TXT文件
     * 
     */
    public void writeFile(String pathName, LinkedList<int[][]> sudokus, int rank) {
        
    	try {
            File writeName = new File(pathName); 
            writeName.createNewFile(); 
            FileWriter writer = new FileWriter(writeName);
		    BufferedWriter out = new BufferedWriter(writer);
		    
		    for(int k = 0; k < sudokus.size(); k++) {
		    	int[][] array = new int[9][9];
		    	array = sudokus.get(k);
		    	for(int i = 0; i < rank; i++) {
		    		for(int j = 0; j < rank; j++) {
		    			out.write(array[i][j] + 48);
		    			out.write(" ");
		    		}
		    		out.write("\n");
		    	}
		    	out.write("\n");
		    }
		    
		    
		    
		    
		    
		    
		    out.close();
        } catch (IOException e) {
            e.printStackTrace();
	    }
    }
    
    /**
     * 将整型readerFile读出的数组分解为不同盘面
     * 用LinkedList进行存储二位数组
     * 返回一个LinkedList
     * @param suCnt面数
     * @param rank阶数
     */
    public LinkedList<int[][]> splitArray(int[] array, int rank, int suCnt) {
    	LinkedList<int[][]> sudokus = new LinkedList<int[][]>();
    	int count = 0;
    	for(int k = 0; k < suCnt; k++) {
    		int[][] temp = new int[9][9];
	    	for(int i = 0; i < rank; i++) {
	    		for(int j = 0; j < rank; j++) {
	    			temp[i][j] = array[count++];
	    		}
	    	}
	    	sudokus.add(temp);
    	}
    	
    	return sudokus;
    }
    
    
    public void printArray(int[][] array, int rank) {
    	for(int i = 0; i < rank; i++) {
    		for(int j = 0; j < rank; j++) {
    			System.out.print(array[i][j]);
    		}
    		System.out.print("\n");
    	}
    }
    
    
    
    
    
    
    
}

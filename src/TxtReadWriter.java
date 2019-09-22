
import java.io.*;

public class TxtReadWriter {

    public static void main(String args[]) {
        readFile("input.txt");
        writeFile("output.txt");
    }

    /**
     * ����TXT�ļ�
     */
    public static void readFile( String pathName) {
        // ����·�������·�������ԣ�д���ļ�ʱ��ʾ���·��,��ȡ����·����input.txt�ļ�
        //��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw;
        //���ر��ļ��ᵼ����Դ��й¶����д�ļ���ͬ��
        //Java7��try-with-resources�������Źر��ļ����쳣ʱ�Զ��ر��ļ�����ϸ���https://stackoverflow.com/a/12665271
        try {
        	FileReader reader = new FileReader(pathName);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                // һ�ζ���һ������
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * д��TXT�ļ�
     */
    public static void writeFile(String pathName) {
        try {
            File writeName = new File(pathName); // ���·�������û����Ҫ����һ���µ�output.txt�ļ�
            writeName.createNewFile(); // �������ļ�,��ͬ�����ļ��Ļ�ֱ�Ӹ���
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write("�һ�д���ļ���1\r\n");
                out.write("�һ�д���ļ���2\r\n");
                out.flush(); // �ѻ���������ѹ���ļ�
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

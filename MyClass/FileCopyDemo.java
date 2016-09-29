import java.io.*;

public class FileCopyDemo {
	public final static String path = "C:\\Java-Project\\";
	public final static String fileName = "Source.txt";
	public final static String destFileName = "copiedfile.txt";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
//		运用字节流复制文件Source.txt到byte copiedfile.txt
		FileInputStream fis = new FileInputStream(path+fileName);
		FileOutputStream fos = new FileOutputStream(path+"byte "+destFileName);
		byte[] b = new byte[1024*1024];
		int n;
		while((n = fis.read(b)) != -1){
			fos.write(b,0,n);
		}
		fis.close();
		fos.close();
		
//		运用字符流复制文件Source.txt到writer copiedfile.txt
		FileReader fr = new FileReader(path+fileName);
		FileWriter fw = new FileWriter(path+"writer "+destFileName);
		int len = 0;
		char[] c = new char[1024*1024];
		while ((len=fr.read(c)) != -1){
			for(int i=0;i<len;i++){
				System.out.println(c[i]);
			}
			fw.write(c,0,len);
			fw.flush();
		}
		fr.close();
		fw.close();
	}

}

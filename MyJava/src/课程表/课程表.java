package 课程表;

import java.io.*;

public class 课程表 {
	static String subSheet[][] = new String[7][11];
	static String classInfo[][][]=new String[7][11][8];

	public static void main(String[] args) {
		FileInputStream kcb;
		try {
			kcb = new FileInputStream("C:\\Users\\Zheng\\Desktop\\grkb1.asp");
			InputStreamReader isr = new InputStreamReader(kcb);
			BufferedReader br = new BufferedReader(isr);
			String data = null;
			int classNo = 1, dayNo = 1;
			while ((data = br.readLine()) != null) {
				if (data.contains("<TD>")) {
					String[] kc = data.split("<TD>|</TD>");
					if (kc.length > 1) {
						System.out.println(classNo + " " + dayNo + " " + kc[1]);
						subSheet[dayNo-1][classNo-1] = kc[1];
					} else {
						System.out.println(classNo + " " + dayNo + " " + "Empty.");
						subSheet[dayNo-1][classNo-1] = "Empty.";
					}
					dayNo++;
					if (dayNo > 7) {
						dayNo = 1;
						classNo++;
					}
					if (classNo > 11)
							break;
				}
			}
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 1; i < 8; i++) {
			for (int j = 1; j < 12; j++) {
				classInfo[i-1][j-1][0]="星期："+i;
				classInfo[i-1][j-1][1]="课程序号："+j;
				String tmp[] = subSheet[i-1][j-1].split("\\[|\\ \\(|\\)\\]\\/");
				for(int k=0;k<tmp.length&&k<6;k++){
					classInfo[i-1][j-1][k+2]=tmp[k];
				}
				for(int a=0;a<8;a++){
					if(classInfo[i-1][j-1][a]!=null)
					System.out.println(classInfo[i-1][j-1][a]);
				}
				System.out.println("--------------------");
			}
		}
	}

}

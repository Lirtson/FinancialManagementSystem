package 个人财务管理系统;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//从文件中读数据的类
public class getDataFromTxt {
	public getDataFromTxt() {
	}
	public void getData(ArrayList<oneAccount> list) {
		FileReader fr;
		oneAccount a;
		String [] words=new String[5];
		String linetext=null;
		try {
			fr = new FileReader("MyData.txt");
			BufferedReader br=new BufferedReader(fr);
			while((linetext=br.readLine())!=null) {
				words=linetext.split(",");
				a=new oneAccount(Integer.parseInt(words[0]),Integer.parseInt(words[1]),words[2],words[3],Double.parseDouble(words[4]));
				list.add(a);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

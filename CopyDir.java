import java.util.*;
import java.io.*;
/*
* Copy one dir to another dir
*/
public class CopyDir{
	public static void main(String[] args) throws IOException{
		File source = new File("");
		
		File object = new File("");
		recursiveCopy(source,object);

	}
	
	public static void recursiveCopy(File path,File obj) throws IOException{
		File[] dir = path.listFiles();
		if(!(obj.exists())){
			File objNd = new File(obj.toString());
			objNd.mkdir();
			obj = objNd;
		}
		for(File file:dir){
			String[] fl = file.toString().split("/");
			String fileName = fl[fl.length-1];
			if(file.isFile()){
				System.out.println("File:"+file.toString());
				File objF = new File(obj,fileName);
				String objpath = objF.toString();
				objF.createNewFile();
				System.out.println(objpath);
				copyFile(file.toString(),objF.toString());
			}else if(file.isDirectory()){
				System.out.println("Dir:"+file.toString());
				File objD = new File(obj,fileName);
				objD.mkdir();
				recursiveCopy(file,objD);
				String objpath = objD.toString();
				System.out.println(objpath);
			}else{
				
			}
		}
		return ;
	}

	public static void copyFile(String source,String object) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(source));
		BufferedWriter bw = new BufferedWriter(new FileWriter(object));
		String str;
		while((str=br.readLine())!=null){
			bw.write(str);
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	public static void catFile(String source) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(source));
		
		String str;
		while((str=br.readLine())!=null){
			System.out.println(str);
		}
		br.close();
		
	}
	public static void recursiveRead(File path) throws IOException{
		File[] dir = path.listFiles();
		for(File file:dir){
			if(file.isFile()){
				System.out.println("File:"+file.toString());
			}else if(file.isDirectory()){
				System.out.println("Dir:"+file.toString());
				recursiveRead(file);
			}else{
			}
		}
		return;
	}
}
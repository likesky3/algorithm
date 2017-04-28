package weiss.chap1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P04ExpandIncludes {

	public void expandIncludes(String dir, String in, String out) {
//		System.out.printf("in=%s, out=%s\n", in, out);
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dir + in)));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir + out)));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] items = line.split("#include ");
				if (items.length == 2) {
					String tmpOut = out + "~";
					expandIncludes(dir, items[1].trim(), tmpOut);
					insertContent(writer, dir + tmpOut);
				} else { 
					writer.write(line);
					writer.write("\n");
				}
			}
//			writer.flush();
			reader.close();
			writer.close();
			
//			reader = new BufferedReader(new InputStreamReader(new FileInputStream(dir + out)));
//			while ((line = reader.readLine()) != null) {
//				System.out.println(line);
//			}
//			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void insertContent(BufferedWriter writer, String file) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.write('\n');
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		P04ExpandIncludes obj = new P04ExpandIncludes();
		obj.expandIncludes("/Users/linlinzhou/Downloads/", "source1.c",  "source1_expandincludes.c");
	}

}

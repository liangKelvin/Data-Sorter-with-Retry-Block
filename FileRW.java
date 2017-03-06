import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.NoSuchFileException;
import java.nio.file.Files;


public class FileRW {

	public static void writeFile(String filename, ArrayList<Integer> values) {

		try{
		    PrintWriter writer = new PrintWriter(filename, "UTF-8");
		    
		    for(int value : values) {
		    	writer.println(String.valueOf(value));
		    }
		    
		    writer.close();
		} catch (IOException e) {
	   		System.out.println("error writing to file");
		}

	}


	public static ArrayList<Integer> readFile(String filename) {

		ArrayList<Integer> values = new ArrayList<Integer>();
		try {
			Scanner scanner = new Scanner(new File(filename));
			int i = 0;
			while(scanner.hasNextInt()) {
				values.add(scanner.nextInt());
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		return values;
	}

	public static Boolean fileExists(String filename) {
		try{
			Scanner scanner = new Scanner(new File(filename));
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	public static void deleteFile(String filename) {
		File f = new File(filename);
        if (f.exists())
            f.delete();
	}
}
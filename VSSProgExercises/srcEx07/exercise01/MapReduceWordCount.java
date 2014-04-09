package exercise01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MapReduceWordCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			map("srcEx07/exercise01/Words.txt");
		} catch (IOException anEx) {
			// TODO Auto-generated catch block
			anEx.printStackTrace();
		}
	}
	/*
	 * This method is meant to read one or more text files and split
	 * them into "blocks". In the exercise it's recommended to use a
	 * "Tokenizer", however according to stackoverflow tokenizer is an
	 * outdated legacy and only left in the JDK because of compatibility.
	 * It is recommended to use .split instead.
	 * http://stackoverflow.com/questions/2356251/string-tokenizer-in-java
	 */
	public static void map(String filepath) throws IOException{
		File f = new File(filepath);
		String input = new String(Files.readAllBytes(f.toPath()));
		String[] inputArray = input.split(" ");
	}
	
	public static void shuffle(){
		
	}
	
	public static void reduce(){
		
	}
}

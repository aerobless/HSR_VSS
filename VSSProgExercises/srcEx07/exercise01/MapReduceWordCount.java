package exercise01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class MapReduceWordCount {

	/*
	 * The idea of this exercise is to build a model for the map reduce
	 * pattern by creating a wordCount class. There are more efficient & easier
	 * ways to count words (for example by simply using a hashmap). So don't
	 * use this code if your intention is to simply count words and you 
	 * found this in a github search.
	 * 
	 * Map-Reduce:
	 * http://www.rabidgremlin.com/data20/MapReduceWordCountOverview1.png
	 */
	public static void main(String[] args) {
		try {
			map("srcEx07/exercise01/Words.txt");
		} catch (IOException anEx) {
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
		//Input:
		File f = new File(filepath);
		String input = new String(Files.readAllBytes(f.toPath()));
		
		//Splitting:
		String[] blockSplitters = input.split(".");
		
		//Mapping:
		ArrayList<String[]> blockSplitterShards = new ArrayList<String[]>();
		for(String splitter : blockSplitters){
			blockSplitterShards.add(splitter.split(" "));
		}
		shuffle(blockSplitterShards);
	}
	
	/*
	 * This exercise is beyond pointless..
	 */
	public static void shuffle(ArrayList<String[]> input){
		for(String[] splitters : input){
			Arrays.sort(splitters);
		}
		reduce(input);
	}
	
	/*
	 * 
	 */
	public static void reduce(ArrayList<String[]> input){
		HashMap<String, Integer> reduceMap = new HashMap<String, Integer>();
		for(String[] splitters : input){
			for(String shard : splitters){
				if(reduceMap.containsKey(shard)){
					reduceMap.put(shard, reduceMap.get(shard)+1);				
				}
				else{
					reduceMap.put(shard, 1);
				}
			}
		}
		for(Entry<String, Integer> out : reduceMap.entrySet()){
			System.out.println(out.getKey());
		}
	}
}

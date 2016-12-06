//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
///**
// * This class takes in an arraylist of flights where each element is a flight and parses the flight for the needed information.
// * 
// * @author veronikaalex
// *
// */
//public class FlightAnalyzer {
//
//
//	//instance variables
//	private ArrayList<String> flightLines;
//	private String flightLine;
////	private HashMap<Character, Integer> numChars;
////	private HashMap<Character, Integer> orderedNumChars;
////	private HashMap<Character, Integer> topTenNumChars;
////	private HashMap<String, Integer> numWords;
////	private HashMap<String, Integer> stopListWords;
////	private HashMap<String, Integer> numQuotes;
////	private HashMap<String, Integer> numWords2;
//
//	
//	
//	
//	/*
//	 * This is the constructor.
//	 * Accepts an ArrayList of String elements where each string is flight.
//	 */
//	public FlightAnalyzer(ArrayList<String> lines) {
//		//flightLines is an ArrayList of all the flights where each flight is an element
//		flightLines = lines;
//		flightLine = null;
//		//numChars = null;
//		//orderedNumChars = null;
//		//topTenNumChars = null;
//		//numWords = null;
//	}
//	
//
////	/**
////	 * Gets the contents of the book by lines.
////	 * @return each line in the book.
////	 */
////	public void analyzeEachLine() {
////		for (String line : bookLines) {
////			bookLine = line;
////			countCharacters();
////			//System.out.println(bookLine);
////		}
////		//System.out.println(bookLine);
////	}
//	
//
//	/**
//	 * Counts the number of times that a delay occurs in a specific month appears.
//	 * @return a HashMap where the key = month and value = delay time
//	 */
//	public HashMap<Character,Integer> countCharacters() {		
//		numChars = new HashMap<Character, Integer>();
//		
//		for (String line : bookLines) {
//			String str3 = line;
//			String str2 = str3.toLowerCase();
//			String str = str2.replaceAll("[^a-z]+", "");
//			
//			int len = str.length();
//			for (int i = 0; i < len; i++) {
//			    char charAt = str.charAt(i);
//
//			    if (!numChars.containsKey(charAt)) {
//			        numChars.put(charAt, 1);
//			    }
//			    else {
//			        numChars.put(charAt, numChars.get(charAt) + 1);
//			    }
//			}	
//		}
//		//System.out.println(numChars);
//		return numChars;
//	}
//	
//	
//	/**
//	 * Counts the number of times that each word appears in a file.
//	 * @return a HashMap where the key = words in file and value = number of occurrences of that word
//	 */
//	public HashMap<String,Integer> countWords() {		
//		numWords = new HashMap<String, Integer>();
//		stopListWords = new HashMap<String, Integer>();
//		FileReader stop = new FileReader("stop-list.txt");
//		ArrayList<String> stopList = stop.getLines();
//		//System.out.println(stopList);
//		
//		for (String line : bookLines) {
//			String str4 = line;
//			String str3 = str4.toLowerCase();
//			String str2 = str3.replaceAll("[^\\w-']+", " ");
//			String str1 = str2.replaceAll("--", " ");
//			String str = str1.replaceAll(" \' | \'|' |^'", " ");
//			
//			//System.out.println(str);
//			
//			String trimmed = str.trim();
//			
//			String[] sentence = trimmed.split("\\s+");
//			  
//			for (int i = 0; i < sentence.length; i++) {
//				String wordAt = sentence[i];
//							
//				
//				if (sentence.length > 1) {
//				    if (!numWords.containsKey(wordAt)) {
//				        numWords.put(wordAt, 1);
//				    }
//				    else {
//				        numWords.put(wordAt, numWords.get(wordAt) + 1);
//				    }
//				}
//			}	
//		}
//		//System.out.println(numWords);
//		return numWords;
//	}
//	
//	
//	
//	
//	
//	/**
//	 * Counts the number of times that each word appears in a file NOT including words in the stop list.
//	 * @return a HashMap where the key = words in file and value = number of occurrences of that word
//	 */
//	public HashMap<String,Integer> countWordsAgainstStopList() {		
//		numWords2 = new HashMap<String, Integer>();
//		stopListWords = new HashMap<String, Integer>();
//		FileReader stop = new FileReader("stop-list.txt");
//		ArrayList<String> stopList = stop.getLines();
//		//System.out.println(stopList);
//		
//		for (String line : bookLines) {
//			String str4 = line;
//			String str3 = str4.toLowerCase();
//			String str2 = str3.replaceAll("[^\\w-']+", " ");
//			String str1 = str2.replaceAll("--", " ");
//			String str = str1.replaceAll(" \' | \'|' |^'", " ");
//			
//			//System.out.println(str);
//			
//			String trimmed = str.trim();
//			
//			String[] sentence = trimmed.split("\\s+");
//			  
//			for (int i = 0; i < sentence.length; i++) {
//				String wordAt = sentence[i];
//							
//				
//				if (sentence.length > 1) {
//				    if (!numWords2.containsKey(wordAt)) {
//				        numWords2.put(wordAt, 1);
//				    }
//				    else {
//				        numWords2.put(wordAt, numWords2.get(wordAt) + 1);
//				    }
//				}
//			}	
//		}
//		
//		for (int i = 0; i < numWords2.size(); i++) {
//			for (int j = 0; j < stopList.size(); j++) {
//				if (numWords2.containsKey(stopList.get(j))) {
//					numWords2.remove(stopList.get(j));
//				}
//			}
//		}
//		//System.out.println(numWords2);
//		return numWords2;
//	}
//	
//	
//	
//	
//	
//	
//	/**
//	 * Counts the number of times that each quote appears in a file.
//	 * @return a HashMap where the key = quote in file and value = number of occurrences of that quote
//	 */
//	public HashMap<String, Integer> countQuotes() {
//		numQuotes = new HashMap<String, Integer>();
//		String totalBook = bookLines.get(0);
//		
//		for (String line : bookLines) {
//			totalBook += " " + line;
//		}
//		//System.out.println(totalBook);
//		
////		String[] maybeQuotes = totalBook.split("\\s\".+\"\\s | \\s\'.+\'\\s");
////		for (String quote : maybeQuotes) {
////			System.out.println("Enter");
////			System.out.println(quote);
////			System.out.println("Exit");
////		}
//		
//		Pattern p = Pattern.compile("\\s+\'([^\']*)\'\\s+");
//		Matcher m = p.matcher(totalBook);
//		
//		while (m.find()) {
//			//System.out.println(m.group(1));
//			String quote = m.group(1).toString();
//			if (!numQuotes.containsKey(quote)) {
//				numQuotes.put(quote, 1);
//			}
//			else {
//				numQuotes.put(quote, numQuotes.get(quote) + 1);
//			}
//		}
//		
//		//System.out.println(numQuotes);
//		return numQuotes;
//	}
//	
//	
//	
//	/**
//	 * Counts the number of lines in a text
//	 * @return the count of the lines.
//	 */
//	public int countLines() {
//		int count = 0;
//		for (String line : bookLines) {
//			count = count + 1;
//		}
//		return count;
//	}
//	
//	
//	
//	
//	
//}

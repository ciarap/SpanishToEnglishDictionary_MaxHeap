package controllers;
import java.io.File;
import java.util.Scanner;

import models.MaxHeap;
import models.Pair;
import models.Serializer;
import models.UserInterface;
import models.XMLSerializer;

/**
 * The main part of the translator.
 * 
 * @author  Ciara Power
 */
public class Dictionary {
	
	private static  boolean debug = false; 
	
	private MaxHeap spanishHeap=new MaxHeap();        
	private static Dictionary dict;      
	private String spanishWord="";    // Spanish word entered for translating
	private String translation="";     // translated
	private File  dictionary = new File("dictionary.xml");   // xml file for saved dictionary heap
	private  Serializer serializer = new XMLSerializer(dictionary);   //to load and save xml 



	public static void main(String[] args) throws Exception {
		dict=new Dictionary();
		new UserInterface(dict);
		dict.load("spanishToEnglish.txt");// pass txt file to load method
        if (debug){
        	System.out.println("--------- LOADED HEAP : ---------\n"+dict.spanishHeap.display()+"\n---------");
        }
	}
	
	public MaxHeap getSpanishHeap() {     //returns heap
		return spanishHeap;
	}

	public void setSpanishHeap(MaxHeap spanishHeap) {   //sets heap 
		this.spanishHeap = spanishHeap;
	}

	public void load (String fileName) throws Exception{    //method to load from txt or xml file
		if (debug){
        	System.out.println("---------\n LOAD \n---------");
        }
		if (dictionary.isFile())   // if xml file exists
		{
			serializer.read();   //read xml file
			spanishHeap=(MaxHeap) serializer.pop();   //pop the heap stored in xml to global heap variable
			 if (debug){
		        	System.out.println("---------\n XML File Loaded \n---------");
		        }
		}
		else{
			readFile(fileName);  // if no xml file, read the txt file 
			write();   //save the heap to xml once txt file loaded
			 if (debug){
		        	System.out.println("---------\n TXT File Loaded \n---------");
		       }
		}
		
	}
	
	public  void readFile(String fileName) throws Exception{   //method to read the txt file of dictionary words, takes filename in as parameter
		File in=new File(fileName);
		Scanner inTerm = new Scanner(in);
		String delims = "[?]";//each field in the file is separated(delimited) by a ?.
		while (inTerm.hasNextLine()) {     //while the txt file has not reached an end
			String termDetails = inTerm.nextLine();
			// parse term details string
			String[] termTokens = termDetails.split(delims);// split the line of data into a String[] by the ? divider
			if (termTokens.length == 2) {     // each token should have a spanish word and english translations 
			if (debug){
				System.out.println(termTokens[0]+" : "+termTokens[1]);
			}
             spanishHeap.add(new Pair(termTokens[0],termTokens[1].trim()));   //add the created pair to the heap
			}
		}
		if (debug){
		System.out.println("---------\n Heap size: "+spanishHeap.getSize()+"\n---------");
		}
		inTerm.close();
	}

	
	public void clear()   //clear strings for translation and spanish word
	{
		spanishWord = "";
		translation="";

	}

	public Pair findMatch(String spanish) {      // return a pair with spanish word that matches the spanish parameter
		Pair item=new Pair(spanish, "");    //create pair with the required spanish word, english not required
		if (debug){
			System.out.println("---------\n"+item.toString()+"\n---------");
		}
		return (Pair) spanishHeap.searchHeap(item, 0, false, false);   //call search method and pass through the pair created above, 
		 															// passes through the starting point in heap, and booleans to indicate the left and right children heaps were not searched yet	
	}
	
	public void add(String spanish,String english) throws Exception {   //method to add translation to dictionary
		Pair match=findMatch(spanish);  //find match for the spanish word entered
		if (match==null){  // if this spanish word isnt already in the heap
		 spanishHeap.add(new Pair(spanish,english));   //add this word and translation as a new pair
		 if (debug){
	        	System.out.println("---------\nNo match found, "+spanish+" : "+english +" added\n---------");
	        }
		}
		else {
			 if (debug){
		        	System.out.println("---------\nMatch for "+spanish+" is: "+match.toString()+"\n---------");
		        }
			match.setEnglish(match.getEnglish()+", "+english); // if spanish word exists already, add the english translation to the existing translation
			if (debug){
	        	System.out.println("---------\nNew Translation for "+spanish+" is: "+match.getEnglish()+"\n---------");
	        }
			
		}
		write();  // save to xml, to save any changes 
	}

	public String getSpanishWord() {   //get the spanish word entered to be translated
		 if (debug){
	        	System.out.println("---------\nGET Spanish Word: "+ spanishWord+"\n---------");
	        }
		return spanishWord;
	}

	public void setSpanishWord(String spanishWord) {   //set the spanish word with the parameter entered
		if (debug){
        	System.out.println("---------\nSET Spanish Word: "+ spanishWord+"\n---------");
        }
		this.spanishWord = spanishWord;
	}

	public String getTranslation() {   //get the translation
		if (debug){
        	System.out.println("---------\nGET Translation: "+ translation+"\n---------");
        }
		return translation;
	}

	public void setTranslation(String translation) {    //set the translation
		if (debug){
        	System.out.println("---------\nSET Translation: "+ translation+"\n---------");
        }
		this.translation = translation;
	}
	
	public int treeTraversal(){    //returns the tree traversal integer at that point in time from heap object
		if (debug){
        	System.out.println("---------\nGET Tree Traversal from heap: "+ spanishHeap.getTreeTraversal()+"\n---------");
        }
		return spanishHeap.getTreeTraversal();   
	}

	public void write() throws Exception {    // saves heap to xml
		if (debug){
        	System.out.println("---------\n WRITE \n---------");
        }
		serializer.push(spanishHeap);
		serializer.write(); 

	}

	public File getDictionary() {
		return dictionary;
	}


}

package models;

/**
 * A Pair object class, for a pairing of a spanish word and corresponding english translations.
 * 
 * @author  Ciara Power
 */
public class Pair implements Comparable<Object>{

private String spanish;
private String english;

public Pair(String spanish, String english) {
	this.spanish = spanish;
	this.english = english;
}

public String getSpanish() {
	return spanish;
}
public void setSpanish(String spanish) {
	this.spanish = spanish;
}
public String getEnglish() {
	return english;
}
public void setEnglish(String english) {
	this.english = english;
}

@Override
public int compareTo(Object arg0) {   // compares pair objects by spanish word, as this is how the dictionary heap is used
	return this.getSpanish().compareTo(((Pair) arg0).getSpanish());

}

@Override
public String toString() {
	return "Pair [spanish=" + spanish + ", english=" + english + "]";
}

	
	

}

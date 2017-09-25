import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.Dictionary;
import models.MaxHeap;
import models.Pair;

public class TestDictionary {
    Dictionary dict=new Dictionary();
	
	@Test
	public void testLoad() throws Exception{
		if (dict.getDictionary().isFile())  {
			dict.getDictionary().delete();   //no xml file to start
		}
		assertEquals(0,dict.getSpanishHeap().getSize());
		dict.load("testData.txt");
		assertEquals(8,dict.getSpanishHeap().getSize());
		assertTrue(dict.getDictionary().exists());
		dict.getSpanishHeap().clear();
		dict.load("");   //this txt file doesnt exist so tests the xml is loaded below
		assertEquals(8,dict.getSpanishHeap().getSize());
		
		
	}
	

	
	@Test 
	public void testAdd() throws Exception{
		if (dict.getDictionary().isFile())  {
			dict.getDictionary().delete();   //no xml file to start
		}
		assertEquals(0,dict.getSpanishHeap().getSize());
		dict.load("testData.txt");
		assertEquals(8,dict.getSpanishHeap().getSize());

		
		dict.add("Hola", "Hi");
		assertEquals(9,dict.getSpanishHeap().getSize());
		
		dict.add("Hola", "Hi,Hello");
		assertEquals(9,dict.getSpanishHeap().getSize());
	}
}

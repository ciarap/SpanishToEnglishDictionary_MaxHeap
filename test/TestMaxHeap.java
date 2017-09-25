import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.Dictionary;
import models.MaxHeap;
import models.Pair;

public class TestMaxHeap {

	private MaxHeap heap;
	Pair pair1,pair2,pair3,pair4,pair5,pair6;
	
	
	@Before
	public void setup() throws Exception{
		pair1=new Pair ("AAA","AAA");
		pair2=new Pair ("BBB","BBB");
		pair3=new Pair ("CCC","CCC");
		pair4=new Pair ("DDD","DDD");
		pair5=new Pair ("ABB","ABB");
		pair6=new Pair("FFF","FFF");
		heap=new MaxHeap();
		}
	
	@Test
	public void testAdd() {             // test all additions affect the heap as expected in size and display order
		assertTrue(heap.getSize()==0);
		
		heap.add(pair1);
		assertTrue(heap.getSize()==1);
		assertTrue(heap.getMax()==pair1);
		
		heap.add(pair2);
		assertTrue(heap.getSize()==2);
		assertEquals("\nPair [spanish=BBB, english=BBB]\nPair [spanish=AAA, english=AAA]",heap.display());
		
		heap.add(pair4);
		assertEquals("\nPair [spanish=DDD, english=DDD]\nPair [spanish=AAA, english=AAA]\nPair "
				+ "[spanish=BBB, english=BBB]",heap.display());
		
		heap.add(pair3);
		assertEquals("\nPair [spanish=DDD, english=DDD]\nPair [spanish=CCC, english=CCC]\nPair [spanish=BBB, english=BBB]"
				+ "\nPair [spanish=AAA, english=AAA]",heap.display());
		
		heap.add(pair5);
		assertEquals("\nPair [spanish=DDD, english=DDD]\nPair [spanish=CCC, english=CCC]\nPair [spanish=BBB, english=BBB]"
				+ "\nPair [spanish=AAA, english=AAA]\nPair [spanish=ABB, english=ABB]",heap.display());
	
		heap.add(pair6);
		assertEquals("\nPair [spanish=FFF, english=FFF]\nPair [spanish=CCC, english=CCC]\nPair [spanish=DDD, english=DDD]\nPair [spanish=AAA, english=AAA]"
				+ "\nPair [spanish=ABB, english=ABB]\nPair [spanish=BBB, english=BBB]",heap.display());
		
		assertEquals(6,heap.getSize());
		
	}
	
	@Test
	public void testRemove(){   //test remove max returns expected value, and rearranges to provide new max, and that getMax does no remove elements 
		heap.add(pair1);
		heap.add(pair2);
		heap.add(pair4);
		heap.add(pair3);
		heap.add(pair5);
		heap.add(pair6);
	    assertEquals(pair6,heap.removeMax());
	    assertEquals(5,heap.getSize());
	    assertEquals(pair4,heap.getMax());
	    assertEquals(5,heap.getSize());
	    assertEquals(pair4 ,heap.removeMax());
	    assertEquals(4,heap.getSize());
	    
	    assertEquals(pair3,heap.removeMax());
	    assertEquals(pair2,heap.getMax());
	    assertEquals(3,heap.getSize());
	}
	
	@Test
	public void testClearAndEmpty(){    // test isEmpty method and text the clear method
		heap.add(pair1);
		heap.add(pair2);
		heap.add(pair4);
		heap.add(pair3);
		heap.add(pair5);
		heap.add(pair6);
		assertEquals(false,heap.isEmpty());
		heap.clear();
		assertEquals(0,heap.getSize());
		assertEquals(true,heap.isEmpty());
	}
	
	@Test
	public void testSearchHeap(){   //test any searches return expected values , and tree traversals are correct
		heap.add(pair1);
		heap.add(pair2);
		heap.add(pair4);
		heap.add(pair3);
		heap.add(pair5);
		heap.add(pair6);
		
		assertEquals(pair6,heap.searchHeap(pair6, 0,false, false));
		assertEquals(0,heap.getTreeTraversal());
		assertEquals(pair1,heap.searchHeap(new Pair("AAA",""), 0,false, false));
		assertEquals(2,heap.getTreeTraversal());
		assertNull(heap.searchHeap(new Pair("Not In Heap","Not"), 0,false, false));
	}

}

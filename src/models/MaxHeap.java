package models;
import java.util.ArrayList;

/**
 * A Max Heap object class.
 * 
 * @author  Ciara Power
 */
public class MaxHeap implements MaxHeapInterface{

	private ArrayList<Comparable> heapArray;   //array for heap implementation
	private Comparable match=null;   //variable for the possible match for a spanish word
	private int treeTraversal=0;  // variable for tree traversals taken (up and down) to find the current match
	private boolean debug=false;


	public MaxHeap() {
		heapArray=new ArrayList<Comparable>();
	}

	@Override
	public void add(Comparable newEntry) {    //method to add a new object to heap
		heapArray.add(newEntry); //add to arraylist
		if(debug){
			System.out.println("---------\nNew Entry added: "+newEntry+"\nHeap Size: "+heapArray.size()+"\n---------");
		}
		siftUp();   // sift the new addition upwards until it is bigger than its children
	}

	@Override
	public int getTreeTraversal() {  //returns the tree traversal variable value
		return treeTraversal;
	}

	@Override
	public void siftUp() {     //method called to sift the last object added upwards
		if(debug){
			System.out.println("---------\nSIFT UP\n---------");
		}
		int k=heapArray.size()-1;   //start at last entry index
		while(k>0){   //while the entry index is within range of array 
			int p=(k-1)/2;   // the parent index 
			if(debug){
				System.out.println("---------\nChild Index: "+k+"\nParent Index: "+p+"\n---------");
			}
			Comparable child= heapArray.get(k);    //child object
			Comparable parent=heapArray.get(p);   //parent object
			if(debug){
				System.out.println("---------\nChild Spanish: "+((Pair) child).getSpanish()+"\nParent Spanish: "+((Pair) parent).getSpanish()+"\n---------");
			}
			if (child.compareTo(parent)>0){   // if the child is bigger than parent alphabetically
				//swap
				heapArray.set(k, parent);   
				heapArray.set(p, child);
				//move up one level
				k=p;  
				if(debug){
					System.out.println("---------\nSWAPPED UP\n---------");
				}
			}
			else{
				if(debug){
					System.out.println("---------\nSIFT UP END\n---------");
				}

				break;
			}
		}

	}

	@Override
	public Comparable removeMax() {   //remove and return the max value of the heap (at top)
		if(debug){
			System.out.println("---------\nREMOVE MAX\n---------");
		}
		Comparable max = null;  //Initialize the max value as null
		if(!heapArray.isEmpty()){   // if there are items in the heap
			max=heapArray.get(0);   //max is the top element
			if(debug){
				System.out.println("---------\nCurrent Max: "+max+"\n---------");
			}
			heapArray.set(0, heapArray.get(heapArray.size()-1));   // set the top element to be the last item added
			heapArray.remove(heapArray.size()-1);  //remove the last item added from the bottom of the heap
			siftdown();  //sift the top element downwards 
			if(debug){
				System.out.println("---------\nNew Max: "+heapArray.get(0)+"\n---------");
			}
		}
		return max;	  //return the max (previous top element before removal)
	}

	@Override
	public void siftdown() {   //method to sift down the top element until it is smaller than parent and bigger than children
		if(debug){
			System.out.println("---------\nSIFT DOWN\n---------");
		}
		int parentIndex=0;   // start at 0
		int leftChildIndex=2*parentIndex+1;   //left child index
		while(leftChildIndex<heapArray.size()){   //while the left child exists
			int max=leftChildIndex;  // set max to left index
			int rightChildIndex=leftChildIndex+1; //get possible right child index 
			if(rightChildIndex<heapArray.size()){  //while right child exists
				if(heapArray.get(rightChildIndex).compareTo(heapArray.get(leftChildIndex))>0){  // iff the right child is larger alphabetically
					if(debug){
						System.out.println("---------\nLeft: "+heapArray.get(max)+"\nRight: "+heapArray.get(rightChildIndex)+"\n---------");
						System.out.println("---------\nRight Child Greater\n---------");
					}
					max++;  //set the max to be right child index (because it is the max of the two children)
				}
			}
			if(heapArray.get(parentIndex).compareTo(heapArray.get(max))<0){   //if the parent is less than the max child 
				//switch
				if(debug){
					System.out.println("---------\nParent is smaller\n---------");
				}
				Comparable temp=heapArray.get(parentIndex);  
				heapArray.set(parentIndex, heapArray.get(max));   //move the max child upwards to parents index
				heapArray.set(max, temp);  //the parent item is now in max childs index
				parentIndex=max;  // set up for next loop, parent is now at max childs original index 
				leftChildIndex=2*parentIndex+1; // left child is recalculated for this parents position
			}
			else{
				break;  //break out of loop if the parent is bigger than children
			}
		}

	}

	@Override
	public ArrayList<Comparable> getHeapArray() {  //returns array of heap elements
		return heapArray;
	}

	@Override
	public void setHeapArray(ArrayList<Comparable> heapArray) {  //sets heap array
		this.heapArray = heapArray;
	}

	@Override
	public Comparable getMax() {   //returns the max value at top of the heap (doesnt remove it from heap)
		return heapArray.get(0);
	}

	@Override
	public boolean isEmpty() {   //returns boolean if the heap is empty or not
		if (heapArray.isEmpty())   // existing arrayList method isEmpty() which returns true if the array is empty
			return true;
		else
			return false;
	}

	@Override
	public int getSize() {    //returns the size of the heap
		return heapArray.size();
	}

	@Override
	public void clear() {   //clears the heap
		heapArray.clear();

	}

	@Override
	public String display(){   //method to return a string of the elements of the heap
		String display="";
		for(Comparable item:heapArray){
			display+="\n"+item.toString();
		}
		return display;
	}

	@Override    //method to search heap and return a match , takes parameters for item to search for, heap index to start at, 
					//and if that currents left and right children were searched yet
	public Comparable searchHeap(Comparable item,int current,boolean leftChecked,boolean rightChecked){
		debug=false;
		if(debug){
			System.out.println("---------\nSEARCH BEGIN\nCurrent: "+current+"\n---------");
		}
		if (current==0 && !leftChecked){   // if index is 0 and left hasnt been checked, this is the first search of the heap
			match = null;    //match initialised to null at start
			treeTraversal=0;   //tree traversal set to 0 (restarted for each new searched item)
		}
		int leftOrRight;   // variable which indicates if left or right child being checked
		if (rightChecked || !leftChecked){  // if the right was checked (all children from current checked) or the left hasnt been checked(first search with this current)
			leftOrRight=1;   //set the variable to 1, which will be added below in equation to access the left child index 
			if(debug){
				System.out.println("---------\nLeft isnt checked yet, or both are checked\n---------");
			}
		}
		else{
			if(debug){
				System.out.println("---------\nLeft Checked, Right Unchecked\n---------");
			}
			leftOrRight=2;   // left has been checked, but right hasnt, so 2 must be added below in equation to access right child of the current 
		}
		if(leftChecked && rightChecked ){  //if both children checked 
			if(current==0){   //if current index is 0, then whole heap was searched 
				if(debug){
					System.out.println("---------\nBoth Children Checked, current is 0 (Done Search of Whole Heap)\n---------");
				}
				return match;  // heap searched so return whatever match value is set at this point, will be an item or null if no matches
			}
			else if(current%2==0){   // if the index is even it is a right child of its parent
				if(debug){
					System.out.println("---------\nBoth Children of the current's parent Checked, current is a Right child (so done Search of the parent's children)\n---------");
				}
				treeTraversal++;
				searchHeap(item,(current-1)/2,true, true);   // search next heap section, starting at the current's parent, left and right are both checked so both true
			}
			else if(current==heapArray.size()-1){  // if the current node being checked is the last node of heap, return match ( match would have been returned by now if match found)
				return match;
			}
			else{
				if(debug){
					System.out.println("---------\nBoth Children of the current's parent Checked, current is a left child (Done Search of the parent's left child only)\n---------");
				}
				treeTraversal++;
				searchHeap(item,(current-1)/2,true, false); // search next heap section upwards, starting at the current's parent, left is checked so true,and right isnt so false
			}
		}
		else {   //if both children not checked 
			if (((Pair)item).compareTo(heapArray.get(current))==0){  // if searched item matches the current node
				if(debug){
					System.out.println("---------\nObjects the same: "+item.toString()+" : "+heapArray.get(current).toString()+"\n---------");
				}
				match=heapArray.get(current);  //set as match
				return match; //return to break out of method , no need to continue as match found
			}
			else if (((Pair) item).getSpanish().compareTo(((Pair) heapArray.get(current)).getSpanish())>0){ // if searched item is bigger alphabetically than current node, match cannot be under this node 
				if(debug){
					System.out.println("---------\nItem is larger than current,so go back up: "+item.toString()+" : "+heapArray.get(current).toString()+"\n---------");
				}
				if(current%2==1){    // if current is a left child of its parent
					treeTraversal++;
					searchHeap(item,(current-1)/2,true,false);// move upwards with search to parent, left is now checked (true) so must check right (false)
				}

				else if (current%2==0){  // if current is a right child of its parent
					treeTraversal++;
					searchHeap(item,(current-1)/2,true,true);// move upwards with search to parent, left is now checked (true) and so is right (true)
				}
			}
			else if(2*current+leftOrRight<heapArray.size() ){  // if the current has a child (left or right depends on setting at beginning of method)
				int child=2*current+leftOrRight;
				if(debug){
					System.out.println("---------\nTraverse down heap to child\nCurrent: "+current+"\nChild: "+child+"\n---------");
				}
				treeTraversal++;
				searchHeap(item,child,false,false);//search downwards into the current's children nodes, initially left and right arent checked for the child, so both false
			}
			else{
				if(debug){
					System.out.println("---------\nNo Children of this current node, go back up\n---------");
					}
				if(current%2==1){   // if current is a left child 
					treeTraversal++;
					searchHeap(item,(current-1)/2,true,false); // move upwards with search to parent, left is now checked (true) so must check right (false)
				}

				else if (current%2==0){  // if current is a right child 
					treeTraversal++;
					searchHeap(item,(current-1)/2,true,true); // move upwards with search to parent, left is now checked (true) and so is right (true)
				}
			}
		}
		return match;   //return match ( will either be null or an object)
	}

}
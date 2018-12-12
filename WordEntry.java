import java.util.*;

public class WordEntry extends MyLinkedList <Position> {
	public String myWord;
	public Position myPosition;
	public AVLTree myTree;
	public MyLinkedList <Position> myList;
	public WordEntry(String word){	//Constructor method.
		myWord=word;
		myList = new MyLinkedList <Position> ();
		myTree= new AVLTree();
	}

	public String getName(){
		return myWord;
	}
	public void addPosition(Position position){		//Adds a position entry for str.
		myList.addLast(position);

	} 

	public void addPositions(MyLinkedList<Position> positions){	// Add multiple position entries for str.
		Node head = positions.first();
		while(head!=null){
			myList.addLast(head.getElement());
			head=head.getNext();
		}
	}

	public MyLinkedList<Position> getAllPositionsForThisWord(){	//turn a linked list of all position entries for str.
		return myList;

	}

	public AVLTree treeForWord(){
		MyLinkedList<Position>.Node positionindex= getAllPositionsForThisWord().first();
		while(positionindex!=null){
			myTree.AVLinsertNode(positionindex.getElement());
			AVLNode nodesss = myTree.search(positionindex.getElement().getWordIndex());
			//System.out.println("Inserted node in small AVL has index "+ positionindex.getElement().getWordIndex());
			positionindex=positionindex.getNext();
		}
		//System.out.println("inserted");
		return myTree;
	}

}

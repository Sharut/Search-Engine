import java.util.*;

public class MyHashTable extends MyLinkedList <WordEntry>{				//It maps a word to its word-entry.
	public MyLinkedList <WordEntry> [] myarray;	// array of linked lists. 2 * asci(sum)+19 mod 31
	public int code;
	public int ascii;
	public int flag;
	public MyHashTable(){
		myarray = new MyLinkedList [501];
		for(int m=0;m<500;m++)
			myarray[m]= new MyLinkedList();
	}

	private int getHashIndex(String str){
		//int ascii ;
		for( int i=0;i<str.length();i++){
			char character = str.charAt(i);
			ascii = ascii + (int) character;
		}
		code = ((29*ascii + 67)% 971)% 500;
		return code;
	}

	public void addPositionsForWord(WordEntry w){
		flag=0;
		String myWord = w.getName();
		int function = getHashIndex(myWord);
		Node head =myarray[function].first();
		//System.out.println("test5 " + function +" "+myWord);
		while(head!=null && flag==0){
			if(head.getElement().getName().equals(myWord)){
				head.getElement().addPositions(w.getAllPositionsForThisWord());
				flag=1;
			}
			head=head.getNext();
		}
		
		if(flag==0)
			myarray[function].addLast(w);

	}
}
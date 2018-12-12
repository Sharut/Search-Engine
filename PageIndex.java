import java.util.*;

public class PageIndex extends MyLinkedList <WordEntry>{			//stores one word-entry for each unique word in the document.
	public MyLinkedList<WordEntry> existingWords;
	public PageIndex(){			//constructor method
		existingWords =  new MyLinkedList <WordEntry>();
	}

	public void addPositionForWord(String str, Position p){
		Node index = existingWords.first();
		Node mynode = null;
		while(index!=null){
			if(index.getElement().getName().equals(str))
				mynode = index;
			index= index.getNext();
		}
		
		if(mynode!=null){
			mynode.getElement().addPosition(p);
			//System.out.println("word alredy exists ");
		}
		else{
			WordEntry mynode1 = new WordEntry(str);
			mynode1.addPosition(p);
			existingWords.addLast(mynode1);
		}
	}

	public MyLinkedList<WordEntry> getWordEntries(){
		return existingWords;
	}

	public boolean containsWord(String amt){
		Node index1 = existingWords.first();
		Node mynode1 = null;
		int flag=0;
		while(index1!=null && flag==0){
			//System.out.println(index1.getElement().getName()+" //" + amt);
			if(index1.getElement().getName().equals(amt)){
				//System.out.println("sheena");
				mynode1 = index1;
				flag=1;
				//System.out.println(index1.getElement().getName() + " ");
			}
			index1= index1.getNext();
		}
		return mynode1!=null;
	}

	public WordEntry getEntryWithName(String g){
		MyLinkedList<WordEntry>.Node node = existingWords.first();
		while(node!=null ){
			if(node.getElement().getName().equals(g)){
				//System.out.println("found");
				return node.getElement();

			} 			
			node=node.getNext();	
		}
		return null;
	}
	public float getTermFrequency(String word){
		WordEntry entries = getEntryWithName(word);
		int x = entries.getAllPositionsForThisWord().size();
		int sum =0;
		MyLinkedList<WordEntry>.Node begin = existingWords.first();
		while(begin!=null){
			 sum = sum + begin.getElement().getAllPositionsForThisWord().size();
			 begin=begin.getNext();
		}
		return x/sum;
	}






}





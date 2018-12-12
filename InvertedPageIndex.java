import java.util.*;

public class InvertedPageIndex {
	public MySet<PageEntry> pageEntrySet;
	public MyHashTable myTable;

	public InvertedPageIndex(){
		pageEntrySet = new MySet<PageEntry>();
		myTable = new MyHashTable();
	}

	public void addPage(PageEntry p){
		PageIndex myPageIndex = p.getPageIndex();
		//System.out.println("test2");
		MyLinkedList<WordEntry>.Node header = myPageIndex.getWordEntries().first();
		//System.out.println("test3");
		while(header!=null){
			myTable.addPositionsForWord(header.getElement());
			header=header.getNext();
		}
		//System.out.println("test4");
		pageEntrySet.addElement(p);
		//System.out.println(pageEntrySet.size() +"bello");
		//this.Display();
	}

	public MySet<PageEntry> getPagesWhichContainWord(String str){
		MySet containsSet = new MySet<PageEntry>();
		MyLinkedList<PageEntry>.Node counter = pageEntrySet.list.first();
		while(counter!=null){
			if (counter.getElement().getPageIndex().containsWord(str)==true){
					containsSet.addElement(counter.getElement());
					//System.out.println("sharut");
			}
			counter = counter.getNext();

		}
		return containsSet;
	}
	public MySet<PageEntry> setOfPageEntries(){
		return pageEntrySet;
	}

	

	public PageEntry pageEntryGivenName(String pagename){
		MyLinkedList<PageEntry>.Node lists = pageEntrySet.list.first();
		while(lists!=null){
			if(lists.getElement().getPageName().equals(pagename))
				return lists.getElement();
			lists=lists.getNext();
		}
		return null;
	}


	public void Display(){
    	MyLinkedList<PageEntry>.Node hum = pageEntrySet.list.first();
    	if(hum==null)
    		return ;
    	while(hum.getNext()!=null){
    		System.out.print(hum.getElement().getPageName() + ",");
    		hum=hum.getNext();
    	}
    	System.out.print(hum.getElement().getPageName());
    	System.out.println();

    }

    public MySet<PageEntry>getPagesWhichContainPhrase(String str[]){
    	MySet<PageEntry> blue = getPagesWhichContainWord(str[0]);
    	MySet<PageEntry> setcontainingphrase = new MySet<PageEntry>();
    	for(int i=1;i<str.length;i++){
    		if(blue!=null)
    			blue = blue.intersection(getPagesWhichContainWord(str[i]));
    	}
    	//System.out.println(blue);
    	if(blue==null){
    		//System.out.println("testuuuu");
    		return null;
    	}
    	//System.out.println(blue.size());
    	MyLinkedList<PageEntry>.Node counts = blue.list.first();
    	while(counts!=null){
    		//System.out.println("CURRENT PAGE IS "+counts.getElement().getPageName());
    		if(counts.getElement().isPhrase(str)){
    			//System.out.println("this page contains phrase "+ counts.getElement().getPageName());
    			setcontainingphrase.addElement(counts.getElement());
    		}
    		counts=counts.getNext();
    	}
    	return setcontainingphrase;
    }


}
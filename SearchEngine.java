import java.util.*;
import java.io.*;


public class SearchEngine {
	public InvertedPageIndex myInvertedPageIndex;
	public PageEntry myPageEntry;
	public MySort sortingClass;
	public SearchEngine(){
		myInvertedPageIndex = new InvertedPageIndex();
		sortingClass= new MySort();
	}

	public ArrayList<SearchResult> sortPages(MySet<PageEntry> pages,boolean isPhrase,String[] wordss){
		MySort sortingClass = new MySort();
		//System.out.println("start sorting");
		MyLinkedList<PageEntry>.Node pagecounter = pages.list.first();
		MySet<SearchResult> sortables = new MySet<SearchResult>();
		while(pagecounter!=null){
			float relevance = pagecounter.getElement().getRelevanceOfPage(wordss,isPhrase);
			//System.out.println(pagecounter.getElement().getPageName() + " has relevance = "+ relevance);
			sortables.addElement(new SearchResult(pagecounter.getElement(),relevance));
			pagecounter=pagecounter.getNext();
		}
		return sortingClass.sortThisList(sortables);
	}



	public void performAction(String actionMessage){
		Scanner s = new Scanner(actionMessage);
		String check = s.next();
		

		if(check.equals("addPage")){
			String newPage = s.next();
			//System.out.println(newPage);
			myPageEntry = new PageEntry(newPage);
			//

			//myPageSet.addElement(myPageEntry);
			myInvertedPageIndex.addPage(myPageEntry);
			//System.out.println("test1");
			myPageEntry.myInvertedIndex = myInvertedPageIndex;
		}

		else if (check.equals("queryFindPagesWhichContainWord")){
			String myword = (s.next()).toLowerCase();
			if(myword.equals("stacks"))
				myword = "stack";
			else if(myword.equals("structures"))
				myword="structure";
			else if(myword.equals("applications"))
				myword="application";
			MySet<PageEntry> myPageSet;
			myPageSet = myInvertedPageIndex.getPagesWhichContainWord(myword);
			//System.out.println(myPageSet.size()+"hi");
			String[] wordarray = new String[1];
			wordarray[0]=myword;
			if(myPageSet.IsEmpty()==false){
				MyLinkedList<PageEntry>.Node myNode = myPageSet.list.first();

				ArrayList<SearchResult> listforword = this.sortPages(myPageSet,true,wordarray);
				
				int l=0;
				for( l=0;l<listforword.size()-1;l++){
					System.out.print(listforword.get(l).getPageEntry().getPageName()+", ");//+": "+ listforword.get(l).getPageEntry().getRelevanceOfPage(wordarray,false) +", ");
				}
				System.out.println(listforword.get(l).getPageEntry().getPageName());//+": "+ listforword.get(l).getPageEntry().getRelevanceOfPage(wordarray,false));

				/*while(myNode.getNext()!=null){
					System.out.print(myNode.getElement().getPageName() + ", ");
					myNode=myNode.getNext();
				}
				System.out.println(myNode.getElement().getPageName());*/
			}
			else{
				System.out.println("No webpage contains word " + myword);
			}
	
		}

		else if (check.equals("queryFindPositionsOfWordInAPage")){
			String x = (s.next()).toLowerCase();
			if(x.equals("stacks"))
				x="stack";
			else if(x.equals("structures"))
				x="structure";
			else if(x.equals("applications"))
				x="application";
			String y = s.next();
			PageEntry myEntry ;
			myEntry = myInvertedPageIndex.pageEntryGivenName(y);
			//System.out.println(myEntry.getPageName() +" BELLO")
			//System.out.println(myEntry==null);
			if(myEntry==null){
				System.out.println("No webpage " + y + " found");
			}
			else{
				if(myEntry.getPageIndex().containsWord(x)==false)
					System.out.println("Webpage "+ y+ " does not contain word " + x);
				else{
					WordEntry entryPage = myEntry.getPageIndex().getEntryWithName(x);
					//System.out.println(entryPage.getName()+" shuuta");					
					MyLinkedList<Position> mylist = entryPage.getAllPositionsForThisWord();
					//System.out.println(mylist.size()+" shuu");
					MyLinkedList<Position>.Node start = mylist.first();
					while(start.getNext()!=null){
						System.out.print(start.getElement().getWordIndex() + ", ");
						start=start.getNext();
					}
					System.out.println(start.getElement().getWordIndex());
				}

			}

		}

		else if(check.equals("queryFindPagesWhichContainAllWords")){
			ArrayList<String> words = new ArrayList<String>();
			while(s.hasNext()){
				words.add(s.next());
			}

			String[] myarray= new String[words.size()];
			for(int m=0;m<words.size();m++){
				myarray[m]=words.get(m);
			}
			
			MySet<PageEntry> pagesAllWords = this.myInvertedPageIndex.getPagesWhichContainWord(words.get(0));
			//System.out.println(pagesAllWords.size());
			for(int i=1;i<myarray.length;i++){
				if(pagesAllWords!=null){
					//System.out.println(myInvertedPageIndex.getPagesWhichContainWord(words.get(i)).size());
					//System.out.println(pagesAllWords.size());
					pagesAllWords = pagesAllWords.intersection(myInvertedPageIndex.getPagesWhichContainWord(words.get(i)));
					//System.out.println(pagesAllWords.size() + "hey");
				}
			}
			//System.out.println("bello");
			if(pagesAllWords==null)
				System.out.println("No Webpage contains all the words");
			else{
				int l=0;
				ArrayList<SearchResult> myarraylist = this.sortPages(pagesAllWords,false,myarray);
				for(l=0;l<myarraylist.size()-1;l++){
					System.out.print(myarraylist.get(l).getPageEntry().getPageName()+", ");// +": " + myarraylist.get(l).getPageEntry().getRelevanceOfPage(myarray,false)+", ");
				}
				System.out.println(myarraylist.get(l).getPageEntry().getPageName());//+": " + myarraylist.get(l).getPageEntry().getRelevanceOfPage(myarray,false));
			}

		}


		else if(check.equals("queryFindPagesWhichContainAnyOfTheseWords")){
			ArrayList<String> words1 = new ArrayList<String>();
			while(s.hasNext()){
				words1.add(s.next());
			}

			String[] myarray1= new String[words1.size()];
			for(int m=0;m<words1.size();m++){
				myarray1[m]=words1.get(m);
			}
			MySet<PageEntry> pagesAllWordsA = this.myInvertedPageIndex.getPagesWhichContainWord(words1.get(0));
			for(int i=1;i<words1.size();i++){
				pagesAllWordsA = pagesAllWordsA.Union(myInvertedPageIndex.getPagesWhichContainWord(words1.get(i)));
			}

			if(pagesAllWordsA==null)
				System.out.println("No Webpage contains any of the words");
			else{
				int l=0;
				ArrayList<SearchResult> myarraylist1 = this.sortPages(pagesAllWordsA,false,myarray1);
				for(l=0;l<myarraylist1.size()-1;l++){
					System.out.print(myarraylist1.get(l).getPageEntry().getPageName()+", ");//+": "+ myarraylist1.get(l).getPageEntry().getRelevanceOfPage(myarray1,false) +", ");
				}
				System.out.println(myarraylist1.get(l).getPageEntry().getPageName());//+": "+ myarraylist1.get(l).getPageEntry().getRelevanceOfPage(myarray1,false));
			}

		}

		else if(check.equals("queryFindPagesWhichContainPhrase")){
			//System.out.println("test11");
			ArrayList<String> word = new ArrayList<String>();
			while(s.hasNext()){
				word.add(s.next());
			}

			String[] array= new String[word.size()];
			for(int m=0;m<word.size();m++){
				array[m]=word.get(m);
			}

			//System.out.println("test1");
			MySet<PageEntry> pagesPhrase = this.myInvertedPageIndex.getPagesWhichContainPhrase(array);
			//System.out.println("test2");
			if(pagesPhrase==null)
				System.out.println("No webpage contains the given phrase");
			else{
				int l=0;
				ArrayList<SearchResult> listy = this.sortPages(pagesPhrase,true,array);
				for(l=0;l<listy.size()-1;l++){
					System.out.print(listy.get(l).getPageEntry().getPageName()+", ");//+": "+ listy.get(l).getPageEntry().getRelevanceOfPage(array,true) +", ");
				}
				System.out.println(listy.get(l).getPageEntry().getPageName());//+": "+ listy.get(l).getPageEntry().getRelevanceOfPage(array,true));
			}

		}
	}
}
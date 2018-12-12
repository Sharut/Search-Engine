import java.util.*;
import java.io.*;

public class PageEntry{		//store the the information related to a webpage.
	public String pageName;
	public PageIndex myIndex;
	public AVLTree myTree;
	public InvertedPageIndex myInvertedIndex;
	public BufferedReader br;
	public int count = 1;


	public PageEntry(String pageName){
		this.pageName = pageName;
		this.myIndex =  new PageIndex();
		String mystring;
		myInvertedIndex= new InvertedPageIndex();
		myTree=new AVLTree();
		String[] punctiations;
		try{
			br = new BufferedReader( new FileReader ("./webpages/"+pageName));
			//System.out.println("blue");
			while((mystring = br.readLine())!=null){
				mystring=mystring.toLowerCase();
				punctiations = mystring.split("\\s++|\\{|}|<|>|\\(|\\)|\\.|,|;|'|\"|\\?|#|!|-|:");
				//punctiations = mystring.split("[{}[]<>=().,;’”?#!-:]+\\s*");
				for(int x =0;x<punctiations.length;x++){
					if(!punctiations[x].equals("")){
						//System.out.println(punctiations[x]);
						if(punctiations[x].equals("stacks"))
							punctiations[x]="stack";
						else if(punctiations[x].equals("structures"))
							punctiations[x]="structure";
						else if(punctiations[x].equals("applications"))
							punctiations[x]="application";

						if(!(punctiations[x].equals("a") || punctiations[x].equals("an") || punctiations[x].equals("the") || punctiations[x].equals("they") || punctiations[x].equals("these") || punctiations[x].equals("this") || punctiations[x].equals("for") || punctiations[x].equals("is") || punctiations[x].equals("are") || punctiations[x].equals("was")|| punctiations[x].equals("of") || punctiations[x].equals("or") || punctiations[x].equals("and") || punctiations[x].equals("does") || punctiations[x].equals("will")|| punctiations[x].equals("whose"))){
							Position myPosition = new Position(this, count);
							//System.out.print(count+ "- " + punctiations[x]+", ");
							myIndex.addPositionForWord(punctiations[x],myPosition);
							myTree.AVLinsertNode(myPosition);
							//System.out.print(myPosition.getWordIndex()+", ");				
						}
					count++;
					}
				}
			}
			//System.out.println("");

		}
		catch (Exception g){
			System.out.println("File not found");
		}

	}
	public PageIndex getPageIndex(){
		return myIndex;
	}

	public String getPageName(){
		return pageName;
	}

	public int wordFrequency(String hello){
		if(this.getPageIndex().getEntryWithName(hello)!=null)
			return this.getPageIndex().getEntryWithName(hello).getAllPositionsForThisWord().size();
		return 0;
	}

	public boolean isPhrase(String arrayofstrings[]){
		MyLinkedList<Position> positionlist = this.getPageIndex().getEntryWithName(arrayofstrings[0]).getAllPositionsForThisWord();
    	MyLinkedList<Position>.Node position1 = positionlist.first();
    	/*while(position1!=null){
    		System.out.print(position1.getElement().getWordIndex() +", ");
    		position1=position1.getNext();
    	}
    	System.out.println("");
    	position1 = positionlist.first();*/

    	int flag=1;
    	while(position1!=null){
    		//System.out.println(position1.getElement().getWordIndex() + " start ");
    		AVLNode mynode= myTree.search(position1.getElement().getWordIndex());
    		//System.out.println("Searched Node is " + mynode.info.getWordIndex());
    		flag=1;
    		for(int i=1;i<arrayofstrings.length && flag==1;i++){
    			//System.out.println("start");
    			AVLTree positionTreeElements = this.getPageIndex().getEntryWithName(arrayofstrings[i]).treeForWord();
    			//positionTreeElements.inorderTraversal();
    			//System.out.println("successor of "+ position1.getElement().getWordIndex()+" is "+ myTree.inOrderSuccessor(mynode).info.getWordIndex());
    			//myTree.inorderTraversal();
    			if(positionTreeElements.search(myTree.inOrderSuccessor(mynode).info.getWordIndex())==null){	
    				//System.out.println("oops");
    				flag=0;
    			}
    		}
    		if(flag==1){
    			//System.out.println("yes");
    			//System.out.println("bbababab");
    			return true;
    		}
    		position1=position1.getNext();
    	}
    	//System.out.println("end");
    	return false;

    }

    public int phraseFrequency(String arrayofstrings[]){
		MyLinkedList<Position> positionlist1 = this.getPageIndex().getEntryWithName(arrayofstrings[0]).getAllPositionsForThisWord();
    	MyLinkedList<Position>.Node position1 = positionlist1.first();
    	int flag=1;
    	int count=0;
    	while(position1!=null){
    		//System.out.println(position1.getElement().getWordIndex());
    		AVLNode mynode= myTree.search(position1.getElement().getWordIndex());
    		flag=1;
    		for(int i=1;i<arrayofstrings.length && flag==1;i++){
    			AVLTree positionTreeElements = this.getPageIndex().getEntryWithName(arrayofstrings[i]).treeForWord();
    			if(positionTreeElements.search(myTree.inOrderSuccessor(mynode).info.getWordIndex())==null){
    				//System.out.println(position1.getElement().getWordIndex());
    				flag=0;
    			}
    		}
    		if(flag==1)
    			count++;

    		position1=position1.getNext();
    	}
    	//System.out.println(this.getPageName()+ " has frequcny = "+ count );
    	return count;

    }


	public float getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase){
		float relevance=0;
		if(doTheseWordsRepresentAPhrase){
			//System.out.println("start relevance check case1");
			float totalWordsInAPage = 0;
			MyLinkedList<WordEntry>.Node hej = this.getPageIndex().getWordEntries().first();
			while(hej!=null){
				totalWordsInAPage = totalWordsInAPage + hej.getElement().getAllPositionsForThisWord().size();
				hej=hej.getNext();
			}
			//System.out.println("total words are "+ totalWordsInAPage);
			float m = this.phraseFrequency(str);
			float noOfPagesContainingPhrase = myInvertedIndex.getPagesWhichContainPhrase(str).size();
			//System.out.println("total pages with phrase are  "+ noOfPagesContainingPhrase);
			float k = str.length;
			//System.out.println("string length is "+ k);
			float n = myInvertedIndex.setOfPageEntries().size();
			//System.out.println("total pages are "+ n);
			relevance = (float)(m/(totalWordsInAPage - (k-1)*m)) * (float)(Math.log(n/noOfPagesContainingPhrase )) ;
			return relevance;
		}
		else{
			//System.out.println("start relevance check case2");
			float totalWordsInAPage = 0;
			MyLinkedList<WordEntry>.Node hej = this.getPageIndex().getWordEntries().first();
			while(hej!=null){
				totalWordsInAPage = totalWordsInAPage + hej.getElement().getAllPositionsForThisWord().size();
				hej=hej.getNext();
			}
			float n = myInvertedIndex.setOfPageEntries().size();
			for(int x=0;x<str.length;x++){
				float noOfPagesContainingWord = myInvertedIndex.getPagesWhichContainWord(str[x]).size();
				//System.out.println("no of pages containing "+str[x]+" "+ noOfPagesContainingWord);

				float frequencyOfWord = this.wordFrequency(str[x]);
				//System.out.println(this.getPageName() + "contains "+ frequencyOfWord + " times " + str[x]);
				float tf = (float)(frequencyOfWord/totalWordsInAPage);
				float idf = (float)(Math.log(n/noOfPagesContainingWord));
				relevance += tf*idf;
			}
			return relevance;
		}
	}






}
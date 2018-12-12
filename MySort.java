import java.util.*;

public class MySort { // BUBBLE SORTING

	public MySort(){}

	public ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries){
		ArrayList<SearchResult> output = new ArrayList<SearchResult>();
		MyLinkedList<SearchResult>.Node start = listOfSortableEntries.list.first();
		if(start==null)
			return null;

		while(start!=null){
			output.add(start.getElement());
			start=start.getNext();
		}
		this.bubbleSort(output);
		return output;
	}

	public void bubbleSort(ArrayList<SearchResult> a){
		for(int i=0;i<a.size()-1;i++){
			for(int j=0;j<a.size()-i-1;j++){
				if((a.get(j)).compareTo(a.get(j+1))<0){
					//System.out.println("old elements are "+ a.get(j).getPageEntry().getPageName() + " and "+ a.get(j+1).getPageEntry().getPageName());
					SearchResult temp = a.get(j);
					a.set(j,a.get(j+1));
					a.set(j+1,temp);
					//System.out.println("new elements are "+ a.get(j).getPageEntry().getPageName() +" and "+ a.get(j+1).getPageEntry().getPageName());
				}
			}
		}
	}
}
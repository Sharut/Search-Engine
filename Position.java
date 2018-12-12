import java.util.*;

public class Position{
	public PageEntry Myentry;
	public int MywordIndex;
	public Position(PageEntry p, int wordIndex) { //	Constructor method
		Myentry = p;
		MywordIndex = wordIndex;
	}

	public PageEntry getPageEntry(){
		return Myentry;
	} 
    public int getWordIndex() {
    	return MywordIndex;
    }
}
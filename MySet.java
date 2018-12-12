import java.util.*;

public class MySet <X> extends MyLinkedList <X>{
	public MyLinkedList <X> list;

    public MySet () {
    	list = new MyLinkedList <X>(); 
    } // constructs an initially empty set

    public int size(){
    	return list.size();
    }

    public Boolean IsEmpty()
    {
        return list.isEmpty();
    }

    public Boolean IsMember(X a){
        return list.isMember(a);

    }

    public void addElement(X o){
    	if(list.isMember(o)==false)
			list.addLast(o); 
    }

    public void Delete(X o){
    	if(list.isMember(o)==false)
    		throw new java.util.NoSuchElementException("element doesn't belong to the set");
    	else
    		list.DeleteAny(o);		
    }

    public MySet Union(MySet <X> a){
    	MySet <X> firstSet = new MySet <X>();
    	Node am = this.list.first(); 
    	Node amm = a.list.first();
    	if(am!=null){
    		while(am!=null){
    			firstSet.addElement(am.getElement());
    			am=am.getNext();
    		}
    	}

    	if(amm!=null){
    		while(amm!=null){
    			firstSet.addElement(amm.getElement());
    			amm=amm.getNext();
    		}
    	}

    	return firstSet;
    }

    public MySet intersection(MySet <X> a){
    	MySet <X> secondSet = new MySet <X>();
        if(a.size()==0 || this.size()==0){
            //System.out.println("bruhhhhh");
            return null;
        }
    	Node ala = this.list.first();
    	while(ala!=null){
    		if(a.IsMember(ala.getElement())==true){
                //System.out.println("bruh");
    			secondSet.addElement(ala.getElement());
            }
    		ala=ala.getNext();
    	}
    	return secondSet;
    }

    public void Display(){
    	Node hum = this.list.first();
    	if(hum==null)
    		return ;
    	while(hum.getNext()!=null){
    		System.out.print(hum.getElement() + ",");
    		hum=hum.getNext();
    	}
    	System.out.print(hum.getElement());
    	System.out.println();

    }
  
/*
    public static void main(String args[])
    {
    	MySet hello = new MySet();
    	MySet hey = new MySet();
    	hey.addElement(2);
    	hey.addElement(1);
    	hey.addElement(5);
    	hello.addElement(5);
    	hello.addElement(6);
    	hello.addElement(7);
    	System.out.println(hello.IsMember(9));
    	hello.Delete(6);
    	hello.addElement(8);
    	hello.Display();
    	hey.Display();
    	MySet bae= hello.intersection(hey);
    	bae.Display();
    	System.out.println(hello.size());


    }*/
}













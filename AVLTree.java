import java.util.Scanner;
 
 /* Class AVLNode */
class AVLNode{    
 	AVLNode leftChild, rightChild, parent;
    Position info;
    int height;
    public AVLNode()  { //constructor
    	leftChild = null;
        rightChild = null;
        info = new Position(null,0);
        height = 0;
     }
	public AVLNode(Position n){	//constructor
        info = n;
     }

    public AVLNode getLeftChild(){
    	return leftChild;
    }

    public AVLNode getRightChild(){
    	return rightChild;
    }

    public AVLNode getParent(){
    	return parent;
    }

    public boolean isExternal(){
    	return (this.leftChild==null && this.rightChild==null);
    }
}

 public class AVLTree {
	public AVLNode myRoot;
	public AVLTree(){
		myRoot=null;
	}
	public AVLNode getRoot(){
		return myRoot;
	}

	public Boolean isEmpty(){
		return myRoot==null;
	}

	public int getHeight(AVLNode nodes){
		if(nodes==null)
			return -1;
		else
			return nodes.height;
	}
	public AVLNode search(AVLNode root, int data){
		AVLNode counter = root;
		if(counter == null)
			return null;
		else{
			if(counter.info.getWordIndex() == data)
				return counter;
			else{
				if(counter.info.getWordIndex() < data)
					return this.search(counter.rightChild,data);
				else
					return this.search(counter.leftChild,data);
			}
		}
	}

	public AVLNode search(int val)
     {
        return this.search(myRoot, val);
         
     }

 

     public boolean checkBalance(AVLNode yo){
        int height1 = getHeight(yo.leftChild);
        int height2= getHeight(yo.rightChild);
        //System.out.println("heights in check balance "+ height1 +" "+ height2);
        return Math.abs(height1-height2) <= 1;
     }

     public void addChildToNode(AVLNode pa, AVLNode baby){
     	if(pa==null)
     		System.out.println("parent doesnt exist");
     	else if ( baby == null)
     		System.out.println("Child doesnt exist");
     	else if ( pa!=null && baby!=null){
     		if(pa.info.getWordIndex()<baby.info.getWordIndex()){
     			pa.rightChild = baby;
     			baby.parent=pa;
     		}
     		else{
     			pa.leftChild=baby;
     			baby.parent=pa;
     		}
     	}

     }

     public AVLNode getChildInRotation(AVLNode grandfather, AVLNode father){
     	if(getHeight(father.leftChild)>getHeight(father.rightChild))
     		return father.leftChild;
     	else if(getHeight(father.leftChild)<getHeight(father.rightChild))
     		return father.rightChild;
     	else if(getHeight(father.leftChild)==getHeight(father.rightChild)){
     		if(grandfather!=null){
     			if(grandfather.getLeftChild()==father)
     				return father.leftChild;
     			else 
     				return father.rightChild;
     		}
     	}
     	return null;

     }


    public void updateHeight(AVLNode hello){
        int height1 = getHeight(hello.getLeftChild());
        int height2= getHeight(hello.getRightChild());
        hello.height = 1+ Math.max(height1,height2);
    }



     public void rotation(AVLNode node){
     	AVLNode grandpa = node.getParent();
     	AVLNode godfather = grandpa.getParent();
     	AVLNode childNode = getChildInRotation(grandpa, node);
     	if(godfather==null){
     		if(grandpa.getLeftChild()==node){
     			AVLNode node4 = grandpa.getRightChild();
     			if(node.getLeftChild()==childNode){
     				AVLNode node1 = childNode.getLeftChild();
     				AVLNode node2 = childNode.getRightChild();
     				AVLNode node3 = node.getRightChild();
     				myRoot=node;
     				node.parent = godfather;
     				node.rightChild = grandpa;
     				grandpa.parent =node;
     				grandpa.leftChild = node3;
     				grandpa.rightChild= node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);
     			}

     			else{
     				AVLNode node1 = node.getLeftChild();
     				AVLNode node2 = childNode.getLeftChild();
     				AVLNode node3 = childNode.getRightChild();
     				myRoot=childNode;
     				childNode.parent = godfather;
     				childNode.rightChild = grandpa;
     				grandpa.parent = childNode;
     				childNode.leftChild = node;
     				node.parent = childNode;
     				node.leftChild = node1;
     				node.rightChild = node2;
     				grandpa.leftChild = node3;
     				grandpa.rightChild = node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);
     			}
     		}
     	
     		else{
     			AVLNode node1 = grandpa.getLeftChild();
     			if(node.getLeftChild()==childNode){
     				AVLNode node2 = childNode.getLeftChild();
     				AVLNode node3 = childNode.getRightChild();
     				AVLNode node4 = node.getRightChild();
     				myRoot=childNode;
     				childNode.parent = godfather;
     				childNode.rightChild = node;
     				node.parent = childNode;
     				childNode.leftChild = grandpa;
     				grandpa.parent = childNode;
     				grandpa.leftChild = node1;
     				grandpa.rightChild = node2;
     				node.leftChild = node3;
     				node.rightChild = node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);
     			}

     		
     			else{
     				AVLNode node2 = node.getLeftChild();
     				AVLNode node3 = childNode.getLeftChild();
     				AVLNode node4 = childNode.getRightChild();
     				//System.out.println("z "+ grandpa.info +" x "+ node.info +" y "+ childNode.info);
     				myRoot=node;
     				node.parent = godfather;
     				node.rightChild = childNode;
     				childNode.parent =node;
     				node.leftChild = grandpa;
     				grandpa.parent = node;
     				grandpa.leftChild=node1;
     				grandpa.rightChild = node2;
     				childNode.leftChild=node3;
     				childNode.rightChild = node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);
     			}
     			
     		}
     	}
     	else{
     	if(grandpa.getLeftChild()==node){
     		AVLNode node4 = grandpa.getRightChild();
     		if(node.getLeftChild()==childNode){
     			AVLNode node1 = childNode.getLeftChild();
     			AVLNode node2 = childNode.getRightChild();
     			AVLNode node3 = node.getRightChild();
     			if(godfather.getRightChild()==grandpa){
     				godfather.rightChild= node;
     				node.parent = godfather;
     				node.rightChild = grandpa;
     				grandpa.parent =node;
     				grandpa.leftChild = node3;
     				grandpa.rightChild= node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);

     			}
     			else{
     				godfather.leftChild= node;
     				node.parent = godfather;
     				node.rightChild = grandpa;
     				grandpa.parent =node;
     				grandpa.leftChild = node3;
     				grandpa.rightChild= node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);
     			}

     		}
     		else{
     			AVLNode node1 = node.getLeftChild();
     			AVLNode node2 = childNode.getLeftChild();
     			AVLNode node3 = childNode.getRightChild();
     			if(godfather.getRightChild()==grandpa){
     				godfather.rightChild= childNode;
     				childNode.parent = godfather;
     				childNode.rightChild = grandpa;
     				grandpa.parent = childNode;
     				childNode.leftChild = node;
     				node.parent = childNode;
     				node.leftChild = node1;
     				node.rightChild = node2;
     				grandpa.leftChild = node3;
     				grandpa.rightChild = node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);
     			}
     			else{
     				godfather.leftChild= childNode;
     				childNode.parent = godfather;
     				childNode.rightChild = grandpa;
     				grandpa.parent = childNode;
     				childNode.leftChild = node;
     				node.parent = childNode;
     				node.leftChild = node1;
     				node.rightChild = node2;
     				grandpa.leftChild = node3;
     				grandpa.rightChild = node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);
     			}
     		}
     	}

     	else{
     		AVLNode node1 = grandpa.getLeftChild();
     		if(node.getLeftChild()==childNode){
     			AVLNode node2 = childNode.getLeftChild();
     			AVLNode node3 = childNode.getRightChild();
     			AVLNode node4 = node.getRightChild();
     			if(godfather.getRightChild()==grandpa){
     				godfather.rightChild = childNode;
     				childNode.parent = godfather;
     				childNode.rightChild = node;
     				node.parent = childNode;
     				childNode.leftChild = grandpa;
     				grandpa.parent = childNode;
     				grandpa.leftChild = node1;
     				grandpa.rightChild = node2;
     				node.leftChild = node3;
     				node.rightChild = node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);
     			}
     			else{
     				godfather.leftChild = childNode;
     				childNode.parent = godfather;
     				childNode.rightChild = node;
     				node.parent = childNode;
     				childNode.leftChild = grandpa;
     				grandpa.parent = childNode;
     				grandpa.leftChild = node1;
     				grandpa.rightChild = node2;
     				node.leftChild = node3;
     				node.rightChild = node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);
     			}

     		}
     		else{
     			AVLNode node2 = node.getLeftChild();
     			AVLNode node3 = childNode.getLeftChild();
     			AVLNode node4 = childNode.getRightChild();
     			if(godfather.getRightChild()==grandpa){
     				godfather.rightChild= node;
     				node.parent = godfather;
     				node.rightChild = childNode;
     				childNode.parent =node;
     				node.leftChild = grandpa;
     				grandpa.parent = node;
     				grandpa.leftChild=node1;
     				grandpa.rightChild = node2;
     				childNode.leftChild=node3;
     				childNode.rightChild = node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);
     			}
     			else{
     				godfather.leftChild= node;
     				node.parent = godfather;
     				node.rightChild = childNode;
     				childNode.parent =node;
     				node.leftChild = grandpa;
     				grandpa.parent = node;
     				grandpa.leftChild=node1;
     				grandpa.rightChild = node2;
     				childNode.leftChild=node3;
     				childNode.rightChild = node4;
     				updateHeight(grandpa);
     				updateHeight(node);
     				updateHeight(childNode);
     			}

     		}
     	}
     }
    }

    public AVLNode BSTInsertNode(Position key){ 		//inserts in BST and returns the node to which inserion took place
    	AVLNode bstNode = new AVLNode(key);
    	AVLNode start = myRoot;
    	AVLNode index = myRoot;
    	if(start==null){
    		myRoot =  bstNode;
    		//System.out.println(myRoot.info);
    		return null;
    	}
    	while(start!=null){
    		index=start;
    		if(key.getWordIndex() <= start.info.getWordIndex())
    			start=start.leftChild;
    		else
    			start=start.rightChild;
    	}

    	this.addChildToNode(index,bstNode);
    	//System.out.println("insertion at "+ start.info);
    	//System.out.println(start.rightChild.info);
    	return index;
    }

    public void AVLinsertNode(Position data1){
		AVLNode my = BSTInsertNode(data1);
		while(my!=null && checkBalance(my)){
			//System.out.println("bru");
			//System.out.println(this.getHeight(my)+" yooo ");
			updateHeight(my);
			//System.out.println(this.getHeight(my)+" yooo ");
			my=my.parent;
		}


		if(my!=null){
			//System.out.println("node of imbalance "+ my.info);
			//System.out.println("node of imbalance 1 "+ my.parent);
			
			AVLNode bello = getChildInRotation(my.parent,my);
			//System.out.println("node of imbalance child  "+ bello.info);
			rotation(bello);
		}
    }

    public void inorderTraversal(){
    	 inorderA(myRoot);
    }

    public void inorderA(AVLNode counters){
    	MyLinkedList<Position> inorderlist = new MyLinkedList<Position>();
    	if(counters==null){
    		//System.out.println("");
    		return ;
    	}
    	inorderA(counters.leftChild);
    	System.out.print(counters.info.getWordIndex() +", ");
    	//inorderlist.addLast(counters.info);
    	inorderA(counters.rightChild);


    }

    public void preorderTraversal(){
    	preorderA(myRoot);
    }

    public void preorderA(AVLNode counters){

    	if(counters==null)
    		return;
    	
    	System.out.print(counters.info.getWordIndex() +", ");
    	preorderA(counters.leftChild);
    	preorderA(counters.rightChild);
    }

    public AVLNode inOrderSuccessor(AVLNode mm){
    	AVLNode mysuccessor = null;
    	if(mm.rightChild != null)
        {
            mysuccessor = mm.rightChild;
            while(mysuccessor.leftChild != null)
            {
                mysuccessor = mysuccessor.leftChild;
            }
            return mysuccessor;
        }
        mysuccessor=mm.parent;
        while(mysuccessor != null && mm==mysuccessor.rightChild)
        {
            mm=mysuccessor;
            mysuccessor=mysuccessor.parent;
        }
        return mysuccessor;

    	/*Position successor = null;
    	MyLinkedList<Position>.Node searchlistnode = this.inorderTraversal().first();
    	System.out.println(inorderTraversal().size());
    	if(searchlistnode==null){
    		System.out.println("oh! empty");
    		return null;
    	}

    	int flag=1;
    	while(searchlistnode!=null && flag==1){
    		System.out.println("started");
    		if(searchlistnode.getElement()==mm){
    			System.out.println("found");
    			flag=0;
    		}

    		searchlistnode=searchlistnode.getNext();
    	}
    	if(searchlistnode==null)
    		return null;
    	return searchlistnode.getElement();*/
    }




	/*public static void main(String[] args){
		AVLTree myTree = new AVLTree();
		myTree.AVLinsertNode(10);
		myTree.preorderTraversal();
		System.out.println("");

		myTree.AVLinsertNode(20);
		myTree.preorderTraversal();
		System.out.println("");
		

		myTree.AVLinsertNode(30);
		myTree.preorderTraversal();
		System.out.println("");

		System.out.println("hello");
		myTree.AVLinsertNode(40);
		myTree.preorderTraversal();
		System.out.println("");

		myTree.AVLinsertNode(50);
		myTree.preorderTraversal();
		System.out.println("");
		
		myTree.AVLinsertNode(25);
		myTree.preorderTraversal();
		System.out.println("");
		
		myTree.AVLinsertNode(32);
		myTree.preorderTraversal();
		System.out.println("");
		
		myTree.AVLinsertNode(8);
		myTree.preorderTraversal();
		System.out.println("");
		

		myTree.AVLinsertNode(42);
		myTree.preorderTraversal();
		System.out.println("");
		

		myTree.AVLinsertNode(26);
		myTree.preorderTraversal();
		System.out.println("");
		
	}*/
}















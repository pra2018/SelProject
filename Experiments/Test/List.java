package Test;

class Node{
	public String data;
	public Node next;
	public Node head;
}

public	class List extends Node{
	public void addElement(String d){
		Node node=new Node();
		node.data=d;
		node.next=null;
		if(head==null){
			head=node;
		}else{
			Node n=head;
			while(n.next!=null){
				n=n.next;
			}
			n.next=node;
		}
	}
	public void showList(){
		Node n=head;
		do{
			System.out.println(n.data);
			n=n.next;

		}while(n.next!=null);

		System.out.println(n.data);

	}   

	public void deleteElement(String d){
	  Node n=head;
		Node prevNodeAddr = head;

		while(n.next!=null){
			
		     if(n.data==d){
			 prevNodeAddr.next=n.next;
		     }
		     prevNodeAddr=n;
			  n=n.next;
			}
		  }
	
}
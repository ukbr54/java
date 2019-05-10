package linkedlist;

public class LinkedList<T> {

    private Node<T> head;
    private int length;

    public void insertAtBegin(Node<T> node){
        node.setNext(head);
        this.head = node;
        length++;
    }

    @Override
    public String toString() {
        String result = "[";
        if(head == null) return result + "]";
        result = result + head.getData();
        Node temp = head.getNext();
        while(temp != null){
            result = result + "," + temp.getData();
            temp = temp.getNext();
        }
        return result + "]";
    }
}

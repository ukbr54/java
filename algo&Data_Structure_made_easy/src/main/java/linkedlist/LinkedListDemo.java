package linkedlist;

public class LinkedListDemo {

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();
        list.insertAtBegin(new Node<>(10));
        list.insertAtBegin(new Node<>(9));
        list.insertAtBegin(new Node<>(8));

        System.out.println(list);
    }
}

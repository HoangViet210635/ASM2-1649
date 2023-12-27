package fgw.Assignment_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements QueueADT<E> {

    private static class Node<E> {
        private final E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }
    private Node<E> head;
    private int size;

    public LinkedListQueue() {
        head = null;
    }

    @Override
    public void enqueue(E data) {
        Node<E> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E data = head.element;
        if (size == 1) {
            head = null;
        } else {
            Node<E> next = head.next;
            head.next = null;
            head = next;
        }
        size--;
        return data;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = head;

            public boolean hasNext() {
                return current != null;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    @Override
    public String toString( ) {
        StringBuilder result = new StringBuilder();
        Node<E> current = head;
        while ( current != null ) {
            result.append( current.element );
            if ( current.next != null ) {
                result.append( "| " );
            }
            current = current.next;
        }
        return result.toString( );
    }
}

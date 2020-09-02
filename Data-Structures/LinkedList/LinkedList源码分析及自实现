package com.tz;

@SuppressWarnings("unchecked")
public class LinkedList<E> extends AbstractList<E>{
     private Node<E> first;
     public static class Node<E> {
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}
	@Override
	public void clear() {
         size = 0;
         first = null;
	}
	@Override
	public E get(int index) {
		return getNode(index).element;
	}
	@Override
	public E set(int index, E element) {
		Node<E> node = getNode(index);
		E old = node.element;
		node.element = element;
		return old;
	}
	@Override
	public int indexOf(E element) {
		Node<E> node = first;
		if (element == null) {
			   for (int i = 0; i < size; i++) {
				if (node.element == null) {
					//找出第一个为空的对象即可
					node = node.next;
					return i;
				}
			}
		}else {
			for (int i = 0; i < size; i++) {
				if (node.element.equals(element)) {
					return i;
				}
		    }
		} 
		     return DEAFULT_NOT_FOUND;
	}
	@Override
	public E remove(int index) {
		rangCheck(index);
		Node<E> node = first;
		if (index == 0) {
			first = first.next;
		}else {
			Node<E> prev = getNode(index - 1);
			node = prev.next;
			prev.next = prev.next.next;
		}
		return node.element;
	}
	@Override
	public void add(int index, E element) {
		rangCheckForAdd(index);
       	 if (index == 0) {
       		first = new Node<>(element, first);
		}else {
			Node<E> prev = getNode(index - 1); 
			prev.next = new Node<>(element, prev.next);
		}
       	 size++;
 	}
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		Node<E> node = first;
		   while (node != null) {
			   sBuilder.append(node.element+ " ");
				 node = node.next;
		}
			
		   return sBuilder.toString();
	}
	/*
	 * 获取相应位置节点
	 * */
	private Node<E> getNode(int index) {
		rangCheck(index);
		Node<E> node = first;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
} 

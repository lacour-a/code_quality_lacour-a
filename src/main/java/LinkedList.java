package main.java;

public final class LinkedList<T extends Comparable<T>> {
  public static final int INDEX_NOT_FOUND = -1;

  class Node {
    public T value;
    public Node next;
    public Node prev;

    public Node(T value, Node next, Node prev) {
      this.value = value;
      this.next = next;
      this.next = prev;
    }

    public Node(T value) {
      this(value, null, null);
    }

    public boolean hasNext() {
      return this.next != null;
    }

    public boolean hasPrev() {
      return this.prev != null;
    }

    public void swapWith(Node otherNode) {
      T temporary = this.value;
      this.value = otherNode.value;
      otherNode.value = temporary;
    }
  }

  public class Iterator {
    private Node node;

    public Iterator(Node node) {
      this.node = node;
    }

    public void advance(int pos) {
      if (pos < 0) {
        this.goBack(-pos);
      } else {
        while (pos != 0) {
          this.node = this.node.next;
          --pos;
        }
      }
    }

    public void goBack(int pos) {
      if (pos < 0) {
        this.advance(-pos);
      } else {
        while (pos != 0) {
          this.node = this.node.prev;
          --pos;
        }
      }
    }
  }

  public LinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }


  // Iterators

  public Iterator begin() {
    return new Iterator(this.head);
  }

  public Iterator end() {
    return new Iterator(null);
  }


  // Accessors

  public T head() {
    return this.head.value;
  }

  public T tail() {
    return this.tail.value;
  }

  public T getAtPosition(int pos) throws IndexOutOfBoundsException {
    if (pos >= this.size || pos < 0) {
      throw new IndexOutOfBoundsException();
    }
    Iterator it = this.begin();
    it.advance(pos);
    return it.node.value;
  }

  public int findFirst(T value) {
    return this.findFirstFromPosition(value, 0);
  }

  public int findNthPosition(T value, int nth) throws IllegalArgumentException {
    if (nth == 0) {
      throw new IllegalArgumentException();
    }

    int currentPosition = 0;
    for (int i = 0; i < nth; ++i, ++currentPosition) {
      currentPosition = this.findFirstFromPosition(value, currentPosition);
      if (currentPosition == INDEX_NOT_FOUND) {
        return INDEX_NOT_FOUND;
      }
    }
    return INDEX_NOT_FOUND;
  }

  public int findFirstFromPosition(T value, int startPosition) {
    Iterator it = this.begin();
    it.advance(startPosition);
    final Iterator end_it = this.end();
    int currentPosition = startPosition;
    while (it != end_it) {
      if (it.node.value.equals(value)) {
        return currentPosition;
      }
      it.advance(1);
      ++currentPosition;
    }
    return INDEX_NOT_FOUND;
  }


  // Size

  public int size() {
    return this.size;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }


  // Modifiers

  public void addHead(T value) {
    Node newHead = new Node(value, head, null);
    this.head = newHead;
    ++this.size;
  }

  public void addTail(T value) {
    Node newTail = new Node(value);
    this.tail.next = newTail;
    newTail.prev = this.tail;
    this.tail = newTail;
    ++this.size;
  }

  public void addAtPosition(T value, int pos) throws IndexOutOfBoundsException {
    if (pos > this.size || pos < 0) {
      throw new IndexOutOfBoundsException();
    } else if (pos == 0) {
      this.addHead(value);
    } else if (pos == this.size) {
      this.addTail(value);
    } else {
      this.addInTheMiddle(value, pos);
    }
  }

  private void addInTheMiddle(T value, int pos) {
    Iterator itBeforeInsertion = this.begin();
    Node newNode = new Node(value);
    itBeforeInsertion.advance(pos - 1);
    Node nodeBeforeInsertion = itBeforeInsertion.node;
    newNode.next = nodeBeforeInsertion.next;
    newNode.prev = nodeBeforeInsertion;
    nodeBeforeInsertion.next = newNode;
    ++this.size;
  }

  public void removeAtPosition(int pos) {
    Iterator removeIt = this.begin();
    removeIt.advance(pos);
    this.removeAt(removeIt);
  }

  public Iterator removeAt(Iterator it) {
    Node nodeToRemove = it.node;
    if (nodeToRemove.hasNext()) {
      nodeToRemove.next.prev = nodeToRemove.prev;
    }
    if (nodeToRemove.hasPrev()) {
      nodeToRemove.prev.next = nodeToRemove.next;
    }
    --this.size;
    return new Iterator(nodeToRemove.next);
  }

  public void removeValue(T value) {
    Iterator it = this.begin();
    while (it != this.end()) {
      if (it.node.value.equals(value)) {
        it.advance(1);
      } else {
        it = this.removeAt(it);
      }
    }
  }

  public void sortAscending() {
    while (this.doOneBubbleSortIteration()) {}
  }

  // Returns true when the container has changed.
  private boolean doOneBubbleSortIteration() {
    Node currentNode = this.head;
    boolean hasChanged = false;
    while (currentNode.next != null) {
      if (currentNode.value.compareTo(currentNode.next.value) > 0) {
        currentNode.swapWith(currentNode.next);
        hasChanged = true;
      }
      currentNode = currentNode.next;
    }
    return hasChanged;
  }

  private Node head;
  private Node tail;
  private int size;
}
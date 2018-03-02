public final class LinkedList<T> {
    static public final int INDEX_NOT_FOUND = -1;

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
    }

    public class Iterator {
        private Node node;

        public Iterator(Node node) {
            this.node = node;
        }

        public void advance(int pos) {
            if (pos < 0)
                this.advance(-pos);
            else {
                while (pos != 0) {
                    this.node = this.node.next;
                    --pos;
                }
            }
        }

        public void goback(int pos) {
            if (pos < 0)
                this.advance(-pos);
            else {
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
        if (pos >= this.size || pos < 0)
            throw new IndexOutOfBoundsException();
        Iterator it = this.begin();
        it.advance(pos);
        return it.node.value;
    }

    public int findFirst(T value) {
        return this.findFirstFromPosition(value, 0);
    }

    public int findNthPosition(T value, int nth) throws IllegalArgumentException {
        if (nth == 0)
            throw new IllegalArgumentException();

        int current_position = 0;
        for (int i = 0; i < nth; ++i, ++current_position) {
            current_position = this.findFirstFromPosition(value, current_position);
            if (current_position == INDEX_NOT_FOUND)
                return INDEX_NOT_FOUND;
        }
        return INDEX_NOT_FOUND;
    }

    public int findFirstFromPosition(T value, int start_position) {
        Iterator it = this.begin();
        it.advance(start_position);
        final Iterator end_it = this.end();
        int current_position = start_position;
        while (it != end_it) {
            if (it.node.value.equals(value))
                return current_position;
            it.advance(1);
            ++current_position;
        }
        return INDEX_NOT_FOUND;
    }


    // Modifiers

    public void addHead(T value) {
        Node new_head = new Node(value, head, null);
        this.head = new_head;
        ++this.size;
    }

    public void addTail(T value) {
        Node new_tail = new Node(value);
        this.tail.next = new_tail;
        new_tail.prev = this.tail;
        this.tail = new_tail;
        ++this.size;
    }

    public void addAtPosition(T value, int pos) throws IndexOutOfBoundsException {
        if (pos > this.size || pos < 0)
            throw new IndexOutOfBoundsException();
        else if (pos == 0)
            this.addHead(value);
        else if (pos == this.size)
            this.addTail(value);
        else
            this.addInTheMiddle(value, pos);
    }

    private void addInTheMiddle(T value, int pos) {
        Iterator it_before_insertion = this.begin();
        Node new_node = new Node(value);
        it_before_insertion.advance(pos - 1);
        Node node_before_insertion = it_before_insertion.node;
        new_node.next = node_before_insertion.next;
        new_node.prev = node_before_insertion;
        node_before_insertion.next = new_node;
        ++this.size;
    }

    public void removeAtPosition(int pos) {
        Iterator remove_it = this.begin();
        remove_it.advance(pos);
        this.removeAt(remove_it);
    }

    public Iterator removeAt(Iterator it) {
        Node node_to_remove = it.node;
        if (node_to_remove.hasNext())
            node_to_remove.next.prev = node_to_remove.prev;
        if (node_to_remove.hasPrev())
            node_to_remove.prev.next = node_to_remove.next;
        --this.size;
        return new Iterator(node_to_remove.next);
    }

    public void removeValue(T value) {
        Iterator it = this.begin();
        while (it != this.end()) {
            if (it.node.value.equals(value))
                it.advance(1);
            else
                it = this.removeAt(it);
        }
    }

    private Node head;
    private Node tail;
    private int size;
}
package DataStructure.List;

import java.nio.file.NotDirectoryException;

/**
 * 考虑到设计，我们需要提供三个类：
 * 1. _LinkedList类本身，包含到两端的链、表的大小以及一些方法
 * 2. _Node类，它可能时一个私有的嵌套类。一个节点包含数据以及到前一个节点的链和到下一个节点的链，还有一些适当的构造方法
 * 3. _LinkedListIterator类，该类抽象了位置的概念，是一个私有类，并实现接口Iterator，它提供了方法next、hasNext和remove的实现
 * */
public class _LinkedList<AnyType> implements Iterable<AnyType>{
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    private static class Node<AnyType> {
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d; prev = p; next = n;
        }

        private AnyType data;
        private Node<AnyType> prev;
        private Node<AnyType> next;
    }

    public _LinkedList() { doClear(); }
    public void clear() { doClear(); }
    public int size() { return theSize; }
    public boolean isEmpty() { return size() == 0; }

    public void doClear() {
        beginMarker = new Node<AnyType>(null, null, null);
        endMarker = new Node<AnyType>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }
    public void add(int idx, AnyType x) {
        addBefore(getNode(idx, 0, size()), x);
    }
    public AnyType get(int idx) { return getNode(idx).data; }
    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    /**
     * 在集合的特定位置p添加新元素
     * 将此处以及此处之后的元素都往前一个位置
     * @param p 要添加的元素
     * @param x 数据
     * @throws IndexOutOfBoundsException 索引不在0到size()之内抛出异常
     * */
    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    /**
     * 删除节点p包含的数据
     * @param p 指定的节点的数据
     * @return 删除的节点
     * */
    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * 获取指定位置的节点，但是要保证指定的范围正确
     * @param idx 指定的位置
     * @return 根据位置找到的节点
     * @throws IndexOutOfBoundsException 超出集合的大小
     * */
    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    /**
     * 在指定的集合范围内获取指定位置的节点
     * @param idx 搜索的节点的下标
     * @param lower 指定最小值
     * @param upper 指定最大值
     * @return 对应的节点
     * @throws IndexOutOfBoundsException 如果指定位置不在指定范围内
     * */
    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p;

        if(idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();

        if(idx < size() / 2) {
            p = beginMarker.next;
            for(int i = 0; i < idx; i++)
                p = p.next;
        }
        else {
            p = endMarker;
            for(int i = size(); i > idx; i--)
                p = p.prev;
        }

        return p;
    }

    public java.util.Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements java.util.Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() { return current != endMarker; }

        public AnyType next() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();

            _LinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

}

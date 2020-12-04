package DataStructure.List;

/**
 * 1. _ArrayList将保持基础数组，数组的容量，以及存储在_ArrayList中的当前项数
 * 2. _ArrayList将提供一种机制以改变基础数组的容量，通过获得一个新的数组，将老数组拷贝到新数组中来改变数组的容量，允许虚拟机回收老数组
 * 3. _ArrayList将提供get和set的实现
 * 4. _ArrayList将提供基本的例程，如size，isEmpty和clear，它们是典型的单行程序；还提供remove和两种不同的add。如果数组的大小和容量相同，那么这两个add例程将增加容量
 * 5. _ArrayList将提供一个Iterator接口的类。这个类将存储迭代序列的下一项的下标，并提供next，hasNext和remove等方法的实现。_ArrayList的迭代器方法直接返回实现Iterator接口的该类的新构造的实例。
 *
 * */

public class _ArrayList<AnyType> implements Iterable<AnyType> {
    private  static final int DEFAULT_CAPACITY = 10;    // 默认容量
    private int theSize;    // 大小
    private AnyType[] theItems; // 项数组

    public _ArrayList() { doClear(); }
    public void clear() { doClear(); }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() { return theSize; }

    public boolean isEmpty() { return size() == 0; }

    public void trimToSize() { ensureCapacity(size()); }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) return;

        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for(int i = 0; i < size(); i++)
            theItems[i] = old[i];
    }

    public AnyType get(int idx){
        if(idx < 0 || idx >= size())
            throw  new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public AnyType set(int idx, AnyType newVal) {
        if(idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();

        AnyType old = theItems[idx];
        theItems[idx] = newVal;

        return old;
    }

    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x) {
        if(theItems.length == size())
            ensureCapacity(size() * 2 + 1);
        for(int i = theSize; i > idx; i--)
            theItems[i] = theItems[i - 1];
        theItems[idx] = x;

        theSize++;
    }

    public AnyType remove(int idx) {
        AnyType removedItem = theItems[idx];
        for(int i = idx; i < size() - 1; i++)
            theItems[i] = theItems[i + 1];

        theSize--;
        return removedItem;
    }

    public java.util.Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements java.util.Iterator<AnyType> {
        private int current = 0;

        public boolean hasNext() { return current < size(); }

        public AnyType next() {
            if(!hasNext())
                throw new java.util.NoSuchElementException();

            return theItems[current++];
        }

        public void remove() {
            _ArrayList.this.remove(--current);
        }
    }
}

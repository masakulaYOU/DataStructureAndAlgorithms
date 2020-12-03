## `Collection`接口

`Collection`接口位于`java.util`包

```java
public interface Collection<AnyType> extends Iterable<AnyType>
{
    int size(); // 返回集合中的项数
    boolean isEmpty();  // 返回true当且仅当集合的大小为0
    void clear();       // 清空集合中的所有元素
    boolean contains();
    boolean add();
    boolean remove(AnyType x);
    java.util.Iterator<AnyType> iterator();
}
```
## `Iterator`接口
实现`Iterator`接口的集合必须提供一个称为`iterator`的方法，该方法返回一个`Iterator`类型的对象，改`Iterator`是一个在`java.util`包中定义的接口。
```java
public interface Iterator<AnyType>
{
    boolean hasNext();
    AnyType next();
    void remove();
}
```

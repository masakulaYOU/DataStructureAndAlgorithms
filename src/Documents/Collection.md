## `Collection`接口

`Collection`接口位于`java.util`包

`Collection`接口的一些重要部分

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

`Collection`接口扩展了`Iterable`接口。

实现`Iterable`接口的那些类可以用用增强`for`循环，该循环施于这些类之上以观察他们所有的项

```java
/* 在Iterable类型上使用增强的for循环 */
public static <AnyType> void print(Collection<AnyType> coll) {
    for (AnyType item : coll)
        System.out.println(item);
}
```

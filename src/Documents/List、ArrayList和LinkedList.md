## `List`接口、`ArrayList`类和`LinkedList`类

表（`List`），由`java.util`包中的`List`接口指定。

`List`接口继承了`Collection`接口，包含`Collection接口的所有方法`，还有一些自己的方法

```java
public interface List<AnyType> extends Collection<AnyType> {
    AnyType get(int idx);
    AnyTYpe set(int idx, AnyType newVal);
    void add(int idx, AnyType x);
    void remove(int idx);
    
    ListIterator<AnyType> listIterator(int pos);
}
```

`get`和`set`使得用户可以访问或改变通过由位置索引`idx`给定的表中的指定位置上的项。索引`0`位于表的前端，索引`size() - 1`代表表中的最后一项，而索引`size()`则表示新加的项可以被防止的位置。

`add`使得在位置`idx`处置入一个新的项（并吧其中的项向后推移一个位置）。于是，在位置`0`处`add`是在表的前端进行的添加，而在位置`size()`处的`add`是把被添加项作为新的最后项添入表中。

除以`AnyType`作为参数的标准的`remove`外，`remove`还被重载以删除指定位置上的项。

`List`接口指定`listIterator`方法，它将产生比通常认为的还要复杂的迭代器。
## `Iterator`接口
实现`Iterator`接口的集合必须提供一个称为`iterator`的方法，该方法返回一个`Iterator`类型的对象，改`Iterator`是一个在`java.util`包中定义的接口。

`Iterator`接口的思路是，通过`iterator`方法，每个集合均可创建并返回客户一个实现`Iterator`接口的对象，并将当前位置的概念在对象内部存储下来

```java
public interface Iterator<AnyType>
{
    boolean hasNext();
    AnyType next();
    void remove();
}
```

每次对`next`的调用都会给出集合的(尚未见到的)下一项。第1次调用`next`给出第1项，第2次调用给出第2项，等等。

`hasNext`用来告诉是否存在下一项。当编译器遇到一个正在用于`Iterable`的对象的增强的`for`循环的时候，它用对`iterator`方法的那些调用代替增强的`for`循环以得到一个`Iterator`对象，然后调用`next`和`hasNext`。

```java
public static <AnyType> void print(Collection<AnyType> coll) {
    Iterator<AnyType> itr = coll.iterator();
    while(itr.hasNext()) {
        AnyType item = itr.next();
        System.out.println(item);
    }
}
```

`remove`方法可以删除由`next`最新返回的项，此后，我们不能再调用`remove`，直到对`next`再一次调用以后。

`Iterator`的`remove`相对于`Collection`的`remove`的优点：`Collection`的`remove`方法必须首先找到被删除的项，当知道所要删除的项的准确位置，那么删除它的开销就会小得多。

**注意：**当直接使用`Iterator`（而不是通过一个增强的`for`循环间接使用）时，如果对正在迭代的集合进行结构上的改变(即对该集合使用`add`、`remove`或`clear`方法)，那么迭代器就不再合法，并且在其后使用该迭代器时将会又`ConcurrentModificationException`异常被抛出。
所以，只有在需要立即使用一个迭代器的时候，我们才应获取迭代器。然而，如果迭代器调用了自己的`remove`方法，那么这个迭代器仍然时合法的。
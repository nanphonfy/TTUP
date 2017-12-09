package generics;//: generics/NonCovariantGenerics.java
// {CompileTimeError} (Won't compile)

public class NonCovariantGenerics {
  // Compile Error: incompatible types:
  /**
   * 尝试使用泛型容器代替数组，Apple的List在类型上不等价于Fruit的List
   * (即使Apple是一种Fruit类型)
   */
  //List<Fruit> flist = new ArrayList<Apple>();
} ///:~

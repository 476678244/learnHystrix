package learnHystrix.builder;

//A builder for objects of type T
public interface Builder<T> {
  public T build();
}
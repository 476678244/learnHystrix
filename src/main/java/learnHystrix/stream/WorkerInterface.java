package learnHystrix.stream;

//定义一个函数式接口
@FunctionalInterface
public interface WorkerInterface {

	public void doSomeWork();

}
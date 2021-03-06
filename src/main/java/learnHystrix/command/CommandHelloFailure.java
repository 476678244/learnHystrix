package learnHystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloFailure extends HystrixCommand<String> {

  private final String name;

  public CommandHelloFailure(String name) {
    super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    this.name = name;
  }

  @Override
  protected String run() {
    System.out.println("running");
    throw new RuntimeException("this command always fails");
  }

  @Override
  protected String getFallback() {
    return "Hello Failure " + name + "!";
  }
}
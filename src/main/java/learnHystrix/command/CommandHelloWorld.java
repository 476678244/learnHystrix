package learnHystrix.command;

import rx.Observable;
import rx.Subscriber;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class CommandHelloWorld extends HystrixObservableCommand<String> {

  private final String name;

  public CommandHelloWorld(String name) {
    super(Setter
        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
        .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorld")));
    this.name = name;
  }

  @Override
  protected Observable<String> construct() {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override
      public void call(Subscriber<? super String> observer) {
        try {
          if (!observer.isUnsubscribed()) {
            // a real example would do work like a network call here
            observer.onNext("Hello");
            observer.onNext(name + "!");
            observer.onCompleted();
          }
        } catch (Exception e) {
          observer.onError(e);
        }
      }
    });
  }
}
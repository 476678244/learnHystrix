package learnHystrix.command;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class Main {

  public static void main(String[] args) {
    String s = new CommandHelloFailure("World").execute();
    System.out.println(s);


  }

}

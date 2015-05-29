package learnHystrix.command;

public class Main {

  public static void main(String[] args) {
    String s = new CommandHelloWorld("World").execute();
    System.out.println(s);
  }

}

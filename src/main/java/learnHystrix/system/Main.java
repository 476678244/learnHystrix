package learnHystrix.system;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

  public static void main(String[] args) {
    System.out.println(System.getProperty("java.vm.name"));
    System.out.println(System.getProperty("os.name"));
    System.out.println(System.getProperty("java.specification.name"));

    InetAddress ip;
    String hostname;
    try {
      ip = InetAddress.getLocalHost();
      hostname = ip.getHostName();
      System.out.println("Your current IP address : " + ip);
      System.out.println("Your current Hostname : " + hostname);

    } catch (UnknownHostException e) {

      e.printStackTrace();
    }
  }

}

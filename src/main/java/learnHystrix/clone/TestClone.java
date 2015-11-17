package learnHystrix.clone;

public class TestClone {

  public static void main(String[] args) throws CloneNotSupportedException {
    Point p = new Point();
    Point p2 = p.clone();
    System.out.print(p2);
  }

  public static class Point implements Cloneable {
  //public static class Point {
    private int x;

    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }
    
    public Point clone() throws CloneNotSupportedException {
      Object obj = super.clone();
      Class clazz = obj.getClass();
      return (Point)obj;
    }
  }

}

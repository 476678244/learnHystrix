package learnHystrix.npe;

public class Main {

  public static void main(String[] args) {
    FamilyEntity family = new FamilyEntity();
    family.getName().toString();

  }
  
  private static class FamilyEntity {
    private String name = null;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

  }

}

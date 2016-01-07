package learnHystrix.fieldholder;

public class FieldHolderTest {

  public static void main(String[] args) {
    FieldType type = getField();
    type = getField();
    System.out.println(type);
  }

  // Lazy initialization holder class idiom for static fields
  private static class FieldHolder {
    static final FieldType field = computeFieldValue();

    private static FieldType computeFieldValue() {
      return field.FIELD1;
    }
  }

  static FieldType getField() {
    return FieldHolder.field;
  }

}

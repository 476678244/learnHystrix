package learnHystrix.builder;

//Builder Pattern
public class NutritionFacts {
  private int servingSize;
  private int servings;
  private int calories;
  private int fat;
  private int sodium;
  private int carbohydrate;

  public static class Builder implements
      learnHystrix.builder.Builder<NutritionFacts> {
    // Required parameters
    private int servingSize;
    private int servings;
    // Optional parameters - initialized to default values
    private int calories = 0;
    private int fat = 0;
    private int carbohydrate = 0;
    private int sodium = 0;

    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    public Builder calories(int val) {
      calories = val;
      return this;
    }

    public Builder fat(int val) {
      fat = val;
      return this;
    }

    public Builder carbohydrate(int val) {
      carbohydrate = val;
      return this;
    }

    public Builder sodium(int val) {
      sodium = val;
      return this;
    }

    @Override
    public NutritionFacts build() {
      return new NutritionFacts(this);
    }

  }

  private NutritionFacts(){}
  
  private NutritionFacts(Builder builder) {
    servingSize = builder.servingSize;
    servings = builder.servings;
    calories = builder.calories;
    fat = builder.fat;
    sodium = builder.sodium;
    carbohydrate = builder.carbohydrate;
  }
  
  public static NutritionFacts of(Builder builder) {
    NutritionFacts obj = new NutritionFacts();
    obj.servingSize = builder.servingSize;
    obj.servings = builder.servings;
    obj.calories = builder.calories;
    obj.fat = builder.fat;
    obj.sodium = builder.sodium;
    obj.carbohydrate = builder.carbohydrate;
    return obj; 
  }
}
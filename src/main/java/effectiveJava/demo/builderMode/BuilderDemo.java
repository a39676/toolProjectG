package effectiveJava.demo.builderMode;

public class BuilderDemo {

	private int fat;
	private int calories;
	private int weight;
	private String foodName;

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public static class Builder {
		private int fat;
		private int calories;
		private int weight;
		private String foodName;

		public Builder fat(int fat) {
			this.fat = fat;
			return this;
		}

		public Builder calories(int calories) {
			this.calories = calories;
			return this;
		}

		public Builder weight(int weight) {
			this.weight = weight;
			return this;
		}

		public Builder foodName(String foodName) {
			this.foodName = foodName;
			return this;
		}

		public BuilderDemo build() {
			return new BuilderDemo(this);
		}

	}

	private BuilderDemo(Builder builder) {
		this.fat = builder.fat;
		this.calories = builder.calories;
		this.weight = builder.weight;
		this.foodName = builder.foodName;
	}

	public static void main(String[] args) {
		BuilderDemo d = new BuilderDemo.Builder().fat(1).calories(2).build();
		System.out.println(d.fat);
		System.out.println(d.foodName);
	}

}

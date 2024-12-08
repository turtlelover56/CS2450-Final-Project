
public class Intermission {
	private String prompt;
	private String optionOne;
	private String optionTwo;
	private Item optionOneItem;
	private Item optionTwoItem;
	private String resultOne;
	private String resultTwo;

	// Constructors
	public Intermission(String prompt, String optionOne, String optionTwo, String resultOne, String resultTwo) {
		this(prompt, optionOne, optionTwo, null, null, resultOne, resultTwo);
	}
	public Intermission(String prompt, String optionOne, String optionTwo, Item optionOneItem, Item optionTwoItem, String resultOne, String resultTwo) {
		this.prompt = prompt;
		this.optionOne = optionOne;
		this.optionTwo = optionTwo;
		this.optionOneItem = optionOneItem;
		this.optionTwoItem = optionTwoItem;
		this.resultOne = resultOne;
		this.resultTwo = resultTwo;
	}

	// Getters/Setters
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public String getOptionOne() {
		return optionOne;
	}
	public void setOptionOne(String optionOne) {
		this.optionOne = optionOne;
	}
	public String getOptionTwo() {
		return optionTwo;
	}
	public void setOptionTwo(String optionTwo) {
		this.optionTwo = optionTwo;
	}
	public Item getOptionOneItem() {
		return optionOneItem;
	}
	public void setOptionOneItem(Item optionOneItem) {
		this.optionOneItem = optionOneItem;
	}
	public Item getOptionTwoItem() {
		return optionTwoItem;
	}
	public void setOptionTwoItem(Item optionTwoItem) {
		this.optionTwoItem = optionTwoItem;
	}
	public String getResultOne() {
		return resultOne;
	}
	public void setResultOne(String resultOne) {
		this.resultOne = resultOne;
	}
	public String getResultTwo() {
		return resultTwo;
	}
	public void setResultTwo(String resultTwo) {
		this.resultTwo = resultTwo;
	}
	
	// toString
	@Override
	public String toString() {
		return "Intermission [prompt=" + prompt + ", optionOne=" + optionOne + ", optionTwo=" + optionTwo
				+ ", optionOneItem=" + optionOneItem + ", optionTwoItem=" + optionTwoItem + ", resultOne=" + resultOne + ", resultTwo=" + resultTwo + "]";
	}
}

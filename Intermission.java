
public class Intermission {
    private String prompt;
    private String optionOne;
    private String optionTwo;
    private Usable optionOneItem;
    private Usable optionTwoItem;
    private int countOne;
    private int countTwo;
    private String resultOne;
    private String resultTwo;

    public Intermission(String prompt, String optionOne, String optionTwo, String resultOne, String resultTwo) {
        this(prompt, optionOne, optionTwo, null, null, resultOne, resultTwo);
    }

    public Intermission(String prompt, String optionOne, String optionTwo, Usable optionOneItem, Usable optionTwoItem, String resultOne, String resultTwo) {
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
    public Usable getOptionOneItem() {
        return optionOneItem;
    }
    public void setOptionOneItem(Item optionOneItem) {
        this.optionOneItem = optionOneItem;
    }
    public Usable getOptionTwoItem() {
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
    public int getCountOne() {
        return countOne;
    }
    public void setCountOne(int countOne) {
        this.countOne = countOne;
    }
    public int getCountTwo() {
        return countTwo;
    }
    public void setCountTwo(int countTwo) {
        this.countTwo = countTwo;
    }
    
    // toString
    @Override
    public String toString() {
        return "Intermission [prompt=" + prompt + ", optionOne=" + optionOne + ", optionTwo=" + optionTwo
                + ", optionOneItem=" + optionOneItem + ", optionTwoItem=" + optionTwoItem + ", countOne=" + countOne
                + ", countTwo=" + countTwo + ", resultOne=" + resultOne + ", resultTwo=" + resultTwo + "]";
    }
}

package recruitment.hackerrank.com.hackerrankassignment.Model;

public class UserNumbers {
    private long firstNumber;
    private long secondNumber;

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = (long) firstNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = (long)secondNumber;
    }

    public long getFirstNumber() {
        return firstNumber;
    }

    public long getSecondNumber() {
        return secondNumber;
    }

    public long addAll() {
        return firstNumber + secondNumber;
    }

    public long getCount() {
        return 2;
    }

}

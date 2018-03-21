package recruitment.hackerrank.com.hackerrankassignment.database;

import android.support.annotation.NonNull;

public class UserData {

    private int id;
    private int firstNumber;
    private int secondNumber;

    public UserData(int firstNumber, int secondNumber) {
        super();
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    @Override
    public String toString() {
        return "UserData [id" + id + "firstNumber=" + firstNumber + ", secondNumber=" + secondNumber
                + "]";
    }
}
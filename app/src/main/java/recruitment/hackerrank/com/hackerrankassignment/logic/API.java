package recruitment.hackerrank.com.hackerrankassignment.logic;

import recruitment.hackerrank.com.hackerrankassignment.Model.PrestoreScores;
import recruitment.hackerrank.com.hackerrankassignment.Model.UserNumbers;
import recruitment.hackerrank.com.hackerrankassignment.database.SQLLiteHelper;

public class API {

    public static long calculateAverage(UserNumbers userEnteredNumbers, PrestoreScores prestoreScores) {
        long total = userEnteredNumbers.addAll() + prestoreScores.addAllScores();
        long scoresCount = userEnteredNumbers.getCount() + prestoreScores.getCount();
        return Math.round(total/scoresCount);
    }
}

package recruitment.hackerrank.com.hackerrankassignment.Model;

public class PrestoreScores {
    private Long [] scores;

    public PrestoreScores(Long[] scores) {
        this.scores = scores;
    }

    public long addAllScores() {
        int total = 0;
        for (Long score : scores) {
            total += score;
        }
        return total;
    }

    public int getCount() {
        return scores.length;
    }
}

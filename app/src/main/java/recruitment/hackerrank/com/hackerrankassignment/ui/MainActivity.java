package recruitment.hackerrank.com.hackerrankassignment.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import recruitment.hackerrank.com.hackerrankassignment.Model.PrestoreScores;
import recruitment.hackerrank.com.hackerrankassignment.Model.UserNumbers;
import recruitment.hackerrank.com.hackerrankassignment.R;
import recruitment.hackerrank.com.hackerrankassignment.database.SQLLiteHelper;
import recruitment.hackerrank.com.hackerrankassignment.logic.API;
import recruitment.hackerrank.com.hackerrankassignment.util.HttpHandler;

public class MainActivity extends AppCompatActivity {

    private SQLLiteHelper db;
    private PrestoreScores prestoreScores;
    private EditText firstNumberText;
    private EditText secondNumberText;
    private Button saveButton, getAverageButton;
    private TextView calculatedAverageText;
    private UserNumbers userEnteredNumbers = new UserNumbers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fetch default score on Run time
        new FetchDefaultNumbers().execute();

        // fetch user input
        fetchUserInput();

        //Save Number stores numbers in core database
        db = new SQLLiteHelper(this);
        saveButton = (Button) findViewById(R.id.saveNumber);
        saveButton.setEnabled(false);
        saveButton.setOnClickListener(setOnSaveButtonClickListener());

        // Get Average
        getAverageButton = (Button)findViewById(R.id.getAverage);
        getAverageButton.setEnabled(false);
        getAverageButton.setOnClickListener(setOnAverageButtonClickListener());
    }

    private void fetchUserInput() {
        // fetch numbers
        firstNumberText = (EditText)findViewById(R.id.first_number);
        secondNumberText = (EditText)findViewById(R.id.second_number);

        userEnteredNumbers.setFirstNumber(1);
        userEnteredNumbers.setSecondNumber(2);

        firstNumberText.addTextChangedListener(new TextWatcher() {

                                                   @Override
                                                   public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                   }

                                                   @Override
                                                   public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                       saveButton.setEnabled(true);

                                                   }

                                                   @Override
                                                   public void afterTextChanged(Editable s) {
                                                       firstNumberText.setText(s.toString() == null? "0" : s.toString());
                                                       userEnteredNumbers.setFirstNumber(Integer.parseInt(firstNumberText.getText().toString()));
                                                   }
                                               }
        );

        secondNumberText.addTextChangedListener(new TextWatcher() {

                                                    @Override
                                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                    }

                                                    @Override
                                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                        getAverageButton.setEnabled(true);
                                                    }

                                                    @Override
                                                    public void afterTextChanged(Editable s) {
                                                        secondNumberText.setText(s.toString() == null? "0" : s.toString());
                                                        userEnteredNumbers.setSecondNumber(Integer.parseInt(secondNumberText.getText().toString()));
                                                    }
                                                }
        );
    }

    private View.OnClickListener setOnSaveButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertNumbers(userEnteredNumbers);
            }
        };
    }

    private View.OnClickListener setOnAverageButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long average = API.calculateAverage(userEnteredNumbers, prestoreScores);
                calculatedAverageText.setVisibility(View.VISIBLE);
                calculatedAverageText.setText("Average is: " + average);
            }
        };
    }

    private class FetchDefaultNumbers extends AsyncTask<Void, Void, Void> {
        private String DEFAULT_SCORE_URL = "https://roktcdn1.akamaized.net/store/test/android/prestored_scores.json";
        String jsonString;
        @Override
        protected void onPreExecute() {
            // TODO: Display progress bar
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler httpHandler = new HttpHandler();
            httpHandler.makeServiceCall(DEFAULT_SCORE_URL);
            //TODO: open downloaded json
            //TODO: read the file
            jsonString = "[2147483647,300,-100, 400]";
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO: hide progress bar
            try {
                JSONObject jsonObj = new JSONObject(jsonString);
                JSONArray arrJson = jsonObj.optJSONObject("data").getJSONArray("numbers");
                Long[] scores = new Long[arrJson.length()];
                for (int i = 0; i < arrJson.length(); i++) {
                    scores[i] = arrJson.getLong(i);
                }
                prestoreScores = new PrestoreScores(scores);
            } catch (JSONException e) {
                //TODO: Handle JSON exeception
            }
        }
    }
}

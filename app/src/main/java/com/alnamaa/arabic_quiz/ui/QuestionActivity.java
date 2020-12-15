package com.alnamaa.arabic_quiz.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alnamaa.arabic_quiz.Connectivity;
import com.alnamaa.arabic_quiz.R;
import com.alnamaa.arabic_quiz.ToastMaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuestionActivity extends AppCompatActivity {

    TextView question_tv;
    Button choice1_b;
    Button choice2_b;
    Button choice3_b;
    Button choice4_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ToastMaker.initialize(this,null);

        question_tv = findViewById(R.id.question_tv);

        choice1_b = findViewById(R.id.choice1_b);
        choice2_b = findViewById(R.id.choice2_b);
        choice3_b = findViewById(R.id.choice3_b);
        choice4_b = findViewById(R.id.choice4_b);


        Connectivity.CommunicationWithAPI("https://follow.leafwaysco.com/arabic/query_question.php",
                null, new Connectivity.ResponseHandler() {
                    @Override
                    public void handleResponse(String response) {
                        ToastMaker.showMessage(response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String id         = jsonObject.getString("id");
                            String question   = jsonObject.getString("question");
                            String topics     = jsonObject.getString("topics");
                            String difficulty = jsonObject.getString("difficulty");
                            String language   = jsonObject.getString("language");
                            String type       = jsonObject.getString("type");
                            String subjects   = jsonObject.getString("subjects");
                            String created_at = jsonObject.getString("created_at");
                            JSONArray choices = jsonObject.getJSONArray("choices");

                            ((TextView)findViewById(R.id.question_tv)).setText(question);

                            for (int i=0;i<choices.length();i++){
                                JSONObject jsonObject1 = (JSONObject)choices.get(i);
                                String idc= jsonObject1.getString("id");
                                String choice = jsonObject1.getString("choice");
                                String status = jsonObject1.getString("status");

                                Button button=((Button)findViewById(R.id.choice1_b));
                                if(i==0)
                                    button=((Button)findViewById(R.id.choice1_b));
                                else if(i==1)
                                    button=((Button)findViewById(R.id.choice2_b));
                                else if(i==2)
                                    button=((Button)findViewById(R.id.choice3_b));
                                else if(i==3)
                                    button=((Button)findViewById(R.id.choice4_b));

                                button.setText(choice);
                                if(status.equals("1")){
                                    ToastMaker.showMessage("Hello status of button "+i+" = 1");
                                    button.setBackgroundColor(Color.GREEN);
                                    button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            ToastMaker.showMessage("Correct Answer!");
                                        }
                                    });
                                }else{
                                    ToastMaker.showMessage("Hello status of button "+i+" = 0");
                                    button.setBackgroundColor(Color.RED);
                                    button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            ToastMaker.showMessage("Wrong Answer!");
                                        }
                                    });
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /*
                        {"id":"1","question":"ما هي عاصمة سوريا","topics":"جغرافيا",
                        "difficulty":"1","language":"arabic","type":"MCQ",
                        "subjects":"عواصم","created_at":"2020-12-15 10:20:14",
                        "choices":[
                                {"id":"1","choice":"دمشق","question_id":"1","status":"1"},
                                {"id":"2","choice":"حلب","question_id":"1","status":"0"}
                                ]}
                         */
                    }
                });
    }
}
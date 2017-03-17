package com.kartik.braintrainer;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    Button op1, op2, op3, op4, newgame;
    TextView question, answer, time, score;
    int correct = 0, incorrect = 0;
    int num1, num2;
    int flag;
    int correctOption;
    int stop = 0;
    int totalTime = 30;

    public int random(int range) {
        Random random = new Random();
        int r = random.nextInt(range) + 1;
        return r;
    }

    public void getNumbers(){
        num1 = random(20);
        num2 = random(20);
    }

    public void newgame(final View view) {
        time = (TextView) findViewById(R.id.time);
        if (stop == 0) {
            stop = 1;
            op1.setEnabled(true);
            op2.setEnabled(true);
            op3.setEnabled(true);
            op4.setEnabled(true);
            next(view);
            new CountDownTimer(totalTime * 1000, 1000) {
                public void onTick(long timeLeft) {
                    time.setText(Long.toString(timeLeft/1000) + "s");
                    if(flag == 1){
                        cancel();
                        flag = 0;
                        stop = 0;
                        time.setText(Integer.toString(totalTime) + "s");
                    }
                }
                public void onFinish() {
                    stop = 0;
                    time.setText(Integer.toString(totalTime) + "s");
                    Toast.makeText(MainActivity.this, "You got " + correct + " questions correct and " + incorrect + " questions incorrect out of the " + (correct + incorrect) + " you attempted!", Toast.LENGTH_LONG).show();
                    clear(view);
                }
            }.start();
        }
        else {
            flag = 1;
            clear(view);
        }
    }

    public void next(View view){
        op1 = (Button) findViewById(R.id.op1);
        op2 = (Button) findViewById(R.id.op2);
        op3 = (Button) findViewById(R.id.op3);
        op4 = (Button) findViewById(R.id.op4);
        question = (TextView) findViewById(R.id.question);
        score = (TextView) findViewById(R.id.score);
        getNumbers();
        question.setText(num1 + " + " + num2);
        score.setText(correct + "/" + incorrect);
        correctOption = random(3);
        switch(correctOption){
            case 1: op1.setText(Integer.toString(num1 + num2));
                    op2.setText(Integer.toString(num1 + num2 + 1));
                    op3.setText(Integer.toString(num1 + num2 - 1));
                    op4.setText(Integer.toString(num1 + num2 + 5));
                    break;

            case 2: op2.setText(Integer.toString(num1 + num2));
                    op1.setText(Integer.toString(num1 + num2 + 3));
                    op3.setText(Integer.toString(num1 + num2 - 2));
                    op4.setText(Integer.toString(num1 + num2 + 1));
                    break;

            case 3: op3.setText(Integer.toString(num1 + num2));
                    op2.setText(Integer.toString(num1 + num2 - 3));
                    op1.setText(Integer.toString(num1 + num2 - 2));
                    op4.setText(Integer.toString(num1 + num2 + 1));
                    break;

            case 4: op4.setText(Integer.toString(num1 + num2));
                    op1.setText(Integer.toString(num1 + num2 + 2));
                    op3.setText(Integer.toString(num1 + num2 - 3));
                    op2.setText(Integer.toString(num1 + num2 - 1));
                    break;
        }
    }

    public void clear(View view){
        op1 = (Button) findViewById(R.id.op1);
        op2 = (Button) findViewById(R.id.op2);
        op3 = (Button) findViewById(R.id.op3);
        op4 = (Button) findViewById(R.id.op4);
        question = (TextView) findViewById(R.id.question);
        answer = (TextView) findViewById(R.id.answer);
        time = (TextView) findViewById(R.id.time);
        score = (TextView) findViewById(R.id.score);
        op1.setText("");
        op2.setText("");
        op3.setText("");
        op4.setText("");
        question.setText("");
        answer.setText("");
        score.setText("0/0");
        op1.setEnabled(false);
        op2.setEnabled(false);
        op3.setEnabled(false);
        op4.setEnabled(false);
        correct = incorrect = 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        op1 = (Button) findViewById(R.id.op1);
        op2 = (Button) findViewById(R.id.op2);
        op3 = (Button) findViewById(R.id.op3);
        op4 = (Button) findViewById(R.id.op4);
        newgame = (Button) findViewById(R.id.newgame);
        question = (TextView) findViewById(R.id.question);
        answer = (TextView) findViewById(R.id.answer);
        time = (TextView) findViewById(R.id.time);
        score = (TextView) findViewById(R.id.score);
        time.setText(totalTime + "s");
        op1.setOnClickListener(this);
        op2.setOnClickListener(this);
        op3.setOnClickListener(this);
        op4.setOnClickListener(this);
        score.setText("0/0");

    }

    @Override
    public void onClick(View v) {
        answer = (TextView) findViewById(R.id.answer);
        switch(v.getId()){
            case R.id.op1:  if(correctOption == 1){
                                correct++;
                                answer.setText("Correct!");
                            }
                            else{
                                incorrect++;
                                answer.setText("Incorrect!");
                            }
                            next(v);
                            break;

            case R.id.op2:  if(correctOption == 2){
                                correct++;
                                answer.setText("Correct!");
                            }
                            else{
                                incorrect++;
                                answer.setText("Incorrect!");
                            }
                            next(v);
                            break;

            case R.id.op3:  if(correctOption == 3){
                                correct++;
                                answer.setText("Correct!");
                            }
                            else{
                                incorrect++;
                                answer.setText("Incorrect!");
                            }
                            next(v);
                            break;

            case R.id.op4:  if(correctOption == 4){
                                correct++;
                                answer.setText("Correct!");
                            }
                            else{
                                incorrect++;
                                answer.setText("Incorrect!");
                            }
                            next(v);
                            break;
        }
    }
}

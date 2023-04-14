package com.example.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerRadioGroup;
    private Button submitButton;
    private ProgressBar progressBar;

    private String[] questions;
    private String[][] answers;
    private int[] correctAnswers;

    private int currentQuestion = 0;
    private int correctCount = 0;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progressBar);

        name = getIntent().getStringExtra("name");

        questions = getResources().getStringArray(R.array.questions);
        answers = new String[][] {
                getResources().getStringArray(R.array.answers1),
                getResources().getStringArray(R.array.answers2),
                getResources().getStringArray(R.array.answers3),
                getResources().getStringArray(R.array.answers4),
                getResources().getStringArray(R.array.answers5)
        };
        correctAnswers = getResources().getIntArray(R.array.correct_answers);

        showQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int answerIndex = answerRadioGroup.indexOfChild(findViewById(answerRadioGroup.getCheckedRadioButtonId()));
                if (answerIndex == -1) {
                    Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                } else {
                    checkAnswer(answerIndex);
                }
            }
        });
    }

    private void showQuestion() {
        questionTextView.setText(questions[currentQuestion]);

        answerRadioGroup.removeAllViews();
        for (String answer : answers[currentQuestion]) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answer);
            answerRadioGroup.addView(radioButton);
        }

        if (currentQuestion == questions.length - 1) {
            submitButton.setText("Finish");
        }

        updateProgressBar();
    }

    private void checkAnswer(int answerIndex) {
        if (answerIndex == correctAnswers[currentQuestion]) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            correctCount++;
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }

        currentQuestion++;

        if (currentQuestion < questions.length) {
            showQuestion();
        } else {
            finishQuiz();
        }
    }

    private void updateProgressBar() {
        int progress = (int) (((float) currentQuestion / questions.length) * 5);
        progressBar.setProgress(progress);
    }

    private void finishQuiz() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quiz finished")
                .setMessage(String.format("Congratulations %s! \n YOUR SCORE: \n %d/%d", name, correctCount,questions.length))
                .setPositiveButton("Take new quiz", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                        intent.putExtra("name", name);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

}
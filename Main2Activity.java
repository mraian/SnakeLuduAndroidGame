package com.example.yiwei.ludu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.os.Handler;

import java.util.HashSet;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    //To receive parameters on newly created activity getStringExtra() method will be used.
    //Intent i = getIntent();
    //String myemail = i.getStringExtra("email");
    HashSet backZone = new HashSet();
    HashSet forwardZone = new HashSet();
    HashSet question = new HashSet();
    int player1Position;
    int player2Position;
    Button button_p1_roll;
    Button button_p2_roll;
    ImageView image_p1;
    ImageView image_p2;
    ImageView board;
    int p1_won;
    int p2_won;
    float previousPositionX_p1;
    float currentPositionX_p1;
    float previousPositionY_p1;
    float currentPositionY_p1;
    float previousPositionX_p2;
    float currentPositionX_p2;
    float previousPositionY_p2;
    float currentPositionY_p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();//initializer

    }

    private void init(){
        backZone.add(3);backZone.add(10);backZone.add(22);backZone.add(38);backZone.add(41);backZone.add(54);
        backZone.add(76);backZone.add(81);backZone.add(95);
        forwardZone.add(13);forwardZone.add(24);forwardZone.add(50);forwardZone.add(61);forwardZone.add(77);forwardZone.add(89);
        question.add(7);question.add(12);question.add(30);question.add(34);question.add(58);question.add(64);
        question.add(80);question.add(83);question.add(91);question.add(93);question.add(96);question.add(98);
        player1Position =0;
        player2Position =0;
        button_p1_roll = (Button)findViewById(R.id.button_P1_Roll);
        button_p2_roll = (Button)findViewById(R.id.button_P2_Roll);
        image_p1 = (ImageView)findViewById(R.id.p1);
        image_p2 = (ImageView)findViewById(R.id.p2);
        board = (ImageView)findViewById(R.id.board);
        p1_won=R.drawable.p_1_won;
        p2_won=R.drawable.p_2_won;
//        image_p1.setX(0);image_p1.setY(60);
//        image_p2.setX(0);image_p2.setY(65);
        previousPositionX_p1=0.0f;
        currentPositionX_p1=0.0f;
        previousPositionY_p1=0.0f;
        currentPositionY_p1=0.0f;
        previousPositionX_p2=0.0f;
        currentPositionX_p2=0.0f;
        previousPositionY_p2=0.0f;
        currentPositionY_p2=0.0f;
    }

    public int randomNum(){
        Random rand = new Random();

        int  n = rand.nextInt(6) + 1;

        return n;
    }

    public void rollDice_P1(View v){
        button_p2_roll.setEnabled(true);
        button_p1_roll.setEnabled(false);
        int diceNumber = switchImage(v);
        player1Position +=diceNumber;
        changeP1Position(player1Position);
        while(true){
            if(backZone.contains(player1Position)) {
                player1Position=back(player1Position);
                Log.d("player1 back", ""+player1Position);
                changeP1Position(player1Position);
            }
            else if(forwardZone.contains(player1Position)) {
                player1Position=forward(player1Position);
                Log.d("player1 forward", ""+player1Position);
                changeP1Position(player1Position);
            }
            else if(question.contains(player1Position)){
                player1Position=ques(player1Position);
                Log.d("player1 ques", ""+player1Position);
                changeP1Position(player1Position);
            }
            else {
                Log.d("player1 break", ""+player1Position);

                break;
            }

            Log.d("player1 in while", ""+player1Position);
        }
        Log.d("player1 out while ", ""+player1Position);
    }

    public int back(int initPosition){
        int resultPosition;
        if(initPosition-5<0)resultPosition=0;
        else resultPosition = initPosition-5;
        Log.d("in back function", "playerPosition: "+initPosition);
        return resultPosition;
    }

    public int forward(int initPosition){
        int resultPosition;
        if(initPosition+6>100)resultPosition=100;
        else resultPosition = initPosition+6;
        Log.d("in forward function", "playerPosition: "+initPosition);
        return resultPosition;
    }

    public int ques(int initPosition){
        int ranNum = randomNum();
        int resultPosition;
        if(ranNum%2 ==0){
            if(initPosition+ranNum <100)resultPosition = initPosition+ranNum;
            else resultPosition = 100;
        }
        else {
            if(initPosition-ranNum <0)resultPosition = 0;
            else resultPosition = initPosition-ranNum;
        }
        Log.d("in ques function", "ranNum: "+ranNum+" player1Position: "+initPosition);
        return resultPosition;
    }

    public void popUpWindow(){
//        LinearLayout layoutOfPopup;
//        PopupWindow popupMessage;
//        Button popupButton, insidePopupButton;
//        TextView popupText;
//
//        popupButton = (Button) findViewById(R.id.button_P1_SpRoll);
//        popupText = new TextView(this);
//        insidePopupButton = new Button(this);
//        layoutOfPopup = new LinearLayout(this);
//        insidePopupButton.setText("OK");
//        popupText.setText("This is Popup Window.press OK to dismiss it.");
//        popupText.setPadding(0, 0, 0, 20);
//        layoutOfPopup.setOrientation(LinearLayout.HORIZONTAL);
//        layoutOfPopup.addView(popupText);
//        layoutOfPopup.addView(insidePopupButton);
//        popupButton.setOnClickListener(this);
//        insidePopupButton.setOnClickListener(this);
//        popupMessage = new PopupWindow(layoutOfPopup, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
//        popupMessage.setContentView(layoutOfPopup);



    }


    public int switchImage(View v){//switch image of dice and return dice number
        ImageView image = (ImageView)findViewById(R.id.dice);
        int tempNum = randomNum();
        Log.d("user tag", ""+tempNum);

        switch(tempNum){
            case 1:{
                image.setImageResource(R.drawable.dice1);
                break;
            }

            case 2:{
                image.setImageResource(R.drawable.dice2);
                break;
            }

            case 3:{
                image.setImageResource(R.drawable.dice3);
                break;
            }

            case 4:{
                image.setImageResource(R.drawable.dice4);
                break;
            }

            case 5:{
                image.setImageResource(R.drawable.dice5);
                break;
            }

            case 6:{
                image.setImageResource(R.drawable.dice6);
                break;
            }

        }//switch
        return tempNum;
    }

    public void rollDice_P2(View v){
        button_p1_roll.setEnabled(true);
        button_p2_roll.setEnabled(false);
        int diceNumber = switchImage(v);
        player2Position +=diceNumber;
        changeP2Position(player2Position);
        while(true){
            if(backZone.contains(player2Position)){
                player2Position=back(player2Position);
                Log.d("player2 back", ""+player2Position);
                changeP2Position(player2Position);
            }
            else if(forwardZone.contains(player2Position)) {
                player2Position=forward(player2Position);
                Log.d("player2 forward", ""+player2Position);
                changeP2Position(player2Position);
            }
            else if(question.contains(player2Position)){
                player2Position=ques(player2Position);
                Log.d("player2 ques", ""+player2Position);
                changeP2Position(player2Position);
            }
            else {
                Log.d("player2 break", ""+player2Position);
                break;
            }
            Log.d("player2", ""+player2Position);
        }
        Log.d("player2 out", ""+player2Position);
    }

    public void test1(View view){
        //changeP1Position(100);
    }

    public void test2(View view){
        //changeP2Position(100);
    }

    public void changeP1Position(int p){

        int position = p;
        final Handler handler = new Handler();
        int row = (position%10)-1;
        int col = position /10;
        if((position%10)==0){
            row=9;
            col--;
        }
        if(position ==0){
            row=0;
            col=0;
        }
        currentPositionX_p1=row*100f;
        currentPositionY_p1=col*102.2f;

        TranslateAnimation animation1 = new TranslateAnimation(previousPositionX_p1,currentPositionX_p1,
                previousPositionY_p1, currentPositionY_p1);
        animation1.setDuration(1000);
        animation1.setFillAfter(true);
        image_p1.startAnimation(animation1);
        previousPositionX_p1=currentPositionX_p1;
        previousPositionY_p1=currentPositionY_p1;
        //make delay of event
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }

        }, 1000);

        if(position >=100){
            board.setImageResource(p1_won);
        }

    }

    public void changeP2Position(int p){

        final int position = p;
        final Handler handler = new Handler();
        int row =(position%10)-1;
        int col = position /10;
        if((position%10)==0){
            row=9;
            col--;
        }
        if(position ==0){
            row=0;
            col=0;
        }
        currentPositionX_p2=row*100f;
        currentPositionY_p2=col*102f;

        TranslateAnimation animation = new TranslateAnimation(previousPositionX_p2,currentPositionX_p2,
                previousPositionY_p2, currentPositionY_p2);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        image_p2.startAnimation(animation);
        previousPositionX_p2=currentPositionX_p2;
        previousPositionY_p2=currentPositionY_p2;

        //make delay of event
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 1000);

        if(position >=100){
            board.setImageResource(p2_won);
        }

    }//changeP2Position


}



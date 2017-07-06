package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    TextView mStoryText;
    Button mTopButton;
    Button mBottomButton;
    final int ENDING_6 = 6;  // consts of te endings for enStory function
    final int ENDING_5 = 5;
    final int ENDING_4 = 4;
    int mStoryIndex;       // track the location of player


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        mStoryText = (TextView) findViewById(R.id.storyTextView);
        mTopButton = (Button) findViewById(R.id.buttonTop);
        mBottomButton = (Button) findViewById(R.id.buttonBottom);
        //restore instance
        if (savedInstanceState != null){
            mStoryIndex = savedInstanceState.getInt("Tracker");
            System.out.println(mStoryIndex);
            Log.d("appatite", "SAved state " + mStoryIndex );
            switch (mStoryIndex){
                case 2:
                    updateStory(R.string.T3_Story,
                                R.string.T3_Ans1,
                                R.string.T3_Ans2);
                case 4:
                    break;
                case 3:
                    updateStory(R.string.T2_Story,
                                R.string.T2_Ans1,
                                R.string.T2_Ans2);
                    break;
                case 5:
                    enStory(ENDING_4);
                    break;
                case 6:
                    enStory(ENDING_5);
                    break;
                case 7:
                    enStory(ENDING_6);
                    break;
                default:
                    updateStory(R.string.T1_Story,
                                R.string.T1_Ans1,
                                R.string.T1_Ans2);
                    break;
            }
        }else{
            mStoryIndex = 1;
        }


        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        /* Add 1 if moving left add 2 if moving right  # || # means left # or right # no
        *  need to have a of them have unique numbers, just have if statements for both the left and
        *  right
        *
        *  (add 2 since if you add one you have whole row
        *  of 2 and that would spell trouble, would then need
        *  to also check button text with == or compare ID's
        *  maybe that would make code simpler to read??)
        *
        *
        *                         1
        *                +1   /       \ +2
        *                  2 || 2     3 || 3
        *                  |    |     |    |
        *                   \  /      |    end
        *                    end      | --------> +1
        *                             4 || 4
        *                             |    |
        *                              \  /
        *                              end
        * */
        mTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goes to t3 story if top button is hit for t1_ans1
                //or if t2_ans1 is hit
                if (mStoryIndex == 1 || mStoryIndex == 3) { //1 top and 3 top go to story 3
                    updateStory(R.string.T3_Story,
                            R.string.T3_Ans1,
                            R.string.T3_Ans2);

                    mStoryIndex += 1;
                }
                else if(mStoryIndex == 2 || mStoryIndex == 4){ // 2 top and 4 top go to ending 6
                    enStory(ENDING_6);
                    mStoryIndex = 7; // for when phone is flipped,
                                    // a unique number so we know what it is
                }
            }
        });

        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        mBottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mStoryIndex == 1){
                    updateStory(R.string.T2_Story,
                                R.string.T2_Ans1,
                                R.string.T2_Ans2);

                    mStoryIndex += 2;
                }

                else if(mStoryIndex == 2 || mStoryIndex == 4){
                    enStory(ENDING_5);
                    mStoryIndex = 6;// for when phone is flipped,
                    // a unique number so we know what it is
                }

                else if(mStoryIndex == 3){ //3 bottom goes to ending 4
                    enStory(ENDING_4);
                    mStoryIndex = 5;// for when phone is flipped,
                    // a unique number so we know what it is
                }
            }
        });
    }
    // avoid the need to always type same stuff, have em all here and then just call
    // if you need to write it more than 3 time might as well make a func
    public void updateStory(int passaage, int topB, int botB){
        // just updates the appropriate fields with the new text
        mStoryText.setText(passaage);
        mTopButton.setText(topB);
        mBottomButton.setText(botB);
    }
    // same idea as before split up the work so you can just call
    public void enStory(int which_Ending){
        // set the buttons to gone, which according to google
        // will actually remove the buttons freeing up the space they took up
        mTopButton.setVisibility(View.GONE);
        mBottomButton.setVisibility(View.GONE);
        // have the end text appear after buttons disappear
        switch(which_Ending){
            case ENDING_4:
                mStoryText.setText(R.string.T4_End);
                break;
            case ENDING_5:
                mStoryText.setText(R.string.T5_End);
                break;
            case ENDING_6:
                mStoryText.setText(R.string.T6_End);
                break;
        }
    }

    // for when the screen is tilted, does not seem to work well...
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("Tracker", mStoryIndex);
    }
}

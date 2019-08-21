package peppersapplications.com.superscribbles;

import android.graphics.drawable.Drawable;
import android.content.Context;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.List;

import static peppersapplications.com.superscribbles.MainActivity.paint;

public class myVoiceReader {

    public SpeechRecognizer mySpeech;
    private Context context;
    private Drawable myDrawable;




    //Activates the speech recognition services
    public void startMySpeech(Context context) {
        this.context=context;

        if(SpeechRecognizer.isRecognitionAvailable(context)){
            mySpeech = SpeechRecognizer.createSpeechRecognizer(context);
            mySpeech.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float rmsdB) {

                }

                @Override
                public void onBufferReceived(byte[] buffer) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int error) {

                }

                @Override
                public void onResults(Bundle results) {

                    //ranks possible words and chooses the most likely one.
                    List<String> words = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    processWords(words.get(0));
                }

                @Override
                public void onPartialResults(Bundle partialResults) {

                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            });
        }
    }


    //determines the words and does something based on that.
    private void processWords(String word) {
        word = word.toLowerCase();


        //color commands
        if(word.indexOf("blue")!= -1){
                Toast.makeText(context,"Blue Lines Activated",Toast.LENGTH_LONG).show();
                paint.blueLines();

        }
        if(word.indexOf("red")!= -1){
                Toast.makeText(context,"Red Lines Activated",Toast.LENGTH_LONG).show();
                paint.redLines();

        }
        if(word.indexOf("black")!= -1){
                Toast.makeText(context,"Black Lines Activated",Toast.LENGTH_LONG).show();
                paint.blackLines();

        }
        if(word.indexOf("white")!= -1){

                Toast.makeText(context,"White Lines Activated",Toast.LENGTH_LONG).show();
                paint.whiteLines();

        }
        if(word.indexOf("green")!= -1){


                Toast.makeText(context,"Green Lines Activated",Toast.LENGTH_LONG).show();
                paint.greenLines();

        }
        if(word.indexOf("yellow")!= -1){


            Toast.makeText(context,"Yellow Lines Activated",Toast.LENGTH_LONG).show();
            paint.yellowLines();

        }
        if(word.indexOf("gray")!= -1){


            Toast.makeText(context,"Gray Lines Activated",Toast.LENGTH_LONG).show();
            paint.grayLines();

        }
        if(word.indexOf("grey")!= -1){


            Toast.makeText(context,"Grey Lines Activated",Toast.LENGTH_LONG).show();
            paint.grayLines();


        }
        if(word.indexOf("pink")!= -1){


            Toast.makeText(context,"Pink Lines Activated",Toast.LENGTH_LONG).show();
            paint.pinkLines();

        }


        //delete commands

        if(word.indexOf("clear")!= -1){
            Toast.makeText(context, "Erased", Toast.LENGTH_SHORT).show();
            paint.erase();
        }
        if(word.indexOf("erase")!= -1){
            Toast.makeText(context, "Erased", Toast.LENGTH_SHORT).show();
            paint.erase();
        }
        if(word.indexOf("delete")!= -1){
            Toast.makeText(context, "Erased", Toast.LENGTH_SHORT).show();
            paint.erase();
        }


        //background commands
        if(word.indexOf("cat")!= -1){

            Toast.makeText(context, "Cat", Toast.LENGTH_SHORT).show();
            myDrawable = ContextCompat.getDrawable(context, R.drawable.cat);
            paint.setBackground(myDrawable);
        }
        if(word.indexOf("dog")!= -1){

            Toast.makeText(context, "Dog", Toast.LENGTH_SHORT).show();
            myDrawable = ContextCompat.getDrawable(context, R.drawable.dog);
            paint.setBackground(myDrawable);
        }
        if(word.indexOf("fish")!= -1){

            Toast.makeText(context, "Fish", Toast.LENGTH_SHORT).show();
            myDrawable = ContextCompat.getDrawable(context, R.drawable.fish);
            paint.setBackground(myDrawable);
        }
        if(word.indexOf("elephant")!= -1){

            Toast.makeText(context, "Elephant", Toast.LENGTH_SHORT).show();
            myDrawable = ContextCompat.getDrawable(context, R.drawable.elephant);
            paint.setBackground(myDrawable);
        }
        if(word.indexOf("mouse")!= -1){

            Toast.makeText(context, "Mouse", Toast.LENGTH_SHORT).show();
            myDrawable = ContextCompat.getDrawable(context, R.drawable.mouse);
            paint.setBackground(myDrawable);
        }

    }
}

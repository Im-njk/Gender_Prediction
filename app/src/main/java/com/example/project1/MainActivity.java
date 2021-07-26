package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText input;
    Button enterbtn,retry;
    PopupWindow popupWindow;
    LinearLayout linearLayout;
    String inputstr;
    TextView output;
    String BaseUrl="https://api.genderize.io/?name=scott";
    Drawable d1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        d1 = getContext().getResources().getDrawable( R.drawable.buttonblue );
        input=findViewById(R.id.input);
        enterbtn=findViewById(R.id.enterbtn);

//        output.setText("Congratulations! You are ");
        linearLayout =findViewById(R.id.linearlayout);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiinterface api=retrofit.create(apiinterface.class);
        enterbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                   enterbtn.setBackgroundResource(R.drawable.buttons);
                   return false;
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    enterbtn.setBackgroundResource(R.drawable.buttonblue);
                    return false;
                }
                return false;
            }
        });

        enterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   inputstr=input.getText().toString().trim();
                Call<model> call = api.getmodel(inputstr);
                if(inputstr.isEmpty()){
                    input.setError("Please enter your name");
                }
                else if(inputstr.equals("pingu")||inputstr.equals("pingaksh")||inputstr.equals("Pingu")||inputstr.equals("PINGU")||inputstr.equals("PINGAKSH")){
                   popup();
                    output.setText("Fuck off! you gay");
                }
                else{
                    popup();
                    call.enqueue(new Callback<model>() {
                        @Override
                        public void onResponse(Call<model> call, Response<model> response) {
                            String outputstr=response.body().getGender();
                            if(outputstr==null){
                                output.setText("Sorry! we cannot find you gender");
                            }else{
                            output.setText("Congratulations! You are "+outputstr);
                            Toast.makeText(MainActivity.this,outputstr, Toast.LENGTH_SHORT).show();}
                        }
                        @Override
                        public void onFailure(Call<model> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "not ok!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }




            }
        });
    }
    void popup(){
        LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup,null);
        retry= (Button) customView.findViewById(R.id.retrybtn);


        popupWindow = new PopupWindow(customView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                input.setText("");

            }
        });
        retry.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    retry.setBackgroundResource(R.drawable.buttons);
                    return false;
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    retry.setBackgroundResource(R.drawable.buttonblue);
                    return false;
                }
                return false;
            }
        });
        output=customView.findViewById(R.id.result);
    }
}
package com.mygdx.game;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import static com.mygdx.game.ScrollingActivity.mediaPlayer;

public class PersonActivity extends AppCompatActivity {
    EditText name_person;
    Button confirm;
    String s;
    GetterANDSetterFile getterANDSetterFile;
    Switch soundMusic;
    Spinner appearance;
    ImageView imageView;
    CountDownTimer countDownTimer;
    int seconds=1;
    @Override
    protected void onStart(){
        if(getterANDSetterFile.get_SoundMusic()==1)mediaPlayer.start();
        super.onStart();
    }
    @Override
    protected void onPause(){
        mediaPlayer.pause();
        super.onPause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        name_person = findViewById(R.id.name_person);
        s = name_person.getText().toString();
        confirm = findViewById(R.id.confirm);
        imageView = findViewById(R.id.appearance_imageView);
        soundMusic = findViewById(R.id.sound);
        getterANDSetterFile = new GetterANDSetterFile();
        if(getterANDSetterFile.get_Nickname()!=name_person.getText().toString()){
            confirm.setVisibility(View.VISIBLE);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s = name_person.getText().toString();
                    if(!s.equals(""))getterANDSetterFile.set_Nickname(s);
                    else Toast.makeText(getApplicationContext(),"?????????????? ???? ?????????? ???????? ????????????",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else confirm.setVisibility(View.INVISIBLE);
        if(!s.equals(""))getterANDSetterFile.set_Nickname(s);
        else Toast.makeText(getApplicationContext(),"?????????????? ???? ?????????? ???????? ????????????",Toast.LENGTH_SHORT).show();

        if(getterANDSetterFile.get_SoundMusic()==1)soundMusic.setChecked(true);
        else soundMusic.setChecked(false);
        soundMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!soundMusic.isChecked()){
                    getterANDSetterFile.set_SoundMusic(0);
                }
                else{
                    Toast.makeText(getApplicationContext(),"??????????????????...",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PersonActivity.this,ScrollingActivity.class));
                    getterANDSetterFile.set_SoundMusic(1);
                }
            }
        });
        countDownTimer = new CountDownTimer(seconds*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                seconds--;
                if(getterANDSetterFile.get_Appearance()==1)imageView.setImageResource(R.mipmap.original);
                if(getterANDSetterFile.get_Appearance()==2)imageView.setImageResource(R.mipmap.original2);
                if(getterANDSetterFile.get_Appearance()==3)imageView.setImageResource(R.mipmap.original3);
                if(getterANDSetterFile.get_Appearance()==4)imageView.setImageResource(R.mipmap.original4);
                if(getterANDSetterFile.get_Appearance()==5)imageView.setImageResource(R.mipmap.original5);
            }
            @Override
            public void onFinish() {
                if (countDownTimer!=null){
                    seconds = 1;
                    countDownTimer.start();
                }
            }
        };
        if (countDownTimer!=null){
            seconds = 1;
            countDownTimer.start();
        }
        appearance = findViewById(R.id.appearance_spinner);
        final String[] s2 = {""};

        appearance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,View itemSelected, int selectedItemPosition, long selectedId) {
                s2[0] = String.valueOf(appearance.getSelectedItem());
                if(appearance.getSelectedItemId()==1)getterANDSetterFile.set_Appearance(1);
                if(appearance.getSelectedItemId()==2)getterANDSetterFile.set_Appearance(2);
                if(appearance.getSelectedItemId()==3)getterANDSetterFile.set_Appearance(3);
                if(appearance.getSelectedItemId()==4)getterANDSetterFile.set_Appearance(4);
                if(appearance.getSelectedItemId()==5)getterANDSetterFile.set_Appearance(5);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.person, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            //setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
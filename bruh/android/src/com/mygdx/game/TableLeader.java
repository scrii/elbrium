package com.mygdx.game;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import static com.mygdx.game.MainActivity.mediaPlayer2;

public class TableLeader extends AppCompatActivity {
    ListView listView;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Double> numbers;
    LeaderBoard leaderBoard;
    TextView leader_nickname,leader_elbrium,number;
    int n;
    double timeDouble=0.0;
    public int b=1;
    String userUid;
    String timeString="";
    FirebaseAuth firebaseAuth;
    GetterANDSetterFile getterANDSetterFile;
    FirebaseUser user;
    DatabaseReference databaseReference;
    FirebaseListAdapter<LeaderBoard> adapter;
    @Override
    protected void onStart(){
        if(getterANDSetterFile.get_SoundMusic()==1)mediaPlayer2.start();
        super.onStart();
    }
    @Override
    protected void onPause(){
        mediaPlayer2.pause();
        super.onPause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_leader);
        listView = findViewById(R.id.leaderView);
        numbers = new ArrayList<>();
        leaderBoard = new LeaderBoard();
        getterANDSetterFile = new GetterANDSetterFile();
        getterANDSetterFile.set_TrueOrFalse(0);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userUid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("LeaderBoard");
        adapter = new FirebaseListAdapter<LeaderBoard>(TableLeader.this,LeaderBoard.class,R.layout.leader_list, FirebaseDatabase.getInstance().getReference("LeaderBoard").orderByChild("elbrium").limitToLast(2147000000)){
            @Override
            protected void populateView(View v, LeaderBoard model, int position) {
                number = v.findViewById(R.id.number);
                leader_nickname = v.findViewById(R.id.name_leader);
                leader_elbrium = v.findViewById(R.id.elbrium_leader);
                numbers.add(model.getElbrium());
                name.add(model.getNickname());
                n = numbers.size()-1;
                for(int u=0;u<n+1;u++){
                    if((name.get(u)!=getterANDSetterFile.get_Nickname())){
                        getterANDSetterFile.set_TrueOrFalse(1);
                    }
                    else {
                        getterANDSetterFile.set_TrueOrFalse(-1);
                    }
                }
                if(b==1 && getterANDSetterFile.get_TrueOrFalse()==1 && getterANDSetterFile.get_TrueOrFalse()!=0){
                    if(getterANDSetterFile.get_Ore_Elbrium()>0.0){
                        getterANDSetterFile.set_TrueOrFalse(-1);
                        b=0;
                    }
                }
                if(getterANDSetterFile.get_TrueOrFalse()==-1){
                    databaseReference.child(user.getUid()).setValue(new LeaderBoard(getterANDSetterFile.get_Nickname(),getterANDSetterFile.get_Ore_Elbrium()));
                    getterANDSetterFile.set_TrueOrFalse(0);
                }
                LayoutInflater mInflater = (LayoutInflater) getApplicationContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                v = mInflater.inflate(R.layout.leader_list, null);
                number.setText(position+" ");
                leader_nickname.setText(model.getNickname()+" ");
                leader_elbrium.setText(model.getElbrium()+"");
                if(position==0)number.setTextColor(getResources().getColor(R.color.zero));
                if(position>0 && position<=10)number.setTextColor(getResources().getColor(R.color.one_ten));
                if(position>10 && position<=20)number.setTextColor(getResources().getColor(R.color.ten_twenty));
                if(position>20 && position<=30)number.setTextColor(getResources().getColor(R.color.twenty_thirty));
                if(position>30 && position<=40)number.setTextColor(getResources().getColor(R.color.thirty_forty));
                if(position>40 && position<=50)number.setTextColor(getResources().getColor(R.color.forty_fifty));
                if(position>50 && position<=60)number.setTextColor(getResources().getColor(R.color.fifty_sixty));
                if(position>60 && position<=70)number.setTextColor(getResources().getColor(R.color.sixty_seventy));
                if(position>70 && position<=80)number.setTextColor(getResources().getColor(R.color.seventy_ninety));
                if(position>80 && position<=90)number.setTextColor(getResources().getColor(R.color.ninety_one_hundred));
                if(position>90)number.setTextColor(getResources().getColor(R.color.other));
                number.setTextSize(16);
                leader_nickname.setTextSize(16);
                leader_elbrium.setTextSize(16);
            }
        };
        listView.setAdapter(adapter);
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
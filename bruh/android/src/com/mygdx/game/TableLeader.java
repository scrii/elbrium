package com.mygdx.game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TableLeader extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayList<Double> yourArray;
    LeaderBoard leaderBoard;
    TextView leader_nickname,leader_elbrium,number;
    int n,v1=0;
    public int b=1;
    CountDownTimer countDownTimer;
    FirebaseListAdapter<LeaderBoard> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_leader);
        listView = findViewById(R.id.leaderView);
        yourArray = new ArrayList<>();
        leaderBoard = new LeaderBoard();
        GetterANDSetterFile getterANDSetterFile = new GetterANDSetterFile();
        getterANDSetterFile.set_TrueOrFalse(0);
        FirebaseDatabase.getInstance().getReference("LeaderBoard").push().setValue(new LeaderBoard("god",20000.0));
        FirebaseDatabase.getInstance().getReference("LeaderBoard").push().setValue(new LeaderBoard("cheater",19999.0));
        //Log.d("Database",FirebaseDatabase.getInstance().getReference("LeaderBoard").getParent().toString());

        adapter = new FirebaseListAdapter<LeaderBoard>(TableLeader.this,LeaderBoard.class,R.layout.leader_list, FirebaseDatabase.getInstance().getReference("LeaderBoard")){
            @Override
            protected void populateView(View v, LeaderBoard model, int position) {
                number = v.findViewById(R.id.number);
                leader_nickname = v.findViewById(R.id.name_leader);
                leader_elbrium = v.findViewById(R.id.elbrium_leader);
                try {
                    yourArray.add(model.getElbrium());
                    arrayList1.add(model.getNickname());
                    n = yourArray.size()-1;
                    //отсортированный массив
                    for (int i = 0; i < n - 1; i++){
                        for (int j = 0; j < n - i - 1; j++){
                            if (yourArray.get(j) < yourArray.get(j + 1)) {
                                double temp = yourArray.get(j);
                                String s1 = (String)arrayList1.get(j);
                                arrayList1.set(j,arrayList1.get(j+1));
                                yourArray.set(j, yourArray.get(j + 1));
                                arrayList1.set(j+1,s1);
                                yourArray.set(j + 1, temp);
                                if((arrayList1.get(j)+"") == (getterANDSetterFile.get_Nickname()))v1++;
                            } //
                        }
                    }
                    for (int c = 0; c < n+1; c++) {
                        for (int i = 0; i < n+1; i++) {
                            leader_nickname.setText(arrayList1.get(i)+" ");
                            leader_elbrium.setText(yourArray.get(i)+"");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                for(int u=0;u<n+1;u++){
                    if((arrayList1.get(u)!=getterANDSetterFile.get_Nickname())){
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
                        FirebaseDatabase.getInstance().getReference("LeaderBoard").push().setValue(new LeaderBoard(getterANDSetterFile.get_Nickname(),getterANDSetterFile.get_Ore_Elbrium()));
                    }
                }
//                if(getterANDSetterFile.get_TrueOrFalse()==-1){
//                    //FirebaseDatabase.getInstance().getReference("LeaderBoard").updateChildren(new LeaderBoard(getterANDSetterFile.get_Nickname(),getterANDSetterFile.get_Ore_Elbrium()));
//                    FirebaseDatabase.getInstance().getReference("LeaderBoard").push().setValue(new LeaderBoard(getterANDSetterFile.get_Nickname(),getterANDSetterFile.get_Ore_Elbrium()));
//                    getterANDSetterFile.set_TrueOrFalse(0);
//                }
                number.setText(position+" ");
                LayoutInflater mInflater = (LayoutInflater) getApplicationContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                //if (convertView == null) {
                v = mInflater.inflate(R.layout.leader_list, null);

                LeaderBoard content = getItem(position);

                leader_nickname.setText(content.getNickname()+"");
                leader_elbrium.setText(content.getElbrium()+"");
                //leader_nickname.setText(model.getNickname()+" ");
                //leader_elbrium.setText(model.getElbrium()+"");
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
//        if(getterANDSetterFile.get_Ore_Elbrium()>0.0){
//            if(getterANDSetterFile.get_TrueOrFalse()==1){
//                FirebaseDatabase.getInstance().getReference("LeaderBoard").push().setValue(new LeaderBoard(getterANDSetterFile.get_Nickname(),getterANDSetterFile.get_Ore_Elbrium()));
//                getterANDSetterFile.set_TrueOrFalse(0);
//            }
//            else if(getterANDSetterFile.get_TrueOrFalse()==-1)FirebaseDatabase.getInstance().getReference("LeaderBoard").setValue(new LeaderBoard(getterANDSetterFile.get_Nickname(),getterANDSetterFile.get_Ore_Elbrium()));
//            //else Toast.makeText(getApplicationContext(),"Ошибка",Toast.LENGTH_SHORT).show();
//        }
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
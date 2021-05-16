package com.mygdx.game;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TableLeader extends AppCompatActivity {
    ListView listView;
    ArrayList arrayList1;
    ArrayList<Double> yourArray;
    TextView leader_nickname,leader_elbrium,number;
    int n;
    double b=-1.0;
    FirebaseListAdapter<LeaderBoard> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_leader);
        listView = findViewById(R.id.leaderView);
        arrayList1 = new ArrayList();
        yourArray = new ArrayList<>();
        GetterANDSetterFile getterANDSetterFile = new GetterANDSetterFile();
        //Log.d("Database",FirebaseDatabase.getInstance().getReference("LeaderBoard").getParent().toString());
        if(getterANDSetterFile.get_Ore_Elbrium()>0.0)FirebaseDatabase.getInstance().getReference("LeaderBoard").push().setValue(new LeaderBoard(getterANDSetterFile.get_Nickname(),getterANDSetterFile.get_Ore_Elbrium()));
        adapter = new FirebaseListAdapter<LeaderBoard>(TableLeader.this,LeaderBoard.class,R.layout.leader_list, FirebaseDatabase.getInstance().getReference("LeaderBoard")){
            @Override
            protected void populateView(View v, LeaderBoard model, int position) {
                number = v.findViewById(R.id.number);
                leader_nickname = v.findViewById(R.id.name_leader);
                leader_elbrium = v.findViewById(R.id.elbrium_leader);
                try {
                    for (int i = 0; i < 10; i++) {
                        if(model.getElbrium()!=b){
                            yourArray.add(model.getElbrium());
                            b=model.getElbrium();
                        }
                        arrayList1.add(model.getNickname());
                    }
                    Log.d("Arrays-4",yourArray+"");
                    n = yourArray.size();
                    //отсортированный массив
                    for (int i = 0; i < n - 1; i++)
                        for (int j = 0; j < n - i - 1; j++)
                            if (yourArray.get(j) < yourArray.get(j + 1)) {
                                double temp = yourArray.get(j);
                                String s1 = (String)arrayList1.get(j);
                                arrayList1.set(j,arrayList1.get(j+1));
                                yourArray.set(j, yourArray.get(j + 1));
                                arrayList1.set(j+1,s1);
                                yourArray.set(j + 1, temp);
                            }
                }catch (Exception e){
                    e.printStackTrace();
                }
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
        for (int i = 0; i < n; i++) {
            Log.d("Arrays-2",arrayList1.get(i)+" ");
            Log.d("Arrays-3",yourArray.get(i)+"");
            leader_nickname.setText(arrayList1.get(i)+" ");
            leader_elbrium.setText(yourArray.get(i)+"");
        }
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
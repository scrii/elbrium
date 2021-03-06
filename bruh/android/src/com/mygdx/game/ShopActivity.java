package com.mygdx.game;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.google.firebase.database.FirebaseDatabase;

import FirebaseHelper.Message;
import static com.mygdx.game.ScrollingActivity.mediaPlayer;

public class ShopActivity extends AppCompatActivity {

    GetterANDSetterFile getter_setter;
    Message player_data;
    public int seconds = 1;
    CountDownTimer countDownTimer;
    int price_ore_elbrium = 3;
    int price_protection = 30;
    int price_speed = 30;
    int price_attack = 10;
    double new_price_maneuverability,new_price_attack,new_price_protection,new_price_speed,new_price_ore_elbrium,new_price_ore_elbrium_speed;
    int price_maneuverability = 10;
    Button maneuverability_plus_one,attack_plus_one,protect_plus_one,speed_plus_one,sale_of_elbrium;
    TextView real_money_characteristic,real_health_characteristic,real_damage_characteristic,real_protect_characteristic,real_speed_characteristic,real_ore_characteristic,maneuverability_price,attack_price,protection_price,speed_price;
    @Override
    protected void onStart(){
        if(getter_setter.get_SoundMusic()==1)mediaPlayer.start();
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
        setContentView(R.layout.shop_activity);
        getter_setter=new GetterANDSetterFile();
        player_data=new Message(getter_setter.getTexture(),-1,-1,(float)getter_setter.get_Attack(),
                (float)getter_setter.get_Health(),(float)getter_setter.get_Protection());
        maneuverability_plus_one = findViewById(R.id.maneuverability_plus_one);
        attack_plus_one = findViewById(R.id.attack_plus_one);
        protect_plus_one = findViewById(R.id.protect_plus_one);
        speed_plus_one = findViewById(R.id.speed_plus_one);
        real_money_characteristic = findViewById(R.id.real_money_characteristic);
        real_health_characteristic = findViewById(R.id.real_health_characteristic);
        real_damage_characteristic = findViewById(R.id.real_damage_characteristic);
        real_protect_characteristic = findViewById(R.id.real_protect_characteristic);
        real_speed_characteristic = findViewById(R.id.real_speed_characteristic);
        real_ore_characteristic = findViewById(R.id.real_ore_characteristic);
        maneuverability_price = findViewById(R.id.maneuverability_price);
        attack_price = findViewById(R.id.attack_price);
        protection_price = findViewById(R.id.protection_price);
        speed_price = findViewById(R.id.speed_price);
        sale_of_elbrium = findViewById(R.id.sale_elbrium);
        GetterANDSetterFile getterANDSetterFile = new GetterANDSetterFile();
        countDownTimer = new CountDownTimer(seconds*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                seconds--;
                real_ore_characteristic.setText(Math.round(getterANDSetterFile.get_Ore_Elbrium()*100.0)/100.0+"");
                real_health_characteristic.setText(Math.round(getterANDSetterFile.get_Health()*100.0)/100.0+"");
                real_damage_characteristic.setText(Math.round(getterANDSetterFile.get_Attack()*100.0)/100.0+"");
                real_protect_characteristic.setText(Math.round(getterANDSetterFile.get_Protection()*100.0)/100.0+"");
                real_speed_characteristic.setText(Math.round(getterANDSetterFile.get_Speed()*100.0)/100.0+"");
                real_money_characteristic.setText(Math.round(getterANDSetterFile.get_Guardian_Money()*100.0)/100.0+"");

                attack_price.setText(Math.round((10 + price_attack*getterANDSetterFile.get_Coefficient_Attack())*100.0)/100.0+"");
                speed_price.setText(Math.round((30 + price_speed*getterANDSetterFile.get_Coefficient_Speed())*100.0)/100.0+"");
                protection_price.setText(Math.round((30 + price_protection*getterANDSetterFile.get_Coefficient_Protection())*100.0)/100.0+"");
                maneuverability_price.setText(Math.round((10 + price_maneuverability*getterANDSetterFile.get_Maneuverability())*100.0)/100.0+"");
            }
            @Override
            public void onFinish() {
                if (countDownTimer != null){
                    seconds = 1;
                    countDownTimer.start();
                }
            }
        };
        if (countDownTimer!=null){
            seconds = 1;
            countDownTimer.start();
        }
        real_ore_characteristic.setText(Math.round(getterANDSetterFile.get_Ore_Elbrium()*100.0)/100.0+"");
        real_health_characteristic.setText(Math.round(getterANDSetterFile.get_Health()*100.0)/100.0+"");
        real_damage_characteristic.setText(Math.round(getterANDSetterFile.get_Attack()*100.0)/100.0+"");
        real_protect_characteristic.setText(Math.round(getterANDSetterFile.get_Protection()*100.0)/100.0+"");
        real_speed_characteristic.setText(Math.round(getterANDSetterFile.get_Speed()*100.0)/100.0+"");
        real_money_characteristic.setText(Math.round(getterANDSetterFile.get_Guardian_Money()*100.0)/100.0+"");
        attack_price.setText(Math.round((10 + price_attack*getterANDSetterFile.get_Coefficient_Attack())*100.0)/100.0+"");
        speed_price.setText(Math.round((30 + price_speed*getterANDSetterFile.get_Coefficient_Speed())*100.0)/100.0+"");
        protection_price.setText(Math.round((30 + price_protection*getterANDSetterFile.get_Coefficient_Protection())*100.0)/100.0+"");
        maneuverability_price.setText(Math.round((10 + price_maneuverability*getterANDSetterFile.get_Maneuverability())*100.0)/100.0+"");
        maneuverability_plus_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_price_maneuverability = 10 + price_maneuverability*getterANDSetterFile.get_Maneuverability();
                if(getterANDSetterFile.get_Guardian_Money()>=new_price_maneuverability){
                    if(getterANDSetterFile.get_Protection()>0.1){
                        getterANDSetterFile.set_Guardian_Money(getterANDSetterFile.get_Guardian_Money()-new_price_maneuverability);
                        getterANDSetterFile.set_Speed(getterANDSetterFile.get_Speed()+0.2);
                        getterANDSetterFile.set_Protection(getterANDSetterFile.get_Protection()-0.1);
                        getterANDSetterFile.set_Maneuverability(getterANDSetterFile.get_Maneuverability()+1);
                        real_speed_characteristic.setText(Math.round(getterANDSetterFile.get_Speed()*100.0)/100.0+"");
                        real_protect_characteristic.setText(Math.round(getterANDSetterFile.get_Protection()*100.0)/100.0+"");
                        maneuverability_price.setText(Math.round(new_price_maneuverability*100.0)/100.0+"");
                        real_money_characteristic.setText(Math.round(getterANDSetterFile.get_Guardian_Money()*100.0)/100.0+"");
                    }
                    else Toast.makeText(getApplicationContext(),"???????? ???????????? ?????????????? ????????",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(),"???????????????????????? ??????????????",Toast.LENGTH_SHORT).show();
            }
        });
        attack_plus_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_price_attack = 10 + price_attack*getterANDSetterFile.get_Coefficient_Attack();
                if(getterANDSetterFile.get_Guardian_Money()>=new_price_attack){
                    if(getterANDSetterFile.get_Speed()>0.1){
                        getterANDSetterFile.set_Guardian_Money(getterANDSetterFile.get_Guardian_Money()-new_price_attack);
                        getterANDSetterFile.set_Attack(getterANDSetterFile.get_Attack()+0.1);
                        getterANDSetterFile.set_Speed(getterANDSetterFile.get_Speed()-0.1);
                        getterANDSetterFile.set_Coefficient_Attack(getterANDSetterFile.get_Coefficient_Attack()+1);
                        real_damage_characteristic.setText(Math.round(getterANDSetterFile.get_Attack()*100.0)/100.0+"");
                        real_speed_characteristic.setText(Math.round(getterANDSetterFile.get_Speed()*100.0)/100.0+"");
                        real_money_characteristic.setText(Math.round(getterANDSetterFile.get_Guardian_Money()*100.0)/100.0+"");
                        attack_price.setText(Math.round(new_price_attack*100.0)/100.0+"");
                    }
                    else Toast.makeText(getApplicationContext(),"???????? ???????????????? ?????????????? ????????",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(),"???????????????????????? ??????????????",Toast.LENGTH_SHORT).show();
                player_data.setAttack((float)getterANDSetterFile.get_Attack());
                update_values();
            }
        });
        protect_plus_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_price_protection = 30 + price_protection*getterANDSetterFile.get_Coefficient_Protection();
                if(getterANDSetterFile.get_Guardian_Money()>=new_price_protection){
                    if(getterANDSetterFile.get_Guardian_Level()<15){
                        getterANDSetterFile.set_Guardian_Money(getterANDSetterFile.get_Guardian_Money()-new_price_protection);
                        getterANDSetterFile.set_Protection(getterANDSetterFile.get_Protection()+0.1);
                        getterANDSetterFile.set_Coefficient_Protection(getterANDSetterFile.get_Coefficient_Protection()+1);
                        real_money_characteristic.setText(Math.round(getterANDSetterFile.get_Guardian_Money()*100.0)/100.0+"");
                        real_protect_characteristic.setText(Math.round(getterANDSetterFile.get_Protection()*100.0)/100.0+"");
                        protection_price.setText(Math.round(new_price_protection*100.0)/100.0+"");
                    }
                    else{
                        new_price_ore_elbrium = 15 + price_ore_elbrium * (getterANDSetterFile.get_Ore_Elbrium()-15);
                        if(getterANDSetterFile.get_Ore_Elbrium()>=new_price_ore_elbrium && getterANDSetterFile.get_Guardian_Money()>=new_price_protection){
                            getterANDSetterFile.set_Guardian_Money(getterANDSetterFile.get_Guardian_Money()-new_price_protection);
                            getterANDSetterFile.set_Protection(getterANDSetterFile.get_Protection()+0.1);
                            getterANDSetterFile.set_Ore_Elbrium(getterANDSetterFile.get_Ore_Elbrium()-new_price_ore_elbrium);
                            getterANDSetterFile.set_Coefficient_Protection(getterANDSetterFile.get_Coefficient_Protection()+1);
                            real_ore_characteristic.setText(Math.round(getterANDSetterFile.get_Ore_Elbrium()*100.0)/100.0+"");
                            real_money_characteristic.setText(Math.round(getterANDSetterFile.get_Guardian_Money()*100.0)/100.0+"");
                            real_protect_characteristic.setText(Math.round(getterANDSetterFile.get_Protection()*100.0)/100.0+"");
                            protection_price.setText(Math.round(new_price_protection*100.0)/100.0+"");
                        }
                        else Toast.makeText(getApplicationContext(),"???????????????????????? ??????????????",Toast.LENGTH_SHORT).show();
                    }
                }
                else Toast.makeText(getApplicationContext(),"???????????????????????? ??????????????",Toast.LENGTH_SHORT).show();
                player_data.setProtect((float)getterANDSetterFile.get_Protection());
                update_values();
            }
        });
        speed_plus_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_price_speed = 30 + price_speed*getterANDSetterFile.get_Coefficient_Speed();
                if(getterANDSetterFile.get_Guardian_Money()>=new_price_speed){
                    if(getterANDSetterFile.get_Guardian_Level()<15){
                        getterANDSetterFile.set_Guardian_Money(getterANDSetterFile.get_Guardian_Money()-new_price_speed);
                        getterANDSetterFile.set_Speed(getterANDSetterFile.get_Speed()+0.1);
                        getterANDSetterFile.set_Coefficient_Speed(getterANDSetterFile.get_Coefficient_Speed()+1);
                        real_money_characteristic.setText(Math.round(getterANDSetterFile.get_Guardian_Money()*100.0)/100.0+"");
                        real_speed_characteristic.setText(Math.round(getterANDSetterFile.get_Speed()*100.0)/100.0+"");
                        speed_price.setText(Math.round(new_price_speed*100.0)/100.0+"");
                    }
                    else{
                        new_price_ore_elbrium_speed = 15 + price_ore_elbrium * (getterANDSetterFile.get_Ore_Elbrium()-15);
                        if(getterANDSetterFile.get_Ore_Elbrium()>=new_price_ore_elbrium_speed && getterANDSetterFile.get_Guardian_Money()>=new_price_speed){
                            getterANDSetterFile.set_Guardian_Money(getterANDSetterFile.get_Guardian_Money()-new_price_speed);
                            getterANDSetterFile.set_Speed(getterANDSetterFile.get_Speed()+0.1);
                            getterANDSetterFile.set_Ore_Elbrium(getterANDSetterFile.get_Ore_Elbrium()-new_price_ore_elbrium_speed);
                            getterANDSetterFile.set_Coefficient_Speed(getterANDSetterFile.get_Coefficient_Speed()+1);
                            real_ore_characteristic.setText(Math.round(getterANDSetterFile.get_Ore_Elbrium()*100.0)/100.0+"");
                            real_money_characteristic.setText(Math.round(getterANDSetterFile.get_Guardian_Money()*100.0)/100.0+"");
                            real_speed_characteristic.setText(Math.round(getterANDSetterFile.get_Speed()*100.0)/100.0+"");
                            speed_price.setText(Math.round(new_price_speed*100.0)/100.0+"");
                        }
                        else Toast.makeText(getApplicationContext(),"???????????????????????? ?????????????? ?????? ????????",Toast.LENGTH_SHORT).show();
                    }
                }
                else Toast.makeText(getApplicationContext(),"???????????????????????? ??????????????",Toast.LENGTH_SHORT).show();
                update_values();
            }
        });
        sale_of_elbrium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getterANDSetterFile.get_Ore_Elbrium()>0.0){
                    getterANDSetterFile.set_Guardian_Money(getterANDSetterFile.get_Guardian_Money() + getterANDSetterFile.get_Ore_Elbrium()*2);
                    getterANDSetterFile.set_Ore_Elbrium(0.0);
                }
                else Toast.makeText(getApplicationContext(),"Elbrium ???? ????????????",Toast.LENGTH_SHORT).show();
            }
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.shop, new SettingsFragment())
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
    public void update_values(){
        FirebaseDatabase.getInstance().getReference("LONGDATA_"+getter_setter.get_Nickname()).setValue(player_data.toString());
    }
}
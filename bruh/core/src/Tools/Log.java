package Tools;

import com.badlogic.gdx.Gdx;

public class Log {
    public void e(String s1, String s2){
        Gdx.app.error(s1,s2);
    }
    public void i(String s1, String s2){
        Gdx.app.log(s1,s2);
    }
    public void d(String s1, String s2){
        Gdx.app.debug(s1,s2);
    }
}

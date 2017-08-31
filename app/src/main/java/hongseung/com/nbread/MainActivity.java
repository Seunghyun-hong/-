package hongseung.com.nbread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ScreenFragment.OnSendMessageListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScreenFragment fragment = new ScreenFragment();

        // R.id.container 영역에 프래그먼트를 붙이겠다.
        // 근데 굳이 동적으로 붙일 필요가... 없긴 한데..
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1, fragment)
                .commit();

    }

    @Override
    public void onSendMessage(String message) {

    }
}


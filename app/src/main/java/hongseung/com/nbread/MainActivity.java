package hongseung.com.nbread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ScreenFragment.OnSendMessageListener {


    private ScreenFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment = new ScreenFragment();

        // R.id.container 영역에 프래그먼트를 붙이겠다.
        // 근데 굳이 동적으로 붙일 필요가... 없긴 한데..
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1, mFragment)
                .commit();

    }


    //여기서 메세지를 뽑아낼 수 있다.
    @Override
    public void onSendMessage(String message) {
        mFragment.sendMessage(message);
    }
}


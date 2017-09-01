package hongseung.com.nbread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ScreenFragment.OnSendMessageListener {

    private ScreenFragment mFragment1;
    private String getMessage;

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
    // 프래그먼트와 통신
    @Override
    public void onSendMessage(String message) {
        getMessage = message;
    }



    public void shareButtonClicked (View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plaint");
        intent.putExtra(Intent.EXTRA_TEXT, getMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}


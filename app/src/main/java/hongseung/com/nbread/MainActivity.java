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

        // 액티비티에 프래그먼트 연결

        mFragment1 = new ScreenFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1, mFragment1)
                .commit();
    }

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

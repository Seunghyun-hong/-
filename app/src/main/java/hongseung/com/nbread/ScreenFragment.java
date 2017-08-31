package hongseung.com.nbread;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenFragment extends Fragment {

    private EditText mFoodName;
    private EditText mFoodPrice;
    private EditText mFoodPeople;
    private TextView mValue;


    //엑티비티와 통신할 수있는 인터페이스를 만들자
    public interface OnSendMessageListener {
        void onSendMessage(String message); // 일단 메세지를 받고 다뿌린다 라는 걸로 할꺼라 구분필요없뜸.
    }

    //이걸 들고 있을 객체도 만들어줌

    private OnSendMessageListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_screen, container, false);
    }

    // 뷰가 다 만들어진 후 호출
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFoodName = (EditText) view.findViewById(R.id.name_edit_text);
        mFoodPrice = (EditText) view.findViewById(R.id.num1_edit_text);
        mFoodPeople = (EditText) view.findViewById(R.id.num2_edit_text);
        mValue = (TextView) view.findViewById(R.id.value_text);

        //계산 버튼 가져오자
        view.findViewById(R.id.result_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // 클릭이 되면 리스너를 가 메시지를 전달하는 거지!
                // 저 벨류값에 있는 걸.. 들고 있어라.
                mListener.onSendMessage(mValue.getText().toString());
            }
        });
    }


}
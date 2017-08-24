package hongseung.com.nbread;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenFragment extends Fragment {

    private EditText mFoodName;
    private EditText mFoodPrice;
    private EditText mFoodPeople;
    private TextView mValue;
    private Button mResultButton;
    private Button mShareButton;

    private OnSendMessageListener mListener;  // 오늘 배운 리스너

    public ScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFoodName = (EditText) view.findViewById(R.id.name_edit_text);
        mFoodPrice = (EditText) view.findViewById(R.id.num1_edit_text);
        mFoodPeople = (EditText) view.findViewById(R.id.num2_edit_text);
        mValue = (TextView) view.findViewById(R.id.value_text);

        mResultButton = (Button) view.findViewById(R.id.result_button);
        mResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSendMessage(mValue.getText().toString());
            }
        });

//        mShareButton = (Button) view.findViewById(R.id.share_button);
//        mShareButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shareButtonClicked(view);
//            }
//        });
    }

    @Override
    public void onAttach(Context context) {  // 온어테치해서 연결하는 오늘 배운거!
        super.onAttach(context);
        if (context instanceof OnSendMessageListener) {
            mListener = (OnSendMessageListener) context;
        } else {
            throw new RuntimeException(context.toString() // 에러처리도 하고 있음.
                    + " must implement OnSendMessageListener");
        }
    }

    @Override
    public void onDetach() { // 이렇게 해주고 있네. //오전 수업에서는 하지 않았음.
        super.onDetach();
        mListener = null;
    }

    //콜백 구현하기! (알아서 되어있쪄!) 우린 이름을 좀 바꿔주자.
    public interface OnSendMessageListener {
        // TODO: Update argument type and name
        void onSendMessage(String message); // 일단 메세지를 받고 다뿌린다 라는 걸로 할꺼라 구분필요없뜸.
    }

    // 메세지 뿌려주는 기능 추가!
    public void sendMessage(String text) {

        if (mFoodName.getText().length() == 0
                || mFoodPeople.getText().length() == 0
                || mFoodPrice.getText().length() == 0
//                        || mFoodPrice.getText().toString().equals("0")
//                        || mFoodPeople.getText().toString().equals("0")
                || mFoodPrice.getText().toString().startsWith("0")
                || mFoodPeople.getText().toString().startsWith("0")) {
            Toast.makeText(getActivity(), "올바른 값을 입력해주세요", Toast.LENGTH_SHORT).show();
        } else {
            String foodPrice = mFoodPrice.getText().toString();
            Integer price = Integer.parseInt(foodPrice);
            String foodPeople = mFoodPeople.getText().toString();
            Integer people = Integer.parseInt(foodPeople);

            mValue.setText("" + price / people);

            String message = "\n음식이름 : " + mFoodName.getText().toString();
            message += "\n====================";
            message += "\n총 가격 : " + mFoodPrice.getText().toString();
            message += "\n인원 : " + mFoodPeople.getText().toString();
            message += "\nN/1 가격 : " + mValue.getText() + "원";

            mValue.setText(message);

            // 초기화
            mFoodName.setText("");
            mFoodPrice.setText("");
            mFoodPeople.setText("");

            mValue.setText(text + "\n" + mValue.getText().toString());
        }
    }


//    // 공유인텐트
//    //원장님버전 (이메일로 보내는 거고 mailto는 반드시 들어가야 하네..)
//    public void shareButtonClicked(View view) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setType("text/plaint");
//        intent.putExtra(Intent.EXTRA_TEXT, mValue.getText().toString());
//
//        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }
}

//getPackageManager()
package hongseung.com.nbread;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
    private String mMessage;

    ///////////////////////////////////////////////////////////
    //엑티비티와 통신할 수있는 인터페이스를 만들자
    public interface OnSendMessageListener {
        void onSendMessage(String message); // 일단 메세지를 받고 다뿌린다 라는 걸로 할꺼라 구분필요없뜸.
    }

    //이걸 들고 있을 객체도 만들어줌
    private OnSendMessageListener mListener;
////////////////////////////////////////////////////////////////

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

        // 키보드를 바꿔보자?!
        mFoodPeople.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.ACTION_DOWN) {
                    dismissKeyboard();
                }
                return false;
            }
        });

        //계산 버튼 가져오자
        view.findViewById(R.id.result_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFoodName.getText().toString().length() == 0 ||
                        mFoodPeople.getText().toString().length() == 0 ||
                        mFoodPrice.getText().toString().length() == 0 ||
                        mFoodPrice.getText().toString().startsWith("0") ||
                        mFoodPeople.getText().toString().startsWith("0")) {
                    Toast.makeText(getContext(), "올바른 값을 넣어주세요><", Toast.LENGTH_SHORT).show();
                } else {
                    result(mFoodPrice.getText().toString(), mFoodPeople.getText().toString());
                    // 클릭이 되면 리스너를 가 메시지를 전달하는 거지!
                    // 저 벨류값에 있는 걸.. 들고 있어라.
                    mListener.onSendMessage(mValue.getText().toString());
                }
            }
        });
    }


    // 키보드 내리게 하는 기능
    private void dismissKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mFoodPeople.getWindowToken(), 0);
    }

    // 계산 할 수 있는 기능을 넣자
    public void result(String foodPrice, String foodPeople) {

//        String foodPrice = mFoodPrice.getText().toString();
        Integer price = Integer.parseInt(foodPrice);
//        String foodPeople = mFoodPeople.getText().toString();
        Integer people = Integer.parseInt(foodPeople);

        mValue.setText("" + price / people);
        int remainder = price % people;

        mMessage = "\n" + mFoodName.getText().toString();
        mMessage += "\n====================";
        mMessage += "\n총 가격 : " + mFoodPrice.getText().toString();
        mMessage += "\n인원 : " + mFoodPeople.getText().toString();
        mMessage += "\nN/1 금액 : " + mValue.getText() + "원";
        if (remainder != 0) {
            mMessage += "\n남은 금액 : " + remainder + "원";
            mMessage += "\n남은 금액은 결제자가 내주세요~>_<";
        }


//        mValue.setText("" + price % people);

//        message += "\n남은 금액 : " + mValue.getText() + "원 \n 결제자가 내는건 어때요?! 헤헤";

        mValue.setText(mMessage);
        // 초기화
        mFoodName.setText("");
        mFoodPrice.setText("");
        mFoodPeople.setText("");

    }


    ///////////////////////////////////////////////////////////////
    public ScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSendMessageListener) {
            mListener = (OnSendMessageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


//    public void sendMessage() {
//        mValue.getText();
//    }
}
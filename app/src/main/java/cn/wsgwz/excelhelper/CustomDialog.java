package cn.wsgwz.excelhelper;

/**
 * Created by admin on 2017/7/7 0007.
 */

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class CustomDialog extends Dialog {


    private MainActivity mainActivity = null;



    public CustomDialog(MainActivity mainActivity) {
        super(mainActivity, R.style.customDialog);
        this.mainActivity = mainActivity;
        setContentView();
    }



    /**
     * 设置dialog里面的view
     */
    private void setContentView()
    {

        // 加载自己定义的布局
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.view_custom_dialog, null);
        final EditText modelNoET = view.findViewById(R.id.modelNoET);
        final EditText colorET = view.findViewById(R.id.colorET);
        final EditText inTheMiddleET = view.findViewById(R.id.inTheMiddleET);
        final EditText buttomET = view.findViewById(R.id.buttomET);





        Button okBn = view.findViewById(R.id.okBn);
        Button cancelBn = view.findViewById(R.id.cancelBn);




        okBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s0 = modelNoET.getText().toString();
                String s1 = colorET.getText().toString();
                String s2 = inTheMiddleET.getText().toString();
                String s3 = buttomET.getText().toString();


                mainActivity.id++;

                ShoesSimple shoesSimple = new ShoesSimple(s0,s1,s2,s3,mainActivity.id+"");
                mainActivity.shoesSimpleList.add(shoesSimple);
                mainActivity.mainAdapter.notifyDataSetChanged();
                mainActivity.isWrite = false;






                mainActivity.toastSuccess();
            }
        });

        cancelBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();

            }
        });


        setContentView(view);
    }

}
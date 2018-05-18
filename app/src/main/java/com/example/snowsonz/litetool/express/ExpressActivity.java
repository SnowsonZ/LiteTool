package com.example.snowsonz.litetool.express;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.snowsonz.litetool.R;
import com.example.snowsonz.litetool.express.model.ExpressInfoModel;
import com.example.snowsonz.litetool.utils.CodeHelper;

import java.util.List;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

public class ExpressActivity extends AppCompatActivity implements View.OnClickListener {

    private ExpressPresenter mPresenter;
    private EditText mInputExNumEt;
    private LinearLayout mContainerExInfoLlayout;
    private Button mQueryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        initView();
        mPresenter = new ExpressPresenter();
        mPresenter.setView(this);

    }

    @Override
    public void onClick(View v) {
        //开始查询
        Editable input = mInputExNumEt.getText();
        if (mPresenter != null) {
            if (TextUtils.isEmpty(input)) {
                CodeHelper.showToast(this, "请输入运单号");
                return;
            }
            mContainerExInfoLlayout.removeAllViews();
            mPresenter.requestExpressInfo(input.toString());
        }
    }

    public void initView() {
        mInputExNumEt = findViewById(R.id.et_input_num);
        mContainerExInfoLlayout = findViewById(R.id.ll_express_info_container);
        mQueryBtn = findViewById(R.id.btn_query);
        mQueryBtn.setOnClickListener(this);
    }

    public void addExpressView(List<ExpressInfoModel.InfoItemModel> expressInfo) {
        if (CodeHelper.isEmpty(expressInfo)) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(this);
        for (ExpressInfoModel.InfoItemModel item : expressInfo) {
            View itemView = inflater.inflate(R.layout.item_express_info,
                    mContainerExInfoLlayout, false);
            TextView itemInfoTv = itemView.findViewById(R.id.tv_item_ex_info);
            TextView timeTv = itemView.findViewById(R.id.tv_item_time);
            String ftime = item.getFtime();
            Log.d(ExpressActivity.this.getClass().getName(), "123\n456");
            timeTv.setText(ftime.replaceAll(" ", "\n"));
            itemInfoTv.setText(item.getContext());
            mContainerExInfoLlayout.addView(itemView);

        }
    }
}

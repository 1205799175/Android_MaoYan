package com.yangyuning.maoyan.movie.area;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.mode.bean.AreaBean;
import com.yangyuning.maoyan.movie.MovieFragment;
import com.yangyuning.maoyan.utils.GestureHelper;
import com.yangyuning.maoyan.utils.MaoYanValue;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 城市Activity
 * @author 杨宇宁
 */
public class AreaActivity extends AbsBaseActivity {

    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;

    private CharacterParser characterParser;
    private List<AreaBean.CtsBean> SourceDateList;

    private PinyinComparator pinyinComparator;

    private ImageView backIv;
    private EventBus eventBus;

//    private GestureHelper gestureHelper;


    @Override
    protected int setLayout() {
        return R.layout.activity_area;
    }

    @Override
    protected void initView() {
        sideBar = byView(R.id.sidrbar);
        dialog = byView(R.id.dialog);
        sortListView = byView(R.id.country_lvcountry);
        mClearEditText = byView(R.id.filter_edit);
        backIv = byView(R.id.title_bar_iv_left);
        //初始化EventBus
        eventBus = EventBus.getDefault();

        //手势退出
//        gestureHelper = new GestureHelper(this);
//        gestureHelper.setListener(new GestureHelper.OnFlingListener() {
//            @Override
//            public void OnFlingLeft() {
//                finish();
//                // 退出动画
//                overridePendingTransition(R.anim.translate_exit_in, R.anim.translate_exit_out);
//            }
//
//            @Override
//            public void OnFlingRight() {
//
//            }
//        });
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return gestureHelper.onTouchEvent(event);
//    }

    @Override
    protected void initDatas() {
        //设置标题栏
        new BaseTitleBar(this).setImageLsftRes(R.mipmap.title_bar_back);
        //点击事件
        initListener();
        SourceDateList = new ArrayList<>();
        VolleyInstance.getInstance().startResult(MaoYanValue.AREA, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                AreaBean bean = gson.fromJson(resultStr, AreaBean.class);
                SourceDateList = bean.getCts();
                initViews();
            }

            @Override
            public void failure() {

            }
        });
    }

    private void initListener() {
        //返回
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AreaActivity.this.finish();
            }
        });
    }

    private void initViews() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sideBar.setTextView(dialog);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position);
                }

            }
        });

        //ListView的点击事件, 得到行布局的值, 返回MovieFragment, 切换地址
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String area = ((AreaBean.CtsBean)adapter.getItem(position)).getNm();
                eventBus.post(area);
                finish();
            }
        });

        SourceDateList = filledData(SourceDateList);

        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);

        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 给ListView设置数据
     * @return
     */
    private List<AreaBean.CtsBean> filledData(List<AreaBean.CtsBean> datas){
        List<AreaBean.CtsBean> mSortList = new ArrayList<>();

        for(int i=0; i < datas.size(); i++){
            AreaBean.CtsBean sortModel = new AreaBean.CtsBean();
            sortModel.setNm(datas.get(i).getNm());
            String pinyin = characterParser.getSelling(datas.get(i).getNm());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if(sortString.matches("[A-Z]")){
                sortModel.setPy(sortString.toUpperCase());
            }else{
                sortModel.setPy("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     *
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<AreaBean.CtsBean> filterDateList = new ArrayList<>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(AreaBean.CtsBean sortModel : SourceDateList){
                String name = sortModel.getNm();
                if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }

        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }


}

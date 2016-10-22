package com.yangyuning.maoyan.movie.area;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.mode.bean.AreaBean;

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

    private String url = "http://api.meituan.com/dianying/cities.json?__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7401&utm_source=Oppo&utm_medium=android&utm_term=7.4.0&utm_content=868853028490566&ci=65&net=255&dModel=R7Plus&uuid=D790FA371746BEECF2BBC8B2254831D38DACB3BC85736DAC8560BD590FAB382C&lat=38.883433&lng=121.544931&refer=%2FMovieMainActivity&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1477021806651&__skua=32bcf146c756ecefe7535b95816908e3&__skno=29bb7e8c-c9e7-49d8-ae07-55aee2f6e8d6&__skcy=hURTq0HBNB%2B5vnxMZdw8HZj8Igs%3D";

    private PinyinComparator pinyinComparator;


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
    }

    @Override
    protected void initDatas() {
        SourceDateList = new ArrayList<>();
        VolleyInstance.getInstance().startResult(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                AreaBean bean = gson.fromJson(resultStr, AreaBean.class);
                SourceDateList = bean.getCts();
                Log.d("qqq", "SourceDateList.size():" + SourceDateList.size());
                initViews();
            }

            @Override
            public void failure() {

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

        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplication(), ((AreaBean.CtsBean)adapter.getItem(position)).getNm(), Toast.LENGTH_SHORT).show();
            }
        });

//        SourceDateList = filledData(getResources().getStringArray(R.array.date));
        SourceDateList = filledData(SourceDateList);

        Log.d("qqq", "11111111.size():" + SourceDateList.size());
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
     * Œ™ListViewÃÓ≥‰ ˝æ›
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
     * ∏˘æ› ‰»ÎøÚ÷–µƒ÷µ¿¥π˝¬À ˝æ›≤¢∏¸–¬ListView
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

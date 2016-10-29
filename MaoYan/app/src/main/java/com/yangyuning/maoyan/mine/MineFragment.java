package com.yangyuning.maoyan.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.mine.dialog.DialogOnClickListener;
import com.yangyuning.maoyan.mine.dialog.DialogOnItemClickListener;
import com.yangyuning.maoyan.mine.dialog.MDAlertDialog;
import com.yangyuning.maoyan.mine.dialog.MDEditDialog;
import com.yangyuning.maoyan.mine.dialog.MDSelectionDialog;
import com.yangyuning.maoyan.mine.dialog.NormalAlertDialog;
import com.yangyuning.maoyan.mine.dialog.NormalSelectionDialog;
import com.yangyuning.maoyan.mine.order.PayActivity;
import com.yangyuning.maoyan.mine.refesh.RefeshActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/18.
 * 我的Fragment
 *
 * @author 杨宇宁 10.18
 */
public class MineFragment extends AbsBaseFragment implements View.OnClickListener {

    private LinearLayout pay;
    private LinearLayout wantWatched, wateced, discuss, topic, noBuy, noDicuss,
            backMoney, collect, vip, bag, money, setting;

    private NormalSelectionDialog dialog1;
    private NormalAlertDialog dialog2;
    private NormalAlertDialog dialog3;
    private MDAlertDialog dialog4;
    private MDSelectionDialog dialog5;
    private MDEditDialog dialog6;

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        pay = byView(R.id.order_pay);
        wantWatched = byView(R.id.mine_want_watch);
        wateced = byView(R.id.mine_watched);
        discuss = byView(R.id.mine_discuss);
        topic = byView(R.id.mine_topic);
        noBuy = byView(R.id.mine_order_no_buy);
        noDicuss = byView(R.id.mine_order_discuss);
        backMoney = byView(R.id.mine_back_money);
        collect = byView(R.id.mine_collect);
        vip = byView(R.id.mine_vip);
        bag = byView(R.id.mine_bag);
        money = byView(R.id.mine_little_money);
        setting = byView(R.id.mine_setting);

        pay.setOnClickListener(this);
        wantWatched.setOnClickListener(this);
        wateced.setOnClickListener(this);
        discuss.setOnClickListener(this);
        topic.setOnClickListener(this);
        noBuy.setOnClickListener(this);
        noDicuss.setOnClickListener(this);
        backMoney.setOnClickListener(this);
        collect.setOnClickListener(this);
        vip.setOnClickListener(this);
        bag.setOnClickListener(this);
        money.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        initNormalSelectDialog();
        initNormalDialog();
        initNormalDialog2();
        initMDDialog();
        initMDMidDialog();
        initMDEditDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_pay:
                context.startActivity(new Intent(context, PayActivity.class));
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.mine_want_watch:  //底部dialog
                dialog1.show();
                break;
            case R.id.mine_watched: //看过
                dialog2.show();
                break;
            case R.id.mine_discuss: //影评
                dialog3.show();
                break;
            case R.id.mine_topic:   //话题
                dialog4.show();
                break;
            case R.id.mine_order_no_buy:    //未消费
                dialog5.show();
                break;
            case R.id.mine_order_discuss: //待评价
                dialog6.show();
                break;
            case R.id.mine_back_money:  //退款
                context.startActivity(new Intent(context, RefeshActivity.class));
                getActivity().overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                break;
            case R.id.mine_collect: //收藏
                break;
            case R.id.mine_vip: //会员中心
                break;
            case R.id.mine_bag: //我的钱包
                break;
            case R.id.mine_little_money:    //我的余额
                break;
            case R.id.mine_setting: //设置中心
                break;
        }
    }

    //想看  底部Dialog
    private void initNormalSelectDialog() {
        final ArrayList<String> s = new ArrayList<>();
        s.add("电视剧");
        s.add("电影");
        s.add("动漫");
        s.add("综艺");
        dialog1 = new NormalSelectionDialog.Builder(context)
                .setlTitleVisible(true)   //设置是否显示标题
                .setTitleHeight(65)   //设置标题高度
                .setTitleText("请选择下列选项中想看的?")  //设置标题提示文本
                .setTitleTextSize(14) //设置标题字体大小 sp
                .setTitleTextColor(R.color.colorPrimary) //设置标题文本颜色
                .setItemHeight(40)  //设置item的高度
                .setItemWidth(0.9f)  //屏幕宽度*0.9
                .setItemTextColor(R.color.colorPrimaryDark)  //设置item字体颜色
                .setItemTextSize(14)  //设置item字体大小
                .setCancleButtonText("确定")  //设置最底部“取消”按钮文本
                .setOnItemListener(new DialogOnItemClickListener() {  //监听item点击事件
                    @Override
                    public void onItemClick(Button button, int position) {
                        Toast.makeText(context, "抱歉, 你选择的选项没有, 不用选了, 选啥都没有", Toast.LENGTH_SHORT).show();
                    }
                })
                .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                .build();
        dialog1.setDataList(s);
    }

    //看过    中间显示
    private void initNormalDialog(){
        dialog2 = new NormalAlertDialog.Builder(context)
                .setHeight(0.23f)  //屏幕高度*0.23
                .setWidth(0.65f)  //屏幕宽度*0.65
                .setTitleVisible(true)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.black_light)
                .setContentText("你看过吗?")
                .setContentTextColor(R.color.black_light)
                .setLeftButtonText("看过")
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText("没看过")
                .setRightButtonTextColor(R.color.black_light)
                .setOnclickListener(new DialogOnClickListener() {
                    @Override
                    public void clickLeftButton(View view) {
                        dialog2.dismiss();
                    }

                    @Override
                    public void clickRightButton(View view) {
                        dialog2.dismiss();
                    }
                })
                .build();
    }

    //影评    中间显示
    private void initNormalDialog2(){
        dialog3 = new NormalAlertDialog.Builder(context)
                .setHeight(0.23f)  //屏幕高度*0.23
                .setWidth(0.65f)  //屏幕宽度*0.65
                .setTitleVisible(true)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.colorPrimary)
                .setContentText("这是影评!!!")
                .setContentTextColor(R.color.colorPrimaryDark)
                .setSingleMode(true)
                .setSingleButtonText("关闭")
                .setSingleButtonTextColor(R.color.colorAccent)
                .setCanceledOnTouchOutside(true)
                .setSingleListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog3.dismiss();
                    }
                })
                .build();
    }

    //话题
    private void initMDDialog() {
        dialog4 = new MDAlertDialog.Builder(context)
                .setHeight(0.21f)  //屏幕高度*0.21
                .setWidth(0.7f)  //屏幕宽度*0.7
                .setTitleVisible(true)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.black_light)
                .setContentText("你知道这是话题??????")
                .setContentTextColor(R.color.black_light)
                .setLeftButtonText("知道")
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText("知道")
                .setRightButtonTextColor(R.color.black_light)
                .setTitleTextSize(16)
                .setContentTextSize(14)
                .setButtonTextSize(14)
                .setOnclickListener(new DialogOnClickListener() {
                    @Override
                    public void clickLeftButton(View view) {
                        dialog4.dismiss();
                    }
                    @Override
                    public void clickRightButton(View view) {
                        dialog4.dismiss();
                    }
                })
                .build();
    }

    //未消费
    private void initMDMidDialog(){
        final ArrayList<String> s = new ArrayList<>();
        s.add("标为未读");
        s.add("置顶聊天");
        s.add("删除该聊天");
        dialog5 = new MDSelectionDialog.Builder(context)
                .setCanceledOnTouchOutside(true)
                .setItemTextColor(R.color.black_light)
                .setItemHeight(50)
                .setItemWidth(0.8f)  //屏幕宽度*0.8
                .setItemTextSize(15)
                .setCanceledOnTouchOutside(true)
                .setOnItemListener(new DialogOnItemClickListener() {
                    @Override
                    public void onItemClick(Button button, int position) {
                        Toast.makeText(context, s.get(position), Toast.LENGTH_SHORT).show();
                        dialog5.dismiss();
                    }
                })
                .build();
        dialog5.setDataList(s);

    }

    //待评价
    private void initMDEditDialog(){
        dialog6 = new MDEditDialog.Builder(context)
                .setTitleVisible(true)
                .setTitleText("评价")
                .setTitleTextSize(20)
                .setTitleTextColor(R.color.black_light)
                .setContentText("yyn")
                .setContentTextSize(18)
                .setMaxLength(7)
                .setHintText("7位字符")
                .setMaxLines(1)
                .setContentTextColor(R.color.colorPrimary)
                .setButtonTextSize(14)
                .setLeftButtonTextColor(R.color.colorPrimary)
                .setLeftButtonText("取消")
                .setRightButtonTextColor(R.color.colorPrimary)
                .setRightButtonText("提交")
                .setLineColor(R.color.colorPrimary)
                .setOnclickListener(new MDEditDialog.OnClickEditDialogListener() {
                    @Override
                    public void clickLeftButton(View view, String editText) {
                        dialog6.dismiss();
                        Toast.makeText(context, editText.trim(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void clickRightButton(View view, String editText) {
                        Toast.makeText(context, editText.trim(), Toast.LENGTH_SHORT).show();
                        dialog6.dismiss();
                    }
                })
                .setMinHeight(0.3f)
                .setWidth(0.8f)
                .build();
    }

}

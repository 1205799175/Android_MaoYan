package com.yangyuning.maoyan.cinema;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.yangyuning.maoyan.R;


/**
 * 卫星菜单
 * 实现思路:
 * 1.自定义ViewGroup
 * (1).自定义属性: a.创建attr.xml, b.在布局中使用 C.在自定义控件中解析使用
 * (2).重写onMeasure与onLayout方法, 设置菜单按钮的布局位置
 * 2.定位Item, 设置每一个菜单项的位置
 * 3.展开Item, 动画效果的实现, 包括菜单按钮旋转动画, 菜单项平移, 旋转动画, 菜单项缩放, 透明度变换动画
 * 4.监听Item点击事件实现
 */
public class ArcMenu extends ViewGroup implements View.OnClickListener{
    private static final  int LEFT_TOP=0;
    private static final int LEFT_BOTTOM=1;
    private static final int CENTER=2;
    private static final int RIGHT_TOP=3;
    private  static final int RIGHT_BOTTOM=4;
    /**菜单按钮自动放大动画控制*/
    private State mMenuAnimationState= State.OPEN;
    /**菜单按钮字段放大动画*/
    private AnimationSet mMenuAnimation;
    /**菜单状态̬**/
    private State mState= State.CLOSE;
    /**菜单居住显示的位置*/
    private int mCenterX,mCenterY;
    /**菜单回调接口*/
    private onMenuItemClickListner mOnMenuItemClickListner;
    /**菜单按钮*/
    private View mCButton;
    /**菜单半径*/
    private int mRadius;
    /**菜单位置*/
    private  Position mPosition= Position.RIGHT_BOTTOM;
    /**菜单展开或隐藏的枚举类型*/
    private enum  State{
        OPEN,CLOSE
    }
    /**菜单位置的枚举类型*/
    private  enum  Position{
        LEFT_TOP,LEFT_BOTTOM,CENTER,RIGHT_TOP,RIGHT_BOTTOM
    }
    public ArcMenu(Context context) {
        this(context, null);
    }
    public ArcMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public ArcMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int defaultRadius= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100, getResources().getDisplayMetrics());
        //获得自定义属性
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.ArcMenu,defStyleAttr,0);
        int pos=typedArray.getInt(R.styleable.ArcMenu_position, 4);
        switch (pos){
            case LEFT_TOP:
                mPosition= Position.LEFT_TOP;
                break;
            case LEFT_BOTTOM:
                mPosition= Position.LEFT_BOTTOM;
                break;
            case CENTER:
                mPosition= Position.CENTER;
                break;
            case RIGHT_TOP:
                mPosition= Position.RIGHT_TOP;
                break;
            case RIGHT_BOTTOM:
                mPosition= Position.RIGHT_BOTTOM;
                break;
        }
        mRadius= (int) typedArray.getDimension(R.styleable.ArcMenu_radius,defaultRadius);
        typedArray.recycle();
    }
    //测量子view
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount=getChildCount();
        for (int i=0;i<childCount;i++){
            View child=getChildAt(i);
            //测量child
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    //为子view设置布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    if(changed){
        layoutCButton();//定位主菜单按钮
        int childCount=getChildCount()-1;//item项有多少个
        //定位Item
        for(int i=0;i<childCount;i++){
            View child=getChildAt(i+1);
            child.setVisibility(View.GONE);
            int left=0;
            int top=0;
            if(mPosition== Position.CENTER){
                 left= (int) (mRadius*Math.cos(Math.PI*2 / (childCount) * i));
                top= (int) (mRadius*Math.sin(Math.PI*2 / (childCount) * i));
            }else{
               left= (int) (mRadius*Math.sin(Math.PI/2/(childCount - 1) * i));
               top= (int) (mRadius*Math.cos(Math.PI/2 / (childCount - 1) * i));
            }

            int width=child.getMeasuredWidth();
            int height=child.getMeasuredHeight();
            //左下, 右下
            if(mPosition== Position.LEFT_BOTTOM||mPosition== Position.RIGHT_BOTTOM){
                top=getMeasuredHeight()-height-top;
            }
            //右下, 右上
            if(mPosition== Position.RIGHT_BOTTOM||mPosition== Position.RIGHT_TOP){
                left=getMeasuredWidth()-width-left;
            }
            //居中
            if(mPosition== Position.CENTER){
                left=mCenterX+left;
                top=mCenterY+top;
            }
            child.layout(left,top,left+child.getMeasuredWidth(),top+child.getMeasuredHeight());
        }
        startMenuAnimation(mCButton);
    }
    }
    /**定位主菜单位置*/
    private void layoutCButton() {
        if(getChildCount()>0){
            mCButton=getChildAt(0);
            mCButton.setOnClickListener(this);
        int left=0;
        int top=0;
            int width=mCButton.getMeasuredWidth();
            int height=mCButton.getMeasuredHeight();
        switch (mPosition){
            case LEFT_TOP:
                left=0;
                top=0;
                break;
            case LEFT_BOTTOM:
                left=0;
                top=getMeasuredHeight()-height;
                break;
            case CENTER:
                left=(getMeasuredWidth()-width)/2;
                top=(getMeasuredHeight()-height) / 2;
                mCenterX=left+width/10;
                mCenterY=top+height/10;
                break;
            case RIGHT_TOP:
                left=getMeasuredWidth()-width;
                top=0;
                break;
            case RIGHT_BOTTOM:
                left=getMeasuredWidth()-width;
                top=getMeasuredHeight()-height;
                break;
        }
            mCButton.layout(left,top,left+width,top+height);

        }
    }

    /**菜单项回调接口*/
    public void setOnMenuItemClickListner(onMenuItemClickListner mOnMenuItemClickListner) {
        this.mOnMenuItemClickListner = mOnMenuItemClickListner;
    }

    /**主菜单回调接口*/
    public interface  onMenuItemClickListner{
        public void onClick(View childView, int position);
    }
    /**主菜单点击事件*/
    @Override
    public void onClick(View v) {
        rotateButton(v, 0f, 360f, 300);//菜单按钮旋转效果
        toggleMenu(300);
        //开启菜单自动放大效果
      startMenuAnimation(v);


    }
    /**菜单项切换*/
    private void toggleMenu(final int duration) {
        int childCount=getChildCount()-1;//菜单项个数
        for(int i=0;i<childCount;i++){
            final View child=getChildAt(i+1);
            child.setVisibility(View.VISIBLE);//菜单项显示出来
            int left=0;//平移时, 起点的位置
            int top=0;
            if(mPosition== Position.CENTER){
                left= (int) (mRadius*Math.cos(Math.PI*2 / (childCount) * i));
                top= (int) (mRadius*Math.sin(Math.PI*2 / (childCount) * i));
            }else{
                left= (int) (mRadius*Math.sin(Math.PI/2/(childCount - 1) * i))-(mCButton.getMeasuredWidth()-child.getMeasuredWidth())/2;
                top= (int) (mRadius*Math.cos(Math.PI / 2 / (childCount - 1) * i))-(mCButton.getMeasuredHeight()-child.getMeasuredHeight())/2;
            }
            int xFlag=1;//平移标识 1:正向 -1:负向
            int yFlag=1;
            if(mPosition== Position.LEFT_BOTTOM||mPosition== Position.LEFT_TOP){
                xFlag=-1;
            }
            if(mPosition== Position.LEFT_TOP||mPosition== Position.RIGHT_TOP){
                yFlag=-1;
            }
            int startX=left*xFlag;//平移开始的位置
            int startY=top*yFlag;
            int endX=0;//平移结束的位置
            int endY=0;
            //居中
            if(mPosition== Position.CENTER){
                startX=-left;
                startY=-top;
                endX=0;
                endY=0;
            }
            //为item设置平移旋转动画
            AnimationSet animationSet=new AnimationSet(true);
            TranslateAnimation transAnimation;//平移动画
            if(mState== State.CLOSE){//需要打开时
                transAnimation=new TranslateAnimation(startX,endX,startY,endY);
                child.setClickable(true);
                child.setFocusable(true);

            }else{//需要关闭
                transAnimation=new TranslateAnimation(endX,startX,endY,startY);
                child.setClickable(false);
                child.setFocusable(false);

            }
            //旋转动画
            RotateAnimation   roateAnimation=new RotateAnimation(0,720,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            roateAnimation.setDuration(duration);
            roateAnimation.setFillAfter(true);
            transAnimation.setDuration(duration);
            transAnimation.setFillAfter(true);
            animationSet.addAnimation(roateAnimation);//动画的点击顺序对实现的效果存在影响
            animationSet.addAnimation(transAnimation);
            animationSet.setStartOffset((i * 100) / childCount);//设置每个菜单项不同的时间
            child.startAnimation(animationSet);
            //监听平移动画结束
            transAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //动画结束时, 关闭菜单时, 需要把菜单项隐藏
                    if (mState == State.CLOSE) {//需要关闭时
                        child.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
           final int poisition=i;//第几个菜单项
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnMenuItemClickListner!=null){
                        mOnMenuItemClickListner.onClick(child,poisition);
                    }
                    childClickAnimation(poisition, duration);
                    changeMenuState();
                    startMenuAnimation(mCButton);
                }
            });

        }
        //改变菜单项关闭开启的状态
        changeMenuState();
    }
    /**菜单项点击效果动画实现*/
    private void childClickAnimation(int poisition,int duration) {
        int childCount=getChildCount()-1;
        for(int i=0;i<childCount;i++){
            View child=getChildAt(i+1);
            if(i==poisition){//放大
                child.startAnimation(childBigAnimation(duration,4.0f));
            }else{//缩小
                child.startAnimation(childSmallAnimation(duration,1.0f));
            }

        }
    }
    /**菜单项点击时,缩小隐藏, 透明度变换动画*/
    private AnimationSet childSmallAnimation(int duration,float scale) {
        AnimationSet animationSet=new AnimationSet(true);
        ScaleAnimation scaleAnimation=new ScaleAnimation(scale,0.0f,scale,0.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.0f);
        animationSet.setFillAfter(true);
        animationSet.setDuration(duration);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
      return  animationSet;
    }
    /**菜单项点击时, 放大显示, 透明度变换动画*/
    private AnimationSet childBigAnimation(int duration,float scale) {
        AnimationSet animationSet=new AnimationSet(true);
        //缩小动画
        ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,scale,1.0f,scale,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.0f);
        animationSet.setFillAfter(true);
        animationSet.setDuration(duration);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        return  animationSet;
    }

    /**改变菜单项关闭开启状态̬*/
    private void changeMenuState() {
        mState=mState== State.OPEN? State.CLOSE: State.OPEN;
    }
    /**菜单项是否处于打开状态*/
    public boolean isMenuOpen(){
     return mState== State.OPEN?true:false;
    }
    /**设置菜单按钮自动缩放动画是否开启*/
    public void setmMenuAnimationState(boolean isOpen){
        mMenuAnimationState=isOpen? State.OPEN: State.CLOSE;
    }
    /**启动菜单项自动缩放动画*/
    public void startMenuAnimation(View v){
        if(mMenuAnimationState== State.OPEN){
            menuAnimation(v,1000);
            if(mState== State.CLOSE){
                v.startAnimation(mMenuAnimation);
            }else {
                v.clearAnimation();
            }
        }
    }
    /**菜单按钮旋转*/
    private void rotateButton(View v, float start, float end, int duration) {
        RotateAnimation roteAnimation=new RotateAnimation(start,end, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);//����Ϊ����
        roteAnimation.setDuration(duration);
        roteAnimation.setFillAfter(true);
        v.startAnimation(roteAnimation);
    }
    /**菜单按钮放大缩小动画*/
    private void menuAnimation(final View v,int duration){
        if(mMenuAnimation==null){
        mMenuAnimation=childBigAnimation(duration, 2.0f);

        mMenuAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                v.startAnimation(mMenuAnimation);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    }
}

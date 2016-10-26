package com.yangyuning.maoyan.mode.db;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.yangyuning.maoyan.app.MaoYanApp;
import com.yangyuning.maoyan.mode.bean.PayBean;
import com.yangyuning.maoyan.utils.MaoYanValue;

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 * LiteOrm单例类
 */
public class LiteOrmInstance {
    private static LiteOrmInstance liteOrmInstance;
    private LiteOrm liteOrm;

    public LiteOrm getLiteOrm() {
        return liteOrm;
    }

    private LiteOrmInstance() {
        liteOrm = LiteOrm.newSingleInstance(MaoYanApp.getContext(), MaoYanValue.PAY_DB_NAME);
    }

    public static LiteOrmInstance getLiteOrmInstance() {
        if (liteOrmInstance == null) {
            synchronized (LiteOrmInstance.class) {
                if (liteOrmInstance == null) {
                    liteOrmInstance = new LiteOrmInstance();
                }
            }
        }
        return liteOrmInstance;
    }

    /**
     * 插入一条数据
     */
    public void insertOne(PayBean payBean) {
        if (liteOrm != null) {
            liteOrm.insert(payBean);
        }
    }

    /**
     * 插入集合
     */
    public void insertList(List<PayBean> payBeanList) {
        if (liteOrm != null) {
            liteOrm.insert(payBeanList);
        }
    }

    /**
     * 查询全部
     */
    public List<PayBean> queryAll() {
        return liteOrm.query(PayBean.class);
    }

    /**
     * 如果有多张表
     */
    public <T> List<T> queryData(Class<T> clz){
        return liteOrm.query(clz);
    }

    /**
     * 按条件查询
     */
    public List<PayBean> queryByTitle(String title) {
        QueryBuilder<PayBean> queryBuilder = new QueryBuilder<>(PayBean.class);
        queryBuilder.where("title = ?", new Object[] {title});
        return liteOrm.query(queryBuilder);
    }

    /**
     * 多张表查询
     * @param clz
     * @param title
     * @param <T>
     * @return
     */
    public <T> List<T> queryByTitles(Class<T> clz, String title){
        QueryBuilder<T> queryBuilder = new QueryBuilder<>(clz);
        queryBuilder.where("title = ?", new Object[]{title});
        return liteOrm.query(queryBuilder);
    }

    /**
     * 删除全部
     */
    public void deleteAll(){
        liteOrm.deleteAll(PayBean.class);
    }

    /**
     * 按条件删除
     */
    public void deleteByTitle(String title){
        WhereBuilder whereBuilder = new WhereBuilder(PayBean.class, "title = ?", new String[] {title});
        liteOrm.delete(whereBuilder);
    }
    public void deleteByTitleDown(String title){
        WhereBuilder whereBuilder = new WhereBuilder(PayBean.class, "title = ?", new String[] {title});
        liteOrm.delete(whereBuilder);
    }
}

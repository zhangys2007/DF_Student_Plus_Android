package com.example.df.zhiyun.analy.mvp.contract;

import com.example.df.zhiyun.mvp.model.entity.BaseResponse;
import com.example.df.zhiyun.mvp.model.entity.ComSubjCla;
import com.example.df.zhiyun.mvp.model.entity.Grade;
import com.example.df.zhiyun.mvp.model.entity.StudentImprove;
import com.github.mikephil.charting.data.Entry;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/24/2019 10:30
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public interface GrowthTraceContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        String KEY_DATA = "key_stdudent";
        String KEY_SUBJ = "key_subjectId";
        String KEY_SUBJ_NAME = "key_subjectName";
        void setData(List<Entry> listMy, List<Entry> listClass);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
//        Observable<BaseResponse<ComSubjCla>> findClassSubjectByStudent();
//        Observable<BaseResponse<List<KnowledgePoint>>> getKnowledgePoints(String classId, String subjectId);
        Observable<BaseResponse<List<StudentImprove>>> studentImprove(String classId, String subjectId, String studentId);
    }
}

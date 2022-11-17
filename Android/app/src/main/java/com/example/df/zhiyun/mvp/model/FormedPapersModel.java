package com.example.df.zhiyun.mvp.model;

import android.app.Application;

import com.example.df.zhiyun.app.AccountManager;
import com.example.df.zhiyun.mvp.model.api.Api;
import com.example.df.zhiyun.mvp.model.api.ParamsUtils;
import com.example.df.zhiyun.mvp.model.api.service.Teacher;
import com.example.df.zhiyun.mvp.model.entity.BaseResponse;
import com.example.df.zhiyun.educate.mvp.model.entity.BelongClass;
import com.example.df.zhiyun.mvp.model.entity.FormedPaper;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.df.zhiyun.mvp.contract.FormedPapersContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/30/2019 13:08
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class FormedPapersModel extends BaseModel implements FormedPapersContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public FormedPapersModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponse<List<FormedPaper>>> formedPaperList(int page, String classId, int type) {
        Map<String,Object> params = new HashMap<>();
        params.put("classId", classId);
        params.put("type", type);
        params.put("userId", AccountManager.getInstance().getUserInfo().getUserId());
        params.put("page",page);
        params.put("size", Api.PageSize);

        return mRepositoryManager
                .obtainRetrofitService(Teacher.class)
                .formedPaperList(ParamsUtils.fromMap(mApplication,params));
    }

    @Override
    public Observable<BaseResponse<List<BelongClass>>> belongClass() {
        Map<String,Object> params = new HashMap<>();
        params.put("userId", AccountManager.getInstance().getUserInfo().getUserId());
        params.put("page",1);
        params.put("size",10);
        return mRepositoryManager
                .obtainRetrofitService(Teacher.class)
                .belongClass(ParamsUtils.fromMap(mApplication,params));
    }

    @Override
    public Observable<BaseResponse<String>> canclePutHW(String paperId, String classId,int type) {
        Map<String,Object> params = new HashMap<>();
        params.put("paperId",paperId);
        params.put("classId",classId);
        params.put("userId", AccountManager.getInstance().getUserInfo().getUserId());
        params.put("type", type);

        return mRepositoryManager
                .obtainRetrofitService(Teacher.class)
                .revokeHomeWork(ParamsUtils.fromMap(mApplication,params));
    }
}
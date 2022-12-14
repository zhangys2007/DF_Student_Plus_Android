package com.example.df.zhiyun.mvp.presenter;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.df.zhiyun.paper.mvp.contract.CardContract;
import com.example.df.zhiyun.mvp.contract.SelPaperContract;
import com.example.df.zhiyun.mvp.model.api.Api;
import com.example.df.zhiyun.mvp.model.entity.Answer;
import com.example.df.zhiyun.mvp.model.entity.BaseResponse;
import com.example.df.zhiyun.mvp.model.entity.HomeworkSet;
import com.example.df.zhiyun.mvp.model.entity.Question;
import com.example.df.zhiyun.paper.mvp.ui.activity.CardActivity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.RxLifecycleUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/03/2019 14:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">?????????????????????</a>
 * ================================================
 */
@ActivityScope
public class SelPaperPresenter extends BasePresenter<SelPaperContract.Model, SelPaperContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    String homeworkId;

    @Inject
    @Named("type")
    Integer mType;

    @Inject
    @Named("subjectId")
    Integer mSubjectId;

    long timeRemain = 0;  //???????????????
    HomeworkSet homeworkSet;
    Disposable mDisposableCount;   //
    Map<Integer, Answer> mAnswers = new HashMap<>();  //?????????????????????

    @Inject
    public SelPaperPresenter(SelPaperContract.Model model, SelPaperContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    //??????????????????????????????????????????????????????????????????????????????????????????
    public void requestNextPage(int position){
        saveCurrentPageAnswer();
        submitQuestion(mRootView.getCurrentQuestionIndex())
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse response) {
                        if(response.isSuccess()){
                            mRootView.changeViewpagerSelect(position);
                        }else{
                            mRootView.showMessage(response.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                });
    }

    //??????????????????
    public void clickCard(int position) {
        saveCurrentPageAnswer();
        submitQuestion(position)
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse response) {
                        if(response.isSuccess()){
//                            Intent intent = new Intent((Activity)mRootView, CardActivity.class);
//                            intent.putParcelableArrayListExtra(CardContract.View.KEY_ANSWER,toAnswerList(true));
//                            intent.putExtra(CardContract.View.KEY_ID,homeworkId);
//                            intent.putExtra(CardContract.View.KEY_TIME,timeRemain);
//                            intent.putExtra(CardContract.View.KEY_SUBJECTID,mSubjectId);
//                            ArmsUtils.startActivity(intent);
                            CardActivity.launchActivity((Activity)mRootView,homeworkId,homeworkId,""
                                    ,mSubjectId,"",0,timeRemain
                                    );
                        }else{
                            mRootView.showMessage(response.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                });
    }

    //?????????????????????,???????????????????????????
    public void forceSubmit(int position){
        saveCurrentPageAnswer();
        submitQuestion(position)
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse response) {
                        if(response.isSuccess()){
                            submitAnswer();
                        }else{
                            mRootView.showMessage(response.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                });
    }

    public void requestInitWithAnswer(int currentIndex){
        mRootView.initQuestionWithAnswer(currentIndex,mAnswers.get(currentIndex));
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
//        requestData();
    }

    public void requestData() {
        mModel.getHomeworkSet(homeworkId, mType,mSubjectId)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//?????????????????????,??????????????????????????????,?????????????????????????????????
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//?????????
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//??????????????????????????????
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//?????? Rxlifecycle,??? Disposable ??? Activity ????????????
                .subscribe(new ErrorHandleSubscriber<BaseResponse<HomeworkSet>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<HomeworkSet> response) {
                        if(response.isSuccess()){
                            processData(response.getData());
                        }else{
                            mRootView.showMessage(response.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                });
    }

    private void processData(HomeworkSet data){
        List<Question> questionList = data.getList();
        if(questionList == null || questionList.size() == 0){
            return;
        }

        homeworkId = data.getStudentHomeWorkId();    //
        homeworkSet = data;
        startCountTime(data);
        mRootView.initViewPager(questionList);

        requestPermission();
    }

    //????????????,?????????????????????????????????
    private void startCountTime(HomeworkSet data){
        timeRemain = data.getTime();
        if(timeRemain <= 0){      //????????????????????????
            timeRemain = Api.DEFAULT_ANSWER_TIME;
        }
        mDisposableCount =Flowable.interval(0,1, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(AndroidSchedulers.mainThread())
        .compose(RxLifecycleUtils.bindUntilEvent(mRootView, ActivityEvent.DESTROY))
        .subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                timeRemain--;
                if(timeRemain == 0){
                    saveCurrentPageAnswer();
                    mRootView.updateCountTime(aLong);
                }else if(timeRemain < 0){
                    mDisposableCount.dispose();
                    mRootView.showTimeoutDialog();
                }else{
                    mRootView.updateCountTime(aLong);
                }
            }
        });
    }



    //????????????????????????,???????????????????????????
    private void saveCurrentPageAnswer(){
        if(timeRemain >= 0) {
            int position = mRootView.getCurrentQuestionIndex();
            Answer answer = mRootView.getAnswerFromQuestion(position);
            mAnswers.put(position,answer);
        }
    }


    //?????????????????????
    private void submitAnswer() {
        mModel.submitHomeworkSet(homeworkId)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//?????????????????????,??????????????????????????????,?????????????????????????????????
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//?????????
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//??????????????????????????????
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//?????? Rxlifecycle,??? Disposable ??? Activity ????????????
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse response) {
                        if(response.isSuccess()){
                            mRootView.showMessage("???????????????");
                            mRootView.killMyself();
                        }else{
                            mRootView.showMessage(response.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                });
    }


    //????????????????????????
    private Observable<BaseResponse> submitQuestion(int index){
       return mModel.submitQuestion(homeworkId,mAnswers.get(index))
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//?????????????????????,??????????????????????????????,?????????????????????????????????
                .doOnSubscribe(disposable -> {
//                    mRootView.showLoading();//?????????
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
//                    mRootView.hideLoading();//??????????????????????????????
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//?????? Rxlifecycle,??? Disposable ??? Activity ????????????
                ;
    }


    private void requestPermission(){
        //????????????????????????????????????android6.0?????????????????????
        PermissionUtil.launchCamera(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                //request permission success, do something.
            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                mRootView.showMessage("??????????????????");
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                mRootView.showMessage("?????????????????????????????????");
            }
        }, mRootView.getRxPermissions(), mErrorHandler);
    }
}

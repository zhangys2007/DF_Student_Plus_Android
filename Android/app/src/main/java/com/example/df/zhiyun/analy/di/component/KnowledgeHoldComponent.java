package com.example.df.zhiyun.analy.di.component;

import dagger.BindsInstance;
import dagger.Component;
import com.jess.arms.di.component.AppComponent;

import com.example.df.zhiyun.analy.di.module.KnowledgeHoldModule;
import com.example.df.zhiyun.analy.mvp.contract.KnowledgeHoldContract;

import com.jess.arms.di.scope.ActivityScope;
import com.example.df.zhiyun.analy.mvp.ui.activity.KnowledgeHoldActivity;   


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/14/2020 11:11
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = KnowledgeHoldModule.class, dependencies = AppComponent.class)
public interface KnowledgeHoldComponent {
    void inject(KnowledgeHoldActivity activity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        KnowledgeHoldComponent.Builder view(KnowledgeHoldContract.View view);
        KnowledgeHoldComponent.Builder appComponent(AppComponent appComponent);
        KnowledgeHoldComponent build();
    }
}
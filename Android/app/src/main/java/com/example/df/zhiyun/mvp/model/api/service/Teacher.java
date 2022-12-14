package com.example.df.zhiyun.mvp.model.api.service;

import com.example.df.zhiyun.main.mvp.model.entity.HomePageData;
import com.example.df.zhiyun.mvp.model.entity.AnalyReport;
import com.example.df.zhiyun.mvp.model.entity.AnalysisDict;
import com.example.df.zhiyun.mvp.model.entity.BaseResponse;
import com.example.df.zhiyun.mvp.model.entity.ClassCorrectInfo;
import com.example.df.zhiyun.mvp.model.entity.ClassImprove;
import com.example.df.zhiyun.mvp.model.entity.ComSubjCla;
import com.example.df.zhiyun.mvp.model.entity.CompearItem;
import com.example.df.zhiyun.mvp.model.entity.CorrectDetail;
import com.example.df.zhiyun.mvp.model.entity.DetailTable;
import com.example.df.zhiyun.mvp.model.entity.FilterGrade;
import com.example.df.zhiyun.mvp.model.entity.FormedPaper;
import com.example.df.zhiyun.mvp.model.entity.Grade;
import com.example.df.zhiyun.mvp.model.entity.GradeAvg;
import com.example.df.zhiyun.mvp.model.entity.GradePerscent;
import com.example.df.zhiyun.mvp.model.entity.GrowthTraceItem;
import com.example.df.zhiyun.mvp.model.entity.HistoryKnowledgePoint;
import com.example.df.zhiyun.mvp.model.entity.HomeworkSet;
import com.example.df.zhiyun.mvp.model.entity.HomeworkState;
import com.example.df.zhiyun.mvp.model.entity.KnowledgeGrasp;
import com.example.df.zhiyun.mvp.model.entity.KnowledgePoint;
import com.example.df.zhiyun.mvp.model.entity.MeasureSumary;
import com.example.df.zhiyun.mvp.model.entity.Plan;
import com.example.df.zhiyun.mvp.model.entity.PlanUsage;
import com.example.df.zhiyun.mvp.model.entity.PointAnalysis;
import com.example.df.zhiyun.mvp.model.entity.PutHWDetail;
import com.example.df.zhiyun.mvp.model.entity.PutStudent;
import com.example.df.zhiyun.mvp.model.entity.ScoreCompear;
import com.example.df.zhiyun.mvp.model.entity.ScoreCompearLayer;
import com.example.df.zhiyun.mvp.model.entity.ScoreCompearLevel;
import com.example.df.zhiyun.mvp.model.entity.ScoreCompearSumary;
import com.example.df.zhiyun.mvp.model.entity.SelPaperItem;
import com.example.df.zhiyun.mvp.model.entity.StudentHomework;

import java.util.List;

import io.reactivex.Observable;

import com.example.df.zhiyun.educate.mvp.model.entity.BelongClass;
import com.example.df.zhiyun.mvp.model.entity.ClassItem;
import com.example.df.zhiyun.mvp.model.entity.CommentItem;
import com.example.df.zhiyun.mvp.model.entity.GradeItem;
import com.example.df.zhiyun.mvp.model.entity.HomeworkArrange;
import com.example.df.zhiyun.mvp.model.entity.MemberItem;
import com.example.df.zhiyun.mvp.model.entity.SchoolItem;
import com.example.df.zhiyun.mvp.model.entity.StudentImprove;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Teacher {
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/putHomeWorkList")
    Observable<BaseResponse<List<HomeworkArrange>>> putHomeWorkList(@Body RequestBody data);  //?????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/teacherBelongClass")
    Observable<BaseResponse<List<BelongClass>>> belongClass(@Body RequestBody data);  //?????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/commentingList")
    Observable<BaseResponse<List<CommentItem>>> commentList(@Body RequestBody data);  //????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/mySchool")
    Observable<BaseResponse<List<SchoolItem>>> schoolList(@Body RequestBody data);  //????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/schoolMember")
    Observable<BaseResponse<List<GradeItem>>> gradeList(@Body RequestBody data);  //????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/gradeMember")
    Observable<BaseResponse<List<ClassItem>>> classList(@Body RequestBody data);  //????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/classMember")
    Observable<BaseResponse<List<MemberItem>>> memberList(@Body RequestBody data);  //????????????


    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/unpaidStudent")
    Observable<BaseResponse<List<StudentHomework>>> unpaidStudent(@Body RequestBody data);  //????????????


    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/isCorrectionStudent")
    Observable<BaseResponse<List<StudentHomework>>> isCorrectionStudent(@Body RequestBody data);  //??????/?????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/homeworkReminder")
    Observable<BaseResponse> homeworkReminder(@Body RequestBody data);  //???????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/updateCorrectionStatus")
    Observable<BaseResponse> updateHomeWorkState(@Body RequestBody data);  //??????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/selectedPaper")
    Observable<BaseResponse<List<SelPaperItem>>> selectedPaper(@Body RequestBody data);  //??????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/formedPapersList")
    Observable<BaseResponse<List<FormedPaper>>> formedPaperList(@Body RequestBody data);  //??????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/teacherClass")
    Observable<BaseResponse<List<BelongClass>>> teacherClass(@Body RequestBody data);  //????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/putHomeWork")
    Observable<BaseResponse> putHomeWork(@Body RequestBody data);  //????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/revokeAllHomeWork")
    Observable<BaseResponse<String>> revokeHomeWork(@Body RequestBody data);  //????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/homeWorkDetails")
    Observable<BaseResponse<PutHWDetail>> putHomeWorkDetail(@Body RequestBody data);  //????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/MyPlanAndResource")
    Observable<BaseResponse<List<Plan>>> myPlan(@Body RequestBody data);  //?????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/planAndResourcePreview")
    Observable<BaseResponse<List<String>>> planResource(@Body RequestBody data);  //?????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/commentingDetail")
    Observable<BaseResponse<String>> commentingDetailUrl(@Body RequestBody data);  //???????????????url

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/correctionDetails")
    Observable<BaseResponse<CorrectDetail>> correctionDetails(@Body RequestBody data);  //??????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/homeworkCorrecting")
    Observable<BaseResponse<HomeworkSet>> homeworkCorrecting(@Body RequestBody data);  //?????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/questionsCorrect")
    Observable<BaseResponse> questionsCorrect(@Body RequestBody data);  //???????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/completeCorrections")
    Observable<BaseResponse> completeCorrections(@Body RequestBody data);  //????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/putStudents")
    Observable<BaseResponse<List<PutStudent>>> putStudents(@Body RequestBody data);  //??????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("tikuCommenton/studentImproveHistory")
    Observable<BaseResponse<List<GrowthTraceItem>>> studentImproveHistory(@Body RequestBody data);  //????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("dataAnalysis/findAnalysisDict")
    Observable<BaseResponse<List<FilterGrade>>> findAnalysisDict(@Body RequestBody data);  //??????????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("dataAnalysis/findClassSubjectByStudent")
    Observable<BaseResponse<ComSubjCla>> findClassSubjectByStudent(@Body RequestBody data);  //??????????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("student/knowledgePointAnalysis")
    Observable<BaseResponse<List<KnowledgePoint>>> knowledgePointAnalysis(@Body RequestBody data);  //????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("student/grades")
    Observable<BaseResponse<List<Grade>>> studentGrades(@Body RequestBody data);  //?????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("student/analysisReport")
    Observable<BaseResponse<AnalyReport>> analysisReport(@Body RequestBody data);  //??????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("tikuCommenton/studentImprove")
    Observable<BaseResponse<List<StudentImprove>>> studentImprove(@Body RequestBody data);  //?????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("tikuCommenton/classImproveCurveInfo")
    Observable<BaseResponse<List<ClassImprove>>> classImproveCurveInfo(@Body RequestBody data); //?????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/stuHomeworkState")
    Observable<BaseResponse<List<HomeworkState>>> homeworkState(@Body RequestBody data); //???????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/teacher/gotPlanUsageHistoryTable")
    Observable<BaseResponse<List<PlanUsage>>> planUsage(@Body RequestBody data); //??????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findHomeworkAnalysisDict")
    Observable<BaseResponse<AnalysisDict>> findHomeworkAnalysisDict(@Body RequestBody data);  //????????????????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/goTCommentonUsageHistoryTable")
    Observable<BaseResponse<List<PlanUsage>>> goTCommentonUsageHistoryTable(@Body RequestBody data);  //????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("dataAnalysis/findKnowledgeAnalysis")
    Observable<BaseResponse<List<HistoryKnowledgePoint>>> historyKnowledgeAnalysis(@Body RequestBody data);  //???????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("teacher/knowledgePointAnalysis")
    Observable<BaseResponse<List<PointAnalysis>>> pointAnalysis(@Body RequestBody data);  //???????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findHomeworkAnalysis")
    Observable<BaseResponse<List<JsonObject>>> findHomeworkAnalysis(@Body RequestBody data);  //??????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findAssignClassByGrade")
    Observable<BaseResponse<List<ClassCorrectInfo>>> findClassCorrectInfo(@Body RequestBody data);  //??????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findTestStatus")
    Observable<BaseResponse<MeasureSumary>> findTestStatus(@Body RequestBody data);  //?????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findKnowledgeScoreAnalysis")
    Observable<BaseResponse<List<KnowledgeGrasp>>> knowledgeGrasp(@Body RequestBody data);  //?????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findTestPaperAnalysis")
    Observable<BaseResponse<List<DetailTable>>> findTestPaperAnalysis(@Body RequestBody data);  //??????????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findClassScoreContrast")
    Observable<BaseResponse<ScoreCompear>> findClassScoreContrast(@Body RequestBody data);  //??????????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findClassQuestionScoreAnalysis")
    Observable<BaseResponse<CompearItem>> findClassQuestionScoreAnalysis(@Body RequestBody data);  //??????????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findClassKnowledgeScoreAnalysis")
    Observable<BaseResponse<CompearItem>> findClassKnowledgeScoreAnalysis(@Body RequestBody data);  //?????????????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findTestPaperAnalysis")
    Observable<BaseResponse<List<DetailTable>>> findTestGradePaperAnalysis(@Body RequestBody data);  //????????????-??????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findScoreOverview")
    Observable<BaseResponse<List<ScoreCompearSumary>>> findScoreOverview(@Body RequestBody data);  //????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findGradeRankDistribution")
    Observable<BaseResponse<GradePerscent>> findGradeRankDistribution(@Body RequestBody data);  //????????????????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findAverageContrast")
    Observable<BaseResponse<List<GradeAvg>>> findAverageContrast(@Body RequestBody data);  //????????????????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findGradeDistribution")
    Observable<BaseResponse<List<ScoreCompearLevel>>> findGradeDistribution(@Body RequestBody data);  //??????????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("homeworkDataAnalysis/findArrangementContrast")
    Observable<BaseResponse<List<ScoreCompearLayer>>> findArrangementContrast(@Body RequestBody data);  //??????????????????????????????????????????

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("api/v1/teacher/homePage")
    Observable<BaseResponse<HomePageData>> getHomepageData(@Body RequestBody data);
}

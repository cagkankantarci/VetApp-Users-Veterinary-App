package com.example.forev.vetapp.RestApi;

import com.example.forev.vetapp.Models.AnswerModel;
import com.example.forev.vetapp.Models.AskQuestionModel;
import com.example.forev.vetapp.Models.CampaignsModel;
import com.example.forev.vetapp.Models.DeleteAnswerModel;
import com.example.forev.vetapp.Models.LoginModel;
import com.example.forev.vetapp.Models.PetModel;
import com.example.forev.vetapp.Models.RegisterPojo;
import com.example.forev.vetapp.Models.VacModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @FormUrlEncoded
    @POST("/veterinaryfolder/register.php")
    Call<RegisterPojo> registerUser(@Field("email") String email, @Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/veterinaryfolder/login.php")
    Call<LoginModel> loginUser(@Field("email") String email, @Field("password") String username);

    @FormUrlEncoded
    @POST("/veterinaryfolder/mypets.php")
    Call<List<PetModel>> getPets(@Field("id") String id);

    @FormUrlEncoded
    @POST("/veterinaryfolder/askme.php")
    Call<AskQuestionModel> askQuestion(@Field("id") String id, @Field("question") String question);

    @FormUrlEncoded
    @POST("/veterinaryfolder/deleteanswer.php")
    Call<DeleteAnswerModel> deleteAnswer(@Field("answer") String answerid, @Field("question") String questionid);

    @FormUrlEncoded
    @POST("/veterinaryfolder/answer.php")
    Call<List<AnswerModel>> getAnswers(@Field("custid") String cust_id);

    @GET("/veterinaryfolder/campaigns.php")
    Call<List<CampaignsModel>> getCampaigns();

    @FormUrlEncoded
    @POST("/veterinaryfolder/vaccalendar.php")
    Call<List<VacModel>> getVac(@Field("custid") String custid);

    @FormUrlEncoded
    @POST("/veterinaryfolder/pastvac.php")
    Call<List<VacModel>> getPastVac(@Field("custid") String custid,@Field("petid") String petid);
}

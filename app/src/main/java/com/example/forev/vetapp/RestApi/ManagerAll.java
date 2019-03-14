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

public class ManagerAll extends BaseManager {

    private  static ManagerAll ourInstance = new ManagerAll();

    public  static synchronized ManagerAll getInstance()
    {
        return  ourInstance;
    }

    public Call<RegisterPojo> addUser(String email , String username, String password)
    {
        Call<RegisterPojo> x = getRestApi().registerUser(email,username,password);
        return  x ;
    }

    public Call<LoginModel> loginUser(String email , String password)
    {
        Call<LoginModel> x = getRestApi().loginUser(email,password);
        return  x ;
    }

    public Call<List<PetModel>> getPets(String id)
    {
        Call<List<PetModel>> x = getRestApi().getPets(id);
        return  x ;
    }

    public Call<AskQuestionModel> askQuestion(String id , String question)
    {
        Call<AskQuestionModel> x = getRestApi().askQuestion(id,question);
        return  x ;
    }

    public Call<List<AnswerModel>> getAnswers(String cust_id)
    {
        Call<List<AnswerModel>> x = getRestApi().getAnswers(cust_id);
        return  x ;
    }

    public Call<List<CampaignsModel>> getCampaigns()
    {
        Call<List<CampaignsModel>> x = getRestApi().getCampaigns();
        return  x ;
    }

    public Call<DeleteAnswerModel> deleteAnswer(String answer , String question)
    {
        Call<DeleteAnswerModel> x = getRestApi().deleteAnswer(answer,question);
        return  x ;
    }

    public Call<List<VacModel>> getVac(String custid)
    {
        Call<List<VacModel>> x = getRestApi().getVac(custid);
        return  x ;
    }

    public Call<List<VacModel>> getPastVac(String custid,String petid)
    {
        Call<List<VacModel>> x = getRestApi().getPastVac(custid,petid);
        return  x ;
    }
}

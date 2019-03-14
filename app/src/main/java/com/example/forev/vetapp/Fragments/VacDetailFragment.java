package com.example.forev.vetapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.forev.vetapp.Adapters.PastVacAdapter;
import com.example.forev.vetapp.Models.VacModel;
import com.example.forev.vetapp.R;
import com.example.forev.vetapp.RestApi.ManagerAll;
import com.example.forev.vetapp.Utils.ChangeFragments;
import com.example.forev.vetapp.Utils.GetSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VacDetailFragment extends Fragment {

    View view;
    private String custid;
    private String petId;
    private GetSharedPreferences getSharedPreferences;
    private RecyclerView vacDetailRecyclerView;
    private PastVacAdapter pastVacAdapter;
    private List<VacModel> vacModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_vac_detail, container, false);
        defineLayout();
        getPastVac();
        return view;
    }

    public void defineLayout()
    {
        petId = getArguments().getString("petid").toString();
        getSharedPreferences = new GetSharedPreferences(getActivity());
        custid = getSharedPreferences.getSession().getString("cust_id",null);
        vacDetailRecyclerView = (RecyclerView)view.findViewById(R.id.vacDetailRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        vacDetailRecyclerView.setLayoutManager(layoutManager);
        vacModelList = new ArrayList<>();
    }

    public void getPastVac()
    {
        Call<List<VacModel>> req = ManagerAll.getInstance().getPastVac(custid,petId);
        req.enqueue(new Callback<List<VacModel>>() {
            @Override
            public void onResponse(Call<List<VacModel>> call, Response<List<VacModel>> response) {
                if(response.body().get(0).isTf())
                {
                    vacModelList = response.body();
                    pastVacAdapter = new PastVacAdapter(vacModelList,getContext());
                    vacDetailRecyclerView.setAdapter(pastVacAdapter);
                    Toast.makeText(getContext(),"Your pet has "+ vacModelList.size()+" vaccines..",Toast.LENGTH_LONG).show();
                }
                else{
                    ChangeFragments changeFragments = new ChangeFragments(getContext());
                    changeFragments.change(new ReportCardFragment());
                    Toast.makeText(getContext(),"There is no vaccation.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<VacModel>> call, Throwable t) {

            }
        });
    }

}

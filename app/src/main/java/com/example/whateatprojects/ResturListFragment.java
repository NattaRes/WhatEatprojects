package com.example.whateatprojects;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whateatprojects.Inteface.ItemClickListener;
import com.example.whateatprojects.Model.Resturantaf;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.whateatprojects.Inteface.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResturListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResturListFragment extends Fragment {

    TextView textView;
    String getter;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference pather;

    String GetfoodID = "";

    FirebaseRecyclerAdapter<Resturantaf, resturAdapter> adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResturListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResturListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResturListFragment newInstance(String param1, String param2) {
        ResturListFragment fragment = new ResturListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restur_list, container, false);

        database = FirebaseDatabase.getInstance();

        GetfoodID = getArguments().getString("foodID");

        pather = database.getReference("foodinres/" + GetfoodID);

        recyclerView = (RecyclerView) view.findViewById(R.id.resturlist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);

        loadRes(GetfoodID);

        return view;
    }

    private void loadRes(String getfoodID) {
        adapter = new FirebaseRecyclerAdapter<Resturantaf, resturAdapter>(Resturantaf.class, R.layout.resitem,
                resturAdapter.class,pather.orderByKey()) {
            @Override
            protected void populateViewHolder(resturAdapter resturAdapter, Resturantaf resturantaf, int i) {
                resturAdapter.restuname.setText(resturantaf.getName());
                resturAdapter.pricer.setText(resturantaf.getPrice());
                resturAdapter.locatis.setText(resturantaf.getLocatinos());

                Resturantaf click = resturantaf;
                resturAdapter.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
package com.example.whateatprojects;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whateatprojects.Inteface.Callback;
import com.example.whateatprojects.Inteface.ItemClickListener;
import com.example.whateatprojects.Model.Menufood;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.whateatprojects.Inteface.Callback;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenufoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenufoodFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference menuList;

    String CataID="";

    FirebaseRecyclerAdapter<Menufood,MenuAdapter> adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenufoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenufoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenufoodFragment newInstance(String param1, String param2) {
        MenufoodFragment fragment = new MenufoodFragment();
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
        View view = inflater.inflate(R.layout.fragment_menufood, container, false);

        database = FirebaseDatabase.getInstance();
        menuList = database.getReference("food");

        recyclerView = (RecyclerView) view.findViewById(R.id.menulist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);

        CataID = getArguments().getString("CateID");

        loadMenu(CataID);

        return view;
    }

    private void loadMenu(String cataID) {
        adapter = new FirebaseRecyclerAdapter<Menufood, MenuAdapter>(Menufood.class, R.layout.menuitem,
                MenuAdapter.class,menuList.orderByChild("CataID").equalTo(cataID)) {
            @Override
            protected void populateViewHolder(MenuAdapter menuAdapter, Menufood food, int i) {
                menuAdapter.foodname.setText(food.getName());
//                Picasso.with(getBaseContext()).load(food.getImage()).into(menuAdapter.foodimage);
                Picasso.get().load(food.getImage()).into(menuAdapter.foodimage);
                Menufood click = food;
                menuAdapter.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(getActivity(), resturList.class);
                        intent.putExtra("foodID", adapter.getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
package com.example.whateatprojects;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whateatprojects.Inteface.ItemClickListener;
import com.example.whateatprojects.Model.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import com.example.whateatprojects.Inteface.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    Callback callback;

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference category;
    LinearLayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category,MyAdapter> adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        recyclerView = (RecyclerView) view.findViewById(R.id.userList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);

        load();

        return view;
    }

    private void load(){
        adapter = new FirebaseRecyclerAdapter<Category, MyAdapter>(Category.class,R.layout.item,
                MyAdapter.class,category) {
            @Override
            protected void populateViewHolder(MyAdapter myAdapter, Category category, int i) {
                myAdapter.Name.setText(category.getName());
//                Picasso.with(getBaseContext()).load(category.getImage()).into(myAdapter.imageView);
                Picasso.get().load(category.getImage()).into(myAdapter.imageView);
                Category click = category;
                myAdapter.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        MenufoodFragment fragment;
                        fragment = new MenufoodFragment();
                        Bundle args = new Bundle();
                        args.putString("CateID", adapter.getRef(position).getKey());
                        fragment.setArguments(args);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.Fcontainer, fragment).addToBackStack(null).commit();
//                        callback.openFragment(fragment);

//                        Toast.makeText(Home.this, ""+click.getName(), Toast.LENGTH_SHORT).show();
//                        Intent CataIntent = new Intent(CategoryFragment.this,menufood.class);
//                        CataIntent.putExtra("CataID", adapter.getRef(position).getKey());
//                        startActivity(CataIntent);
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
    }
}
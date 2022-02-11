package com.example.whateatprojects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.example.whateatprojects.Inteface.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RandomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomFragment extends Fragment {

    Callback callback;

    TextView txt;
    Button nxt, rn;

    String foodID, Name;

    FirebaseDatabase ran;
    DatabaseReference databaseReference, referver;

    public Object Home;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (Callback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement Callback");
        }
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RandomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RandomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RandomFragment newInstance(String param1, String param2) {
        RandomFragment fragment = new RandomFragment();
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
        View view = inflater.inflate(R.layout.fragment_random, container, false);

        txt = (TextView) view.findViewById(R.id.txtran);
        rn = (Button) view.findViewById(R.id.rnd);
        nxt = (Button) view.findViewById(R.id.next);

        rn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("food");
                databaseReference.addValueEventListener(new ValueEventListener() {

                    // Event listenere to update.
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> productIdsList = new ArrayList<>();
                        for(DataSnapshot data: dataSnapshot.getChildren()){
                            String productId = data.getKey();
                            productIdsList.add(productId);
                        }
                        Collections.shuffle(productIdsList, new Random());
                        int counter = 0;

                        foodID = productIdsList.get(counter);

                        Name = dataSnapshot.child(foodID).child("name").getValue(String.class);

                        txt.setText(Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ResturListFragment fragment;
//                fragment = new ResturListFragment();
//                Bundle args = new Bundle();
//                args.putString("foodID", foodID);
//                fragment.setArguments(args);
//                callback.openFragment(fragment);
                Intent intent = new Intent(getActivity(), resturList.class);
                intent.putExtra("foodID", foodID);
                startActivity(intent);
            }
        });

        return view;
    }
}
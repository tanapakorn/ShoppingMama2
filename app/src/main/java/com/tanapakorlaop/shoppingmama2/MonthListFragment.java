package com.tanapakorlaop.shoppingmama2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.tanapakorlaop.shoppingmama2.dummy.DummyContent;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class MonthListFragment extends ListFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private ArrayList<SaveMonth> monthly = new ArrayList<>();
    MonthAdapter monthAdapter;
    // TODO: Rename and change types of parameters
    public static MonthListFragment newInstance(String param1, String param2) {
        MonthListFragment fragment = new MonthListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MonthListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        monthly = ((MainActivity)getActivity()).getMonthly();
        //monthly.add(new SaveMonth("Create New List","dd/mm/yyyy"," 0 Listed",android.R.drawable.ic_input_add));
        //monthly.add(0,new SaveMonth("222 22 2222","dd/mm/yyyy"," 1 Listed",android.R.drawable.ic_input_add));
        // TODO: Change Adapter to display your content
        monthAdapter = new MonthAdapter(getActivity(), R.layout.save_list_layout, monthly);
        setListAdapter(monthAdapter);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            //mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
            monthly = ((MainActivity)getActivity()).getMonthly();
            if(position == monthly.size()-1){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("New List");
                builder.setMessage("Do you want to create a New List?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(((MainActivity)getActivity()).getCurrentDate(monthly.get(position).getDate()));
                builder.setMessage(monthly.get(position).getSumPrice() + " Price\n"
                        + monthly.get(position).getListed());
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // ((MainActivity)getActivity()).editList(table);
                        String tableName = monthly.get(position).getMonth();
                       // String tableName = monthly.get(position).getMonth()+String.valueOf(monthly.get(position).get_id());
                        ((MainActivity)getActivity()).editList(tableName);
                        Toast.makeText(getActivity(),tableName,Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}

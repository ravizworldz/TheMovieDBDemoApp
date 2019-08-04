package com.android.stanley.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.stanley.R;
import com.android.stanley.constants.AppConstants;
import com.android.stanley.models.MovieDetailsModel;
import com.android.stanley.ui.DetailActivity;
import com.android.stanley.ui.adapter.IHandleRowClick;
import com.android.stanley.ui.adapter.RecyclerViewAdapter;
import com.android.stanley.utils.LogUtils;
import com.android.stanley.viewmodel.HistoryActivityViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {
    private static final String TAG = "HistoryFragment";
    private RecyclerViewAdapter adapter;

    @BindView(R.id.listRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvnoresult)
    TextView tvNoResult;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment HistoryFragment.
     */
    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.log(TAG, "onResume called");
        initViewModel();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.log(TAG, "onViewCreated called");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Initialize and request to get the data through view model.
     */
    private void initViewModel() {
        LogUtils.log(TAG,"historyfragment initViewModel called : ");
        HistoryActivityViewModel historyActivityViewModel = ViewModelProviders.of(this).get(HistoryActivityViewModel.class);
        historyActivityViewModel.getMovieDataList();

        final Observer<List<MovieDetailsModel>> listObserver = new Observer<List<MovieDetailsModel>>() {
            @Override
            public void onChanged(@Nullable final List<MovieDetailsModel> updatedList) {

                LogUtils.log(TAG,"historyfragment onChanged called : updatedList : " + updatedList);
                if(updatedList != null && updatedList.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    tvNoResult.setVisibility(View.GONE);
                    adapter.setUpdatedData(updatedList);
                    recyclerView.setAdapter(adapter);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    tvNoResult.setVisibility(View.VISIBLE);
                }
            }
        };
        historyActivityViewModel.getMovieDataList().observe(this, listObserver);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtils.log(TAG, "onCreateView called");
        View rootView  = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, rootView);
        setRecyclerViewAdapter();
        return rootView;
    }

    /**
     * Setting the adapter for recycler view
     */
    private void setRecyclerViewAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(getActivity(), null, new IHandleRowClick() {
            @Override
            public void handleRowClick(MovieDetailsModel model) {
                LogUtils.log(TAG, "====model title :" + model.getTitle() + ", release date  :" +model.getRelease_date());
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(AppConstants.DATA, model.toJSONString());
                intent.putExtra(AppConstants.FROM,1);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

    }
}

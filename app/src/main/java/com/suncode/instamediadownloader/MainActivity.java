package com.suncode.instamediadownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.suncode.instamediadownloader.adapter.InstaAdapter;
import com.suncode.instamediadownloader.helper.InstaGenerator;
import com.suncode.instamediadownloader.model.Graphql;
import com.suncode.instamediadownloader.model.InstaModel;
import com.suncode.instamediadownloader.service.InstaService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText mLinkEditText;
    private Button mRequestLinkButton;

    public static final String CONTENT_TYPE_IMAGE = "GraphImage";
    public static final String CONTENT_TYPE_VIDEO = "GraphVideo";
    public static final String CONTENT_TYPE_SIDECAR = "GraphSidecar";

    private ArrayList<String> mData;

    private RecyclerView mMainRecycleview;
    private InstaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinkEditText = findViewById(R.id.editTextLink);
        mRequestLinkButton = findViewById(R.id.buttonRequestLink);

        mData = new ArrayList<>();

        mMainRecycleview = findViewById(R.id.recycleviewMain);

        mRequestLinkButton.setOnClickListener(v -> {
            mData.clear();
            requestData();
        });

    }

    private void requestData() {
        if (TextUtils.isEmpty(mLinkEditText.getText().toString())) {
            shortToast("Link must be filled");
            return;
        }

        if (!isUrlInstagram(mLinkEditText.getText().toString())) {
            shortToast("Invalid Link!");
            return;
        }

        InstaService service = InstaGenerator.build(mLinkEditText.getText().toString()).create(InstaService.class);
        Call<InstaModel> call = service.getData();

        call.enqueue(new Callback<InstaModel>() {
            @Override
            public void onResponse(Call<InstaModel> call, Response<InstaModel> response) {

                String typename = response.body().graphql.shortcodeMedia.getTypename();

                if (typename.equals(CONTENT_TYPE_IMAGE)) {
                    String displayUrl = response.body().graphql.shortcodeMedia.getDisplayUrl();
                    mData.add(displayUrl);
                    downloadImage(mData);
                }

//                if (typename == CONTENT_TYPE_IMAGE) {
//
////                    mData.add(displayUrl);
//                    downloadImage(displayUrl);
//                } else if (typename == CONTENT_TYPE_VIDEO) {
////                    String displayUrl = response.body().graphql.shortcodeMedia.getDisplayUrl();
////                    String videoUrl = response.body().graphql.shortcodeMedia.getVideoUrl();
////                    downloadVideo(videoUrl);
//                } else {
////                    downloadSideCar();
//                }
            }

            @Override
            public void onFailure(Call<InstaModel> call, Throwable t) {

            }
        });
    }

    private void downloadImage(ArrayList<String> urls) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mMainRecycleview.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(this, layoutManager.getOrientation());
        mMainRecycleview.addItemDecoration(divider);

        mAdapter = new InstaAdapter(this, urls);
        mMainRecycleview.setAdapter(mAdapter);
    }

    private void downloadVideo(String urlVideo) {

    }

    private void downloadSideCar() {

    }

    private boolean isUrlInstagram(String url) {
        if (url.contains("www.instagram.com"))
            return true;
        else
            return false;
    }

    private void shortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
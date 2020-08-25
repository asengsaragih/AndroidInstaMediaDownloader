package com.suncode.instamediadownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.suncode.instamediadownloader.helper.InstaGenerator;
import com.suncode.instamediadownloader.model.Graphql;
import com.suncode.instamediadownloader.model.InstaModel;
import com.suncode.instamediadownloader.service.InstaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText mLinkEditText;
    private Button mRequestLinkButton;
    public static final String NUMBER_QUERY = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinkEditText = findViewById(R.id.editTextLink);
        mRequestLinkButton = findViewById(R.id.buttonRequestLink);

        mRequestLinkButton.setOnClickListener(v -> {
            requestData();
        });

    }

    private void requestData() {
        if (TextUtils.isEmpty(mLinkEditText.getText().toString())) {
            shortToast("Link must be filled");
            return;
        }

//        shortToast(mLinkEditText.getText().toString());

        InstaService service = InstaGenerator.build(mLinkEditText.getText().toString()).create(InstaService.class);
        Call<InstaModel> call = service.getData();

        call.enqueue(new Callback<InstaModel>() {
            @Override
            public void onResponse(Call<InstaModel> call, Response<InstaModel> response) {
                InstaModel model = response.body();

                Graphql data = model.getGraphql();

                Log.d("CHECKTAG", response.body().graphql.getShortcode_media().getTypename());
            }

            @Override
            public void onFailure(Call<InstaModel> call, Throwable t) {

            }
        });
//
//        call.enqueue(new Callback<Graphql>() {
//            @Override
//            public void onResponse(Call<Graphql> call, Response<Graphql> response) {
////                Graphql data = response.body();
//                Log.d("CHECKK", response.toString());
//            }
//
//            @Override
//            public void onFailure(Call<Graphql> call, Throwable t) {
//
//            }
//        });

    }

    private void shortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
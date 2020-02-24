package com.example.test.app.view.customview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.test.app.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CustomLoadingView extends FrameLayout {
    private ProgressBar pbLoading;
    private ImageView ivRetry;
    private FrameLayout flContainer;

    public CustomLoadingView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public CustomLoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomLoadingView(@NonNull Context context, @Nullable AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomLoadingView(@NonNull Context context, @Nullable AttributeSet attrs,
                             int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(@NonNull Context context,
                      @Nullable AttributeSet attrs) {

        inflate(context, R.layout.custom_loading_view, this);
        pbLoading = findViewById(R.id.pbLoading);
        ivRetry = findViewById(R.id.ivRetry);
        flContainer = findViewById(R.id.flContainer);

    }

    public void showLoading() {
        pbLoading.setVisibility(VISIBLE);
        ivRetry.setVisibility(GONE);
        flContainer.setVisibility(VISIBLE);
    }

    public void hide() {
        pbLoading.setVisibility(GONE);
        ivRetry.setVisibility(GONE);
        flContainer.setVisibility(GONE);
    }

    public void showRetry() {
        pbLoading.setVisibility(GONE);
        ivRetry.setVisibility(VISIBLE);
        flContainer.setVisibility(VISIBLE);
    }

    public void setOnRetryClicked(OnClickListener onRetryClicked) {
        ivRetry.setOnClickListener(onRetryClicked);
    }
}

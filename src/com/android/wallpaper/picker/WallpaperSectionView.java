/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.wallpaper.picker;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;

import com.android.wallpaper.picker.SectionView;
import com.android.wallpaper.R;
import com.android.wallpaper.util.ScreenSizeCalculator;
import com.android.wallpaper.util.SizeCalculator;

/**
 * The wallpaper section view in the Customization Hub fragment.
 */
public final class WallpaperSectionView extends SectionView {

    private CardView mHomePreviewCard;
    private CardView mLockscreenPreviewCard;

    public WallpaperSectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        matchDeviceShape(mHomePreviewCard);
        matchDeviceShape(mLockscreenPreviewCard);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHomePreviewCard = findViewById(R.id.home_preview);
        mLockscreenPreviewCard = findViewById(R.id.lock_preview);

        // Disable the shadows of these card views.
        mHomePreviewCard.setCardElevation(0);
        mLockscreenPreviewCard.setCardElevation(0);
    }

    private void matchDeviceShape(CardView cardView) {
        // Match device aspect ratio
        float screenAspectRatio =
                ScreenSizeCalculator.getInstance().getScreenAspectRatio(getContext());
        int cardWidth = cardView.getMeasuredWidth();
        int cardHeight = (int) (cardWidth * screenAspectRatio);
        ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
        layoutParams.height = cardHeight;

        // Match device corner
        cardView.setRadius(
                SizeCalculator.getPreviewCornerRadius((Activity) getContext(), cardWidth));
    }
}
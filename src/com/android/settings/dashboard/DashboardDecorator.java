/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.android.settings.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.TypedValue;
import android.view.View;
import com.android.settings.R;

public class DashboardDecorator extends RecyclerView.ItemDecoration {

    private final Context mContext;
    private final Drawable mDivider;

    public DashboardDecorator(Context context) {
        mContext = context;
        TypedValue value = new TypedValue();
        mContext.getTheme().resolveAttribute(android.R.attr.listDivider, value, true);
        mDivider = mContext.getDrawable(value.resourceId);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, State state) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final ViewHolder holder = parent.getChildViewHolder(child);

            if (holder.getItemViewType() == R.layout.dashboard_tile) {
                int top = getChildTop(child);
                int left = getChildLeft(child);
                mDivider.setBounds(left, top, child.getRight(),
                        top + mDivider.getIntrinsicHeight());
                mDivider.draw(c);
            }
        }
    }

    private int getChildTop(View child) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        return child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
    }

    private int getChildLeft(View child) {
        final int margin = mContext.getResources().getDimensionPixelSize(R.dimen.dashboard_tile_image_size)
            + mContext.getResources().getDimensionPixelSize(R.dimen.dashboard_tile_image_margin)
            + mContext.getResources().getDimensionPixelSize(R.dimen.dashboard_tile_image_margin)
            - mContext.getResources().getDimensionPixelSize(R.dimen.dashboard_tile_divider_offset);
        return child.getLeft() + margin;
    }
}

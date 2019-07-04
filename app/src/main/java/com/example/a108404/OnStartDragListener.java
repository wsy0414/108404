package com.example.a108404;

import android.support.v7.widget.RecyclerView;

public interface OnStartDragListener {
    /**
     * 當View需要拖拽時回撥
     *
     * @param viewHolder The holder of view to drag
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
    void onStartSwip(RecyclerView.ViewHolder viewHolder);
}

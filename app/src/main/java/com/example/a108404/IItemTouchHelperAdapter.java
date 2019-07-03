package com.example.a108404;

public interface IItemTouchHelperAdapter {
    /**
     * 當item被移動時呼叫
     *
     * @param fromPosition 被操作的item的起點
     * @param toPosition  被操作的item的終點
     */
    void onItemMove(int fromPosition, int toPosition);
    /**
     * 當item被側滑時呼叫
     *
     * @param position 被側滑的item的position
     */
    void onItemDismiss(int position);
}

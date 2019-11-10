package com.example.a108404.Service;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.a108404.Adapter.IItemTouchHelperAdapter;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;

public class MyItemTouchHelperCallback extends ItemTouchHelperExtension.Callback {
    public IItemTouchHelperAdapter mAdapter;
    public MyItemTouchHelperCallback(IItemTouchHelperAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

//上下拖拽，若有其他需求同理
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//向右側滑，若有其他需求同理
        //int swipeFlags = ItemTouchHelper.LEFT;


        return makeMovementFlags(dragFlags, ItemTouchHelper.START);
    }
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//通知Adapter更新資料和檢視
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
//若返回false則表示不支援上下拖拽
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.d("onSwiped", "ok");
    }

//    @Override
//    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        Log.d("onChildDraw", "ok");
//        SetAdapter.addViewHolder holder = (SetAdapter.addViewHolder) viewHolder;
//        if (viewHolder != null) {
//            if (dX < -holder.getActionWidth()) {
//                dX = -holder.getActionWidth() + 300;
//            }
//            holder.itemView.scrollTo(-(int) dX, 0);
//        }
//    }
}

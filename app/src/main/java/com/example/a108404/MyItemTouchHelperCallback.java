package com.example.a108404;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private IItemTouchHelperAdapter mAdapter;
    //限制ImageView長度所能增加的最大值
    private double ICON_MAX_SIZE = 50;
    //ImageView的初始長寬
    private int fixedWidth = 150;
    public MyItemTouchHelperCallback(IItemTouchHelperAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//上下拖拽，若有其他需求同理
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//向右側滑，若有其他需求同理
        int swipeFlags = ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipeFlags);
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
//通知Adapter更新資料和檢視
        //mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
    @Override
    public boolean isItemViewSwipeEnabled() {
//是否可以左右側滑，預設返回true
        return true;
    }
    @Override
    public boolean isLongPressDragEnabled() {
//是否可以長按上下拖拽，預設返回false
        return false;
    }
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        super.clearView(recyclerView, viewHolder);
//        //重置改变，防止由于复用而导致的显示问题
//        viewHolder.itemView.setScrollX(0);
//        ((SetAdapter.addViewHolder)viewHolder).dltBtn.setText("删除");
////        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((SetAdapter.addViewHolder) viewHolder).getLayoutParams();
////        params.width = 150;
////        params.height = 150;
////        ((MyAdapter.NormalItem) viewHolder).iv.setLayoutParams(params);
////        ((MyAdapter.NormalItem) viewHolder).iv.setVisibility(View.INVISIBLE);

        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //仅对侧滑状态下的效果做出改变
//        if (actionState ==ItemTouchHelper.ACTION_STATE_SWIPE){
//            //如果dX小于等于删除方块的宽度，那么我们把该方块滑出来
//            if (Math.abs(dX) <= getSlideLimitation(viewHolder)){
//                viewHolder.itemView.scrollTo(-(int) dX,0);
//            }
////            //如果dX还未达到能删除的距离，此时慢慢增加“眼睛”的大小，增加的最大值为ICON_MAX_SIZE
//////            else if (Math.abs(dX) <= recyclerView.getWidth() / 2){
//////                double distance = (recyclerView.getWidth() / 2 -getSlideLimitation(viewHolder));
//////                double factor = ICON_MAX_SIZE / distance;
//////                double diff =  (Math.abs(dX) - getSlideLimitation(viewHolder)) * factor;
//////                if (diff >= ICON_MAX_SIZE)
//////                    diff = ICON_MAX_SIZE;
////                //((SetAdapter.addViewHolder)viewHolder).dltBtn.setText("");   //把文字去掉
////                //((MyAdapter.NormalItem) viewHolder).iv.setVisibility(View.VISIBLE);  //显示眼睛
//////                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ((MyAdapter.NormalItem) viewHolder).iv.getLayoutParams();
//////                params.width = (int) (fixWidth + diff);
//////                params.height = (int) (fixWidth + diff);
//////                ((MyAdapter.NormalItem) viewHolder).iv.setLayoutParams(params);
////
//        }else {
//            //拖拽状态下不做改变，需要调用父类的方法
//            super.onChildDraw(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive);
//        }
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            Log.d("swipe", "ok");
            //if (viewHolder instanceof SetAdapter.addViewHolder) {
            SetAdapter.addViewHolder holder = (SetAdapter.addViewHolder) viewHolder;
            float actionWidth = holder.getActionWidth();
            if (dX < -actionWidth) {
                dX = -actionWidth;
            }
            //dX = (float)dX;
            ((SetAdapter.addViewHolder) viewHolder).dltBtn.setTranslationX((int) dX);
            //}
        }
    }

    /**
     * 获取删除方块的宽度
     */
    public int getSlideLimitation(RecyclerView.ViewHolder viewHolder){
        ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;
        return viewGroup.getChildAt(1).getLayoutParams().width;
    }
}

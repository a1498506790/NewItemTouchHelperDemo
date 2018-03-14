package com.mir.itemtouchhelperdemo;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * @author by lx
 * @github https://github.com/a1498506790
 * @data 2018-03-14
 * @desc
 */

public class SimpleItemTouchHelper extends ItemTouchHelper.Callback {

    private ItemTouchHelperAdapter mHelperAdapter;

    public SimpleItemTouchHelper(ItemTouchHelperAdapter helperAdapter) {
        mHelperAdapter = helperAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN; //允许上下滑动
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT; //只允许从右向左滑
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * 当用户拖动一个Item进行上下移动的时候 从旧的位置到新的位置 会调用该方法
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //原来的位置
        int fromPosition = viewHolder.getAdapterPosition();
        //移动到的位置
        int toPosition = target.getAdapterPosition();
        if (mHelperAdapter != null){
            mHelperAdapter.onItemMove(fromPosition, toPosition);
        }
        return true;
    }

    /**
     * 该方法返回true时，表示支持长按拖动，即长按ItemView后才可以拖动，我们遇到的场景一般也是这样的。默认是返回true。
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        mHelperAdapter.onItemSwiped(position);
    }

    /**
     * 当item被拖拽或侧滑时触发
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        //不管是拖拽或是侧滑，背景色都要变化
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(android.R.color.darker_gray));
    }

    /**
     *  当item的交互动画结束时触发
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(android.R.color.white));
        viewHolder.itemView.setAlpha(1);
        viewHolder.itemView.setScaleY(1);
    }

    /**
     * 左右滑动时item渐变
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        //我们只需要在左右滑动时，将透明度和高度的值变小（1 --> 0）
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float value = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(value);
            viewHolder.itemView.setScaleY(value);
        }
    }
}

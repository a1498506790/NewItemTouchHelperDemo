package com.mir.itemtouchhelperdemo;

/**
 * @author by lx
 * @github https://github.com/a1498506790
 * @data 2018-03-14
 * @desc 用来连接 ItemTouchHelperCallback 跟 Adapter
 */

public interface ItemTouchHelperAdapter {

    // 数据交换
    void onItemMove(int fromPosition, int toPosition);

    //删除Item
    void onItemSwiped(int position);

}

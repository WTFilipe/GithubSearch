package com.example.githubsearch.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.dp

class GridLayoutItemDecoration(
    private val spaceTop: Int,
    private val spaceBottom: Int,
    private val spaceStart: Int,
    private val spaceEnd: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (!recyclerViewHasGridLayoutManager(parent)) {
            super.getItemOffsets(outRect, view, parent, state)
        } else {
            val spanCount = (parent.layoutManager as GridLayoutManager).spanCount
            val position = parent.getChildAdapterPosition(view)
            val itemCount = parent.adapter?.itemCount ?: 0

            outRect.top = if (position < spanCount) spaceTop.dp else (spaceTop / 2).dp

            val quantityOfItemsAtLastRow = position % spanCount
            outRect.bottom = if (position > itemCount - quantityOfItemsAtLastRow) spaceBottom.dp else (spaceBottom / 2).dp

            outRect.left = if (position % spanCount == 0) spaceStart.dp else (spaceStart / 2).dp

            outRect.right = if ((position - 1) % spanCount == 0 ) spaceEnd.dp else (spaceEnd / 2).dp
        }


    }

    private fun recyclerViewHasGridLayoutManager(parent: RecyclerView) : Boolean {
        return parent.layoutManager?.let { it is GridLayoutManager } ?: false
    }
}
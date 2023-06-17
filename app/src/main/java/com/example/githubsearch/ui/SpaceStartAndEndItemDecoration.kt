package com.example.githubsearch.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.dp

class SpaceStartAndEndItemDecoration(
    private val spaceStart: Int,
    private val spaceEnd: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        when (position) {
            0 -> {
                outRect.left = spaceStart.dp
                outRect.right = (spaceEnd / 2).dp
            }
            itemCount - 1 -> {
                outRect.left = (spaceStart / 2).dp
                outRect.right = spaceEnd.dp
            }
            else -> {
                outRect.left = spaceStart.dp
                outRect.right = spaceEnd.dp
            }
        }

    }
}
package com.example.util.simpletimetracker.feature_statistics_detail.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.example.util.simpletimetracker.core.adapter.BaseRecyclerAdapter
import com.example.util.simpletimetracker.feature_views.extension.dpToPx
import com.example.util.simpletimetracker.feature_views.extension.spToPx
import com.example.util.simpletimetracker.feature_views.extension.updatePadding
import com.example.util.simpletimetracker.feature_statistics_detail.R
import com.example.util.simpletimetracker.feature_statistics_detail.adapter.createStatisticsDetailCardAdapterDelegate
import com.example.util.simpletimetracker.feature_statistics_detail.databinding.StatisticsDetailCardViewBinding
import com.example.util.simpletimetracker.feature_statistics_detail.viewData.StatisticsDetailCardViewData
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxItemDecoration
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class StatisticsDetailCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr
) {

    var listener: (() -> Unit)? = null
    var items: List<StatisticsDetailCardViewData> = emptyList()
        set(value) {
            typesAdapter.replace(value)
            field = value
        }

    private val binding: StatisticsDetailCardViewBinding = StatisticsDetailCardViewBinding
        .inflate(LayoutInflater.from(context), this, true)

    private var itemsCount: Int
    private var titleTextSize: Int = 16.spToPx()
    private val typesAdapter: BaseRecyclerAdapter by lazy {
        BaseRecyclerAdapter(
            createStatisticsDetailCardAdapterDelegate(titleTextSize, ::onItemClick)
        )
    }

    init {
        updatePadding(bottom = 2.dpToPx())
        clipToPadding = false

        var dividerDrawable = ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, context.theme)
        runCatching {
            context.theme.obtainStyledAttributes(intArrayOf(R.attr.appDivider)).run {
                dividerDrawable = getDrawable(0)
                recycle()
            }
        }
        val itemDecoration = FlexboxItemDecoration(context).apply {
            setDrawable(dividerDrawable)
        }

        context.obtainStyledAttributes(
            attrs,
            R.styleable.StatisticsDetailCardView,
            defStyleAttr,
            0
        ).run {
            itemsCount =
                getInt(R.styleable.StatisticsDetailCardView_itemCount, DEFAULT_ITEM_COUNT)
            titleTextSize =
                getDimensionPixelSize(R.styleable.StatisticsDetailCardView_itemTitleTextSize, 16.spToPx())
            recycle()
        }

        binding.rvStatisticsDetailCard.apply {
            layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.CENTER
                flexWrap = FlexWrap.NOWRAP
                addItemDecoration(itemDecoration)
            }
            adapter = typesAdapter
        }

        if (isInEditMode) {
            (1..itemsCount)
                .map { StatisticsDetailCardViewData("$DEFAULT_TITLE$it", "$DEFAULT_SUBTITLE$it") }
                .let { items = it }
        }
    }

    private fun onItemClick() {
        listener?.invoke()
    }

    companion object {
        private const val DEFAULT_ITEM_COUNT = 2
        private const val DEFAULT_TITLE = "Title"
        private const val DEFAULT_SUBTITLE = "Subtitle"
    }
}
package com.example.util.simpletimetracker.feature_notification.goalTime.manager

import com.example.util.simpletimetracker.domain.model.GoalTimeType
import com.example.util.simpletimetracker.feature_views.viewData.RecordTypeIcon

data class NotificationGoalTimeParams(
    val typeId: Long,
    val goalTimeType: GoalTimeType,
    val icon: RecordTypeIcon,
    val color: Int,
    val text: String,
    val description: String
)
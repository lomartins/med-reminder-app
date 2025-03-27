package dev.luisamartins.medreminder.domain.model

import kotlinx.datetime.LocalDateTime

data class UseRegister(
    val id: Long = -1,
    val scheduleId: Long,
    val date: LocalDateTime,
    val usageTimeOffsetInMinutes: Int
)

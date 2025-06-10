package com.tetron.domain_models

data class TaskWithoutId(
    val title: String,
    val description: String,
    val dateMill: Long,
    val status: Boolean,
    val tagId: Long? = null
)
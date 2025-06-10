package com.tetron.domain_models

data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val dateMill: Long,
    val status: Boolean,
    val tagId: Long? = null,
    val tagName: String? = null
)

package com.tetron.domain_models

data class TaskShort(
    val title: String,
    val description: String,
    val dateMill: Long,
    val status: Boolean = false,
) {

    fun mapToFullTaskWithoutId(idTag: Long?) : TaskWithoutId  =
        TaskWithoutId(
            title,
            description,
            dateMill,
            status,
            idTag
        )
}

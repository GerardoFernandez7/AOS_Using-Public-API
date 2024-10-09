// CategoryEntity.kt
package com.gerardo_fdez.usingpublicapi

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        idCategory = this.idCategory,
        strCategory = this.strCategory,
        strCategoryThumb = this.strCategoryThumb,
        strCategoryDescription = this.strCategoryDescription
    )
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        idCategory = this.idCategory,
        strCategory = this.strCategory,
        strCategoryThumb = this.strCategoryThumb,
        strCategoryDescription = this.strCategoryDescription
    )
}
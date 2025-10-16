package com.example.testpartnerkin.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsData(
    val about: String,
    val categories: List<Category>,
    val city: String,
    @SerialName("city_id")
    val cityId: Int,
    val country: String,
    @SerialName("country_id")
    val countryId: Int,
    @SerialName("custom_date")
    val customDate: String?,
    @SerialName("end_date")
    val endDate: String,
    val format: String,
    val id: Int,
    val image: Image,
    val name: String,
    val oneday: Int,
    val rating: String?,
    @SerialName("register_url")
    val registerUrl: String,
    @SerialName("start_date")
    val startDate: String,
    val status: String,
    @SerialName("status_title")
    val statusTitle: String,
    val type: Type,
    @SerialName("type_id")
    val typeId: Int,
    val url: String
)
package com.androiddevs.twistcode.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "carts"
)

data class ProductResponseItem(
    @PrimaryKey
    val id_cart: Int? = null,
    val id: String?,
    val added_date: String?,
    val added_date_str: String?,
    val added_user_id: String?,
    val added_user_name: String?,
    val address: String?,
    val appear_duration: String?,
    val brand: String?,
    val business_mode: String?,
    val cat_id: String?,
    val category: Category?,
    val condition_of_item: ConditionOfItem?,
    val condition_of_item_id: String?,
    val deal_option: DealOption?,
    val deal_option_id: String?,
    val deal_option_remark: String?,
    val default_photo: DefaultPhotoX?,
    val description: String?,
    val favourite_count: String?,
    //val get_address: GetAddress?,
    //val get_delivery: String?,
    val highlight_info: String?,
    val is_available: String?,
    val is_favourited: String?,
    val is_food: String?,
    val is_free: String?,
    val is_halal: String?,
    val is_owner: String?,
    val is_paid: String?,
    val is_pre_order: String?,
    val is_sold_out: String?,
    val item_currency: ItemCurrency?,
    val item_currency_id: String?,
    val item_location: ItemLocation?,
    val item_location_id: String?,
    val item_price_type: ItemPriceType?,
    val item_price_type_id: String?,
    val item_type: ItemType?,
    val item_type_id: String?,
    val lat: String?,
    val lng: String?,
    val location_id: String?,
    val location_name: String?,
    val location_type: String?,
    val paid_status: String?,
    val photo_count: String?,
    val pickup_time: String?,
    val po_delivery: String?,
    val po_end: String?,
    val po_slot: String?,
    val po_start: String?,
    val price: String?,
    val status: String?,
    val stock: String?,
    val sub_cat_id: String?,
    val sub_category: SubCategory?,
    val title: String?,
    val touch_count: String?,
    val updated_date: String?,
    val updated_flag: String?,
    val updated_user_id: String?,
    val user: User?,
    val weight: String?
) : Serializable
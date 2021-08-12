package com.androiddevs.twistcode.db

import androidx.room.TypeConverter
import com.androiddevs.twistcode.model.*

class Converters {

    //photo
    @TypeConverter
    fun fromPhoto(photo: DefaultPhotoX): String? {
        return photo.img_path
    }

    @TypeConverter
    fun toPhoto(img_path: String): DefaultPhotoX {
        return DefaultPhotoX(img_path,img_path,img_path,img_path,img_path,img_path,img_path)
    }

    //category
    @TypeConverter
    fun fromCategory(category: Category): String? {
        return category.cat_name
    }

    @TypeConverter
    fun toCategory(cat_name: String): Category {
        return Category(cat_name,cat_name,cat_name,cat_name,DefaultIcon(cat_name, cat_name,cat_name,cat_name,cat_name,cat_name,cat_name),DefaultPhotoX(cat_name,cat_name,cat_name,cat_name,cat_name,cat_name,cat_name),cat_name,cat_name)
    }

    //condition
    @TypeConverter
    fun fromCondition(item: ConditionOfItem): String? {
        return item.name
    }

    @TypeConverter
    fun toCondition(name: String): ConditionOfItem {
        return ConditionOfItem(name,name,name,name)
    }

    //DealOption
    @TypeConverter
    fun fromDealOption(deal: DealOption): String? {
        return deal.name
    }

    @TypeConverter
    fun toDealOption(name: String): DealOption {
        return DealOption(name,name,name,name)
    }

    //GetAddress
    @TypeConverter
    fun fromAddress(address: GetAddress): String? {
        return address.full_address
    }

    @TypeConverter
    fun toAddress(full_address: String): GetAddress {
        return GetAddress(full_address, full_address, full_address, full_address, full_address, full_address, full_address, full_address, full_address, full_address, full_address, full_address, full_address, full_address, full_address, full_address, full_address)
    }

    //ItemCurrency
    @TypeConverter
    fun fromItemCurrency(currency: ItemCurrency): String? {
        return currency.currency_short_form
    }

    @TypeConverter
    fun toItemCurrency(currency_short_form: String): ItemCurrency {
        return ItemCurrency(currency_short_form,currency_short_form,currency_short_form,currency_short_form,currency_short_form,currency_short_form)
    }

    //ItemLocation
    @TypeConverter
    fun fromItemLocation(location: ItemLocation): String? {
        return location.name
    }

    @TypeConverter
    fun toItemLocation(name: String): ItemLocation {
        return ItemLocation(name,name,name,name,name,name)
    }

    //ItemPriceType
    @TypeConverter
    fun fromItemPriceType(price: ItemPriceType): String? {
        return price.name
    }

    @TypeConverter
    fun toItemPriceType(name: String): ItemPriceType {
        return ItemPriceType(name,name,name,name,name)
    }

    //ItemType
    @TypeConverter
    fun fromItemType(type: ItemType): String? {
        return type.name
    }

    @TypeConverter
    fun toItemType(name: String): ItemType {
        return ItemType(name,name,name,name,name)
    }

    //SubCategory
    @TypeConverter
    fun fromSubCategory(subcategory: SubCategory): String? {
        return subcategory.name
    }

    @TypeConverter
    fun toSubCategory(name: String): SubCategory {
        return SubCategory(name,name,name, DefaultPhoto(name,name,name,name,name,name,name),name,name,name,name,name,name)
    }

    //User
    @TypeConverter
    fun fromUser(user: User): String? {
        return user.user_name
    }

    @TypeConverter
    fun toUser(user_name: String): User {
        return User(user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,RatingDetails(user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name),user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name,user_name)
    }
}
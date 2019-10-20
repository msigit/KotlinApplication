package com.mcc.kotlinapplication.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Sahidul Islam on 9/26/2019.
 */

@Entity
data class FavouritePostModel(

    @ColumnInfo(name = "id")
    var postId: Int,

    @ColumnInfo(name = "title")
    var postTitle: String,

    @ColumnInfo(name = "category")
    var postCategory: String,

    @ColumnInfo(name = "image")
    var postImage: String,

    @ColumnInfo(name = "date")
    var postDate: String

) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var autoId: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
        autoId = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(postId)
        parcel.writeString(postTitle)
        parcel.writeString(postCategory)
        parcel.writeString(postImage)
        parcel.writeString(postDate)
        parcel.writeInt(autoId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FavouritePostModel> {
        override fun createFromParcel(parcel: Parcel): FavouritePostModel {
            return FavouritePostModel(parcel)
        }

        override fun newArray(size: Int): Array<FavouritePostModel?> {
            return arrayOfNulls(size)
        }
    }
}
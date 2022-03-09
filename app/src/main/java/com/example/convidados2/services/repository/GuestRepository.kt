package com.example.convidados2.services.repository

import android.content.Context
import com.example.convidados2.services.model.GuestModel

class GuestRepository (context: Context) {

    private val mDataBase = GuestDataBase.getDataBase(context).guestDAO()

    fun get(id: Int) : GuestModel {
        return mDataBase.get(id)
    }

    fun update(guest: GuestModel) : Boolean {
        return mDataBase.update(guest) > 0
    }

    fun save(guest: GuestModel) : Boolean {
        return mDataBase.save(guest) > 0
    }

    fun delete(guest: GuestModel) {
        mDataBase.delete(guest)
    }

    fun getAll(): List<GuestModel> {
        return mDataBase.getInvited()
    }

    fun getPresents(): List<GuestModel> {
        return mDataBase.getPresent()
    }

    fun getAbsents(): List<GuestModel> {
        return mDataBase.getAbsent()
    }
}
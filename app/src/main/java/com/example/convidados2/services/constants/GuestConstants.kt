package com.example.convidados2.services.constants

class GuestConstants private constructor() {

    companion object {
        const val GUESTID = "guestID"
    }

    object FILTER {
        const val EMPTY = 0
        const val PRESENT = 1
        const val ABSENT = 2
    }
}
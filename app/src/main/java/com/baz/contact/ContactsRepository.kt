package com.baz.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ContactsRepository {

    private val contactsLiveData = MutableLiveData<List<Contact>>()

    fun fetchContacts(): LiveData<List<Contact>> {
        return contactsLiveData
    }
}
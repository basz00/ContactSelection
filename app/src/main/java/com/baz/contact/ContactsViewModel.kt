package com.baz.contact

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import java.util.concurrent.atomic.AtomicInteger

internal class ContactsViewModel(private val repository: ContactsRepository) : AndroidViewModel() {

    private val contactLoadTrigger = AtomicInteger()
    private val contactsInputLiveData = MutableLiveData<Int>()

    internal val contactsSubAccounts by lazy(LazyThreadSafetyMode.NONE) {
        Transformations.switchMap(contactsInputLiveData) {
            repository.fetchContacts()
        }
    }
    internal val selectedItems = MutableLiveData<List<Contact>>()

    private fun fetchContacts() {
        contactsInputLiveData.value = contactLoadTrigger.getAndIncrement()
    }

    fun applySelection(contact: Contact) {
        val currentItems = selectedItems.value ?: emptyList()
        selectedItems.value = if (currentItems.contains(contact)) currentItems - contact
        else currentItems + contact
    }

    fun applySelection(contacts: List<Contact>, selections: List<String>) {
        val currentSelection = selectedItems.value ?: emptyList()
        val newSelection = currentSelection + contacts.filter { selections.contains(it.id) }
        selectedItems.value = newSelection.distinctBy { it.number }
    }

    fun selectedContacts(): List<Contact> {
        return selectedItems.value ?: emptyList()
    }
}
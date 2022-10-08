package com.konterraweb.chokee.models

import Contact
import android.app.Application
import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.konterraweb.chokee.MyApp

class ContactsViewModel(application: Application) : AndroidViewModel(application)
{
    private val contacts = MutableLiveData<ArrayList<Contact>>();

    fun readContacts() {
        val contentResolver = getApplication<MyApp>().contentResolver
        val allContacts = ArrayList<Contact>()
        val contactsPhones = ArrayList<String>()
        val cursor: Cursor? = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        cursor?.let {
            if(it.moveToFirst()) {
                do {
                    val idColumnIndex: Int = cursor.getColumnIndex(ContactsContract.Contacts._ID)
                    val hasPhoneNumberColumnIndex: Int = cursor.getColumnIndex(ContactsContract.Contacts._ID)
                    val userNameColumnIndex: Int = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                    val imageColumnIndex: Int = cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)
                    val contactId: String  = cursor.getString(idColumnIndex)
                    val hasPhoneNumber: Int  = cursor.getInt(hasPhoneNumberColumnIndex)
                    val userName: String  = cursor.getString(userNameColumnIndex)
                    var image: String? = null
                    if(imageColumnIndex > -1) {
                        image = cursor.getString(imageColumnIndex)
                    }
                    if(hasPhoneNumber > 0) {
                        val phones: Cursor? = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+contactId, null, "contact_id ASC LIMIT 1")
                        phones?.let {
                            if(phones.moveToFirst()) {
                                val phoneNumberColumnIndex: Int = phones.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER)
                                var phoneNumber: String = phones
                                    .getString(phoneNumberColumnIndex)
                                allContacts.add(Contact(phoneNumber, userName, image))
                                phoneNumber = phoneNumber.replace(Regex("[^0-9]"), "")
                                if(phoneNumber.length == 11) {
                                    contactsPhones.add(phoneNumber.replaceFirst("8", "7"))
                                }
                            }
                        }
                    }
                } while(it.moveToNext())
            }
        }

        contacts.value = allContacts
    }

    fun getContacts(): LiveData<ArrayList<Contact>> {
        return contacts
    }
}
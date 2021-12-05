package com.laupdev.projectx.presentation.ui.hotel.contacts

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.laupdev.projectx.R
import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.databinding.ItemHotelContactBinding
import com.laupdev.projectx.databinding.ViewHotelDetailsContactsBinding

class HotelDetailsContactsView(context: Context) : ConstraintLayout(context) {

    constructor(context: Context, contactInfo: LiveData<ContactInfo>) : this(context) {
        this.contactInfo = contactInfo
        this.contactInfo.observeForever(observer)
    }

    private val binding = ViewHotelDetailsContactsBinding.inflate(LayoutInflater.from(context), this, true)
    private val observer = Observer<ContactInfo> {
        it?.let {
            binding.allContactsGroup.removeAllViews()
            binding.addressText.text = it.address
            it.contacts.split("|").forEach { contact ->
                val hotelContactBinding = ItemHotelContactBinding.inflate(LayoutInflater.from(context), binding.allContactsGroup, false)
                contact.split(":").let { splitContact ->
                    hotelContactBinding.contactText.text = context.getString(R.string.contact_format, splitContact[0], splitContact[1])
                }
                binding.allContactsGroup.addView(hotelContactBinding.root)
            }
            binding.orderBtn.setOnClickListener {
                Snackbar.make(this, R.string.successful_order, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private lateinit var contactInfo: LiveData<ContactInfo>


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        contactInfo.removeObserver(observer)
    }

}
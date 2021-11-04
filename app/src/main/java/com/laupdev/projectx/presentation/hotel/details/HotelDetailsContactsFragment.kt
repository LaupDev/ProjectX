package com.laupdev.projectx.presentation.hotel.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laupdev.projectx.R
import com.laupdev.projectx.databinding.FragmentHotelDetailsContactsBinding
import com.laupdev.projectx.presentation.hotel.HotelsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

private const val ARG_HOTEL_ID = "hotel_id"

class HotelDetailsContactsFragment : Fragment() {

    private var hotelId: Int? = null

    private val viewModel by sharedViewModel<HotelsViewModel>()

    private var _binding: FragmentHotelDetailsContactsBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hotelId = it.getInt(ARG_HOTEL_ID)
        }
        if (hotelId != null) {
            viewModel.getContactInfoByHotelId(hotelId!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelDetailsContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
        @JvmStatic
        fun newInstance(hotelId: Int) =
            HotelDetailsContactsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_HOTEL_ID, hotelId)
                }
            }
    }
}
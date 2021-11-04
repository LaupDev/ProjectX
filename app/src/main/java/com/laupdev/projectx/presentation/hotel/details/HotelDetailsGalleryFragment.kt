package com.laupdev.projectx.presentation.hotel.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laupdev.projectx.databinding.FragmentHotelDetailsGalleryBinding
import com.laupdev.projectx.presentation.hotel.HotelsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

private const val ARG_HOTEL_ID = "hotel_id"

class HotelDetailsGalleryFragment : Fragment() {

    private var hotelId: Int? = null

    private val viewModel by sharedViewModel<HotelsViewModel>()

    private var _binding: FragmentHotelDetailsGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hotelId = it.getInt(ARG_HOTEL_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelDetailsGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (hotelId != null) {
            viewModel.getHotelsGalleryAndLoadToAdapter(hotelId = hotelId!!)
        }
        binding.galleryViewPager.adapter = viewModel.pictureAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(hotelId: Int) =
            HotelDetailsGalleryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_HOTEL_ID, hotelId)
                }
            }
    }
}
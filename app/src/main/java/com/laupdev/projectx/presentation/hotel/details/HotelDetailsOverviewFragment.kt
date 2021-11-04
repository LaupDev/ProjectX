package com.laupdev.projectx.presentation.hotel.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laupdev.projectx.databinding.FragmentHotelsDetailsOverviewBinding
import com.laupdev.projectx.presentation.hotel.HotelsViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.File

private const val HOTEL_ID_ARG = "hotel_id"

class HotelDetailsOverviewFragment : Fragment() {

    private var hotelId: Int? = null

    private val viewModel by sharedViewModel<HotelsViewModel>()

    private var _binding: FragmentHotelsDetailsOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hotelId = it.getInt(HOTEL_ID_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelsDetailsOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (hotelId != null) {
            viewModel.getHotelById(hotelId!!)
        }
        viewModel.currentHotel.observe(viewLifecycleOwner) {
            it.let {
                Picasso.get().load(File(it.imagePath)).into(binding.mainPhotoImage)
                binding.hotelNameText.text = it.name
                binding.hotelDescText.text = it.description
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(hotelId: Int) =
            HotelDetailsOverviewFragment().apply {
                arguments = Bundle().apply {
                    putInt(HOTEL_ID_ARG, hotelId)
                }
            }
    }
}
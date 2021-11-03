package com.laupdev.projectx.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laupdev.projectx.databinding.FragmentHotelDetailsBinding
import com.laupdev.projectx.domain.viewmodel.HotelsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val HOTEL_ID_PARAM = "hotel_id"

class HotelDetailsFragment : Fragment() {

    private var hotelId = 0

    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HotelsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hotelId = it.getInt(HOTEL_ID_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHotelWithAllInfo(hotelId)

        viewModel.hotelWithAllInfo.observe(viewLifecycleOwner) {
            it?.let {
                binding.text.text = it.name
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.laupdev.projectx.presentation.ui.hotel.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.laupdev.projectx.databinding.FragmentHotelDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.coroutines.coroutineContext

private const val ARG_HOTEL_ID = "hotel_id"

class HotelDetailsFragment : Fragment() {

    private var hotelId: Int? = null

    val viewModel by viewModel<HotelDetailsViewModel>()

    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding get() = _binding!!

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hotelId = it.getInt(ARG_HOTEL_ID)
        }
        viewModel.hotelId = hotelId!!
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

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    binding.pageView.addView(viewModel.getPage(it.position, requireContext()))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                binding.pageView.removeAllViews()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        binding.tabs.selectTab(binding.tabs.getTabAt(0))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
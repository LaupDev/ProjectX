package com.laupdev.projectx.presentation.ui.hotel.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.laupdev.projectx.R
import com.laupdev.projectx.databinding.FragmentHotelDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_HOTEL_ID = "hotel_id"

class HotelDetailsFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var hotelId: Int? = null

    val viewModel by viewModel<HotelDetailsViewModel>()

    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hotelId = it.getInt(ARG_HOTEL_ID)
        }
        viewModel.hotelId = hotelId!!
        auth = Firebase.auth
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

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.log_out -> {
                    viewModel.signOut()
                    auth.signOut()
                    findNavController().navigate(R.id.go_to_auth)
                    true
                }
                else -> false
            }
        }

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
                tab?.let {
                    if (binding.pageView.isEmpty() && it.position == TabPosition.OVERVIEW.position) {
                        binding.pageView.addView(viewModel.getPage(it.position, requireContext()))
                    }
                }
            }

        })
        binding.tabs.getTabAt(TabPosition.OVERVIEW.position)?.select()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
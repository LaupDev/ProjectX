package com.laupdev.projectx.presentation.ui.hotels_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.laupdev.projectx.R
import com.laupdev.projectx.databinding.FragmentHotelsListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HotelsListFragment : Fragment() {

    private val viewModel by viewModel<HotelsListViewModel>()

    private var _binding: FragmentHotelsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        recyclerView = binding.hotelsRecyclerView
        recyclerView.adapter = viewModel.hotelAdapter
        viewModel.getHotelsPagingAndLoadToAdapter()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    if (viewModel.isLoadingAllowed) {
                        Timber.e("LOAD DATA")
                        viewModel.getHotelsPagingAndLoadToAdapter()
                    }
                }
            }
        })

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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
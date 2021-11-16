package com.laupdev.projectx.presentation.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.laupdev.projectx.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {

    private val viewModel by viewModel<StartViewModel>()

    override fun onStart() {
        super.onStart()
        viewModel.isUserLoggedIn.observe(viewLifecycleOwner) {
            if (it) {
                val action = StartFragmentDirections.goToHotelsListFragment()
                findNavController().navigate(action)
            } else {
                val action = StartFragmentDirections.goToAuth()
                findNavController().navigate(action)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
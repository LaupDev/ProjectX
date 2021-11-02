package com.laupdev.projectx.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.laupdev.projectx.R
import com.laupdev.projectx.databinding.FragmentUserAuthBinding
import com.laupdev.projectx.model.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class UserAuthFragment : Fragment() {

    private var _binding: FragmentUserAuthBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logInBtn.setOnClickListener {
            viewModel.authenticateUser(binding.loginInputEditText.text.toString(), binding.passwordInputEditText.text.toString())
        }

        viewModel.isLoggedIn.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    val action = UserAuthFragmentDirections.goToHotelsList()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), R.string.wrong_data, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
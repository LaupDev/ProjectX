package com.laupdev.projectx.presentation.ui.user

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.laupdev.projectx.R
import com.laupdev.projectx.databinding.FragmentUserAuthBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserAuthFragment : Fragment() {

    private var _binding: FragmentUserAuthBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<UserViewModel>()

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        viewModel.isLoggedIn.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.go_to_HotelsListFragment)
            } else {
                Snackbar.make(
                    view,
                    R.string.authentication_failed,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        binding.logInBtn.setOnClickListener {
            if (isFieldsValid()) {
                val email = binding.emailInputEditText.text.toString()
                val password = binding.passwordInputEditText.text.toString()
                viewModel.signIn(email, password)
                hideKeyboard()
            }
        }

        binding.registrationBtn.setOnClickListener {
            val action = UserAuthFragmentDirections.goToRegistration()
            findNavController().navigate(action)
        }
    }

    private fun isFieldsValid(): Boolean {
        var isValid = true
        if (!validateField(binding.emailInput, binding.emailInputEditText)) {
            isValid = false
        }
        if (!validateField(binding.passwordInput, binding.passwordInputEditText)) {
            isValid = false
        }
        return isValid
    }

    private fun validateField(inputLayout: TextInputLayout, editText: TextInputEditText): Boolean {
        return if (editText.text.toString().isEmpty()) {
            inputLayout.error = "Required"
            inputLayout.isErrorEnabled = true
            false
        } else {
            inputLayout.isErrorEnabled = false
            true
        }
    }

    private fun hideKeyboard() {
        (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
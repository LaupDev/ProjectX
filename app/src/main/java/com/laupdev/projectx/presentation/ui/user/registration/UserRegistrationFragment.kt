package com.laupdev.projectx.presentation.ui.user.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.laupdev.projectx.R
import com.laupdev.projectx.databinding.FragmentUserRegistrationBinding
import com.laupdev.projectx.presentation.ui.user.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class UserRegistrationFragment : Fragment() {

    private val viewModel by viewModel<UserViewModel>()

    private var _binding: FragmentUserRegistrationBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpBtn.setOnClickListener {
            if (isFieldsValid()) {
                val email = binding.emailInputEditText.text.toString()
                val password = binding.passwordInputEditText.text.toString()
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            viewModel.authenticateUser(email, password)
                            val action = UserRegistrationFragmentDirections.goToHotelsListFragment()
                            findNavController().navigate(action)
                        } else {
                            Snackbar.make(view, R.string.registration_failed, Snackbar.LENGTH_SHORT).show()
                            Timber.d(task.exception?.message)
                        }
                    }
            }
        }
    }

    private fun isFieldsValid(): Boolean {
        var isValid = true
        if (!validateField(binding.emailInput, binding.emailInputEditText)) {
            isValid = false
        } else {
            if (isEmail(binding.emailInputEditText.text.toString())) {
                binding.emailInput.error = "Not valid email"
                binding.emailInput.isErrorEnabled = true
            } else {
                binding.emailInput.isErrorEnabled = false
            }
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

    private fun isEmail(userInput: String): Boolean {
        return userInput.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
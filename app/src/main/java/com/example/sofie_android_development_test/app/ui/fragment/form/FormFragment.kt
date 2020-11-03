package com.example.sofie_android_development_test.app.ui.fragment.form

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.sofie_android_development_test.R

abstract class FormFragment : Fragment() {

    private val formViewModel by viewModel<FormViewModel>()
    protected abstract val toolbarTitle: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, R.layout.fragment_form, container, false
        )
        binding.lifecycleOwner = this
        val view = binding.root
        binding.setVariable(com.example.sofie_android_development_test.BR.viewModel, formViewModel)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = toolbarTitle

        formViewModel.formStatus.observe(viewLifecycleOwner, Observer {})
        formViewModel.email.observe(viewLifecycleOwner, Observer {})
        formViewModel.taskName.observe(viewLifecycleOwner, Observer {})
        formViewModel.taskDescription.observe(viewLifecycleOwner, Observer {})
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_clear_white_24dp)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_done -> validateForm()
            android.R.id.home -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    protected abstract fun sendTaskSuccess(title: String, email: String, description: String)

    private fun validateForm(){
        when(formViewModel.formStatus.value){
            true -> {
                val title = formViewModel.taskName.value!!
                val email = formViewModel.email.value!!
                val description = formViewModel.taskDescription.value!!
                sendTaskSuccess(title, email, description)
            }
            else -> alertBox(
                resources.getString(R.string.alert_title_validation_error),
                resources.getString(R.string.alert_message_validation_error)
            )
        }
    }

    private fun alertBox(title: String, message: String){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(resources.getString(R.string.alert_positive_button_text)){ dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }
}

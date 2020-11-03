package com.example.sofie_android_development_test.app.ui.fragment.form.add

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.sofie_android_development_test.R
import com.example.sofie_android_development_test.app.ui.fragment.form.FormFragment
import com.example.sofie_android_development_test.resources.remote.api.request.TaskRequest
import kotlinx.android.synthetic.main.fragment_form.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFragment : FormFragment() {
    private val addViewModel by viewModel<AddViewModel>()
    override val toolbarTitle: String by lazy { resources.getString(R.string.add_task_title) }

    override fun onViewReady() {
        addViewModel.createdTask.observe(viewLifecycleOwner, Observer {
            Toast.makeText(
                requireContext(),
                resources.getString(R.string.task_success),
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_AddTaskFragment_to_ListFragment)
        })

        addViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            if(isLoading) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        })

        addViewModel.error.observe(viewLifecycleOwner, Observer {
            it.message?.let {message ->
                alertBox(resources.getString(R.string.alert_title_validation_error), message)
            }
        })
    }

    override fun sendTaskSuccess(title: String, email: String, description: String) {
        val taskRequest = TaskRequest(email, title, description)
        addViewModel.create(taskRequest)
    }
}

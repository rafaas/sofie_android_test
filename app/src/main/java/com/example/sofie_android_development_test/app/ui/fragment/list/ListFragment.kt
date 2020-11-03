package com.example.sofie_android_development_test.app.ui.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.sofie_android_development_test.R
import com.example.sofie_android_development_test.app.ui.fragment.list.adapter.ListAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private val lisViewModel by viewModel<ListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = resources.getString(R.string.app_name)
        fab.setOnClickListener { _ ->
            findNavController().navigate(R.id.action_ListFragment_to_AddTaskFragment)
        }

        lisViewModel.tasks.observe(viewLifecycleOwner, Observer {
            recycler_view.adapter = ListAdapter(it)
        })

        lisViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            if(isLoading) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        })

        lisViewModel.error.observe(viewLifecycleOwner, Observer {
            it.message?.let {message ->
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        })

        lisViewModel.list()
    }

    override fun onResume() {
        super.onResume()
        resetActionBar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetActionBar()
    }

    private fun resetActionBar(){
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.let {
            it.setDisplayHomeAsUpEnabled(false)
            it.setDisplayShowHomeEnabled(false)
        }
    }
}

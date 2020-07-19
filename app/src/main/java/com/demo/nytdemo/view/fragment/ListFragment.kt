package com.demo.nytdemo.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.demo.nytdemo.R
import com.demo.nytdemo.utils.snack
import com.demo.nytdemo.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ListFragment : Fragment() {

    /** Koin is used as Dependency Injector**/
    private val viewModel by sharedViewModel<MainViewModel>(from = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getApiData()
        observeFailureMessage()
        setupRecyclerView()
        observeClickEvent()

    }

    private fun observeFailureMessage() {
        if (viewModel.getFailureMessage().hasActiveObservers()) {
            viewModel.getFailureMessage().removeObservers(viewLifecycleOwner)
        }
        viewModel.getFailureMessage().observe(viewLifecycleOwner, Observer {
            container.snack(it,Snackbar.LENGTH_LONG)
        })
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = viewModel.adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun observeClickEvent() {
        if (viewModel.userClickedEvent.hasActiveObservers()) {
            viewModel.userClickedEvent.removeObservers(viewLifecycleOwner)
        }
        viewModel.userClickedEvent.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                gotoDetailPage()
            }
        })
    }

    private fun gotoDetailPage() {
        val gotoDetailPage = ListFragmentDirections.actionListFragmentToDetailFragment()
        findNavController().navigate(gotoDetailPage)
    }


}
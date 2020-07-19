package com.demo.nytdemo.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.demo.nytdemo.R
import com.demo.nytdemo.model.Result
import com.demo.nytdemo.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : Fragment() {

    /** Koin is used as Dependency Injector**/
    private val viewModel by sharedViewModel<MainViewModel>(from = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDetail()
        topAppBar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showDetail() {
        viewModel.getUserClickedData().observe(viewLifecycleOwner, Observer {
            setUpView(it)
        })
    }

    private fun setUpView(it: Result) {
        if (!it.media.isNullOrEmpty()) {
            val ordered = it.media[0].media.sortedByDescending { it.height }
            setImage(ordered[0].url)
        }
        title.text = it.title
        abstractData.text = it.abstract
        source.text=it.source
        val url=it.url
        MoreDetailLayout.setOnClickListener {
            openUrl(url)
        }
    }

    private fun setImage(url: String) {
        Glide.with(requireActivity())
            .load(url)
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                )
            )
            .into(img)
    }
    private fun openUrl(url:String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        val title = "Select a browser"
        val chooser = Intent.createChooser(intent, title)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(chooser)
        }
    }
}
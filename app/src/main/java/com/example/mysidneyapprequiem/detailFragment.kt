package com.example.mysidneyapprequiem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.mysidneyapprequiem.databinding.FragmentDetailBinding
import com.example.mysidneyapprequiem.databinding.FragmentListPoiBinding
import com.squareup.picasso.Picasso

class detailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val model: PoiViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.selectedPoi.observe(viewLifecycleOwner, {
            binding.tvNameDetail.text = it.name
            binding.tvDescDetail.text = it.description
            Picasso.get().load(it.imageUrl).into(binding.imageViewDetail)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = detailFragment().apply {

        }
    }
}
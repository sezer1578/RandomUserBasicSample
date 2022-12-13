package com.ozaltun.randomuser.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.ozaltun.randomuser.R
import com.ozaltun.randomuser.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val bundle: DetailFragmentArgs by navArgs()
        val getData = bundle.data


        val imageLink = getData?.picture?.large
        binding.imageViewDetail.load(imageLink) {
            crossfade(true)
            crossfade(1000)
        }
        binding.apply {
            textViewDetail.text = getData?.name?.first
            tvMailDetail.text = getData?.email
            tvPhoneDetail.text = getData?.phone
        }

        return binding.root
    }

}
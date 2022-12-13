package com.ozaltun.randomuser.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ozaltun.randomuser.R
import com.ozaltun.randomuser.data.model.UserModel
import com.ozaltun.randomuser.data.remote.ApiService
import com.ozaltun.randomuser.data.remote.ApiUtils
import com.ozaltun.randomuser.databinding.FragmentHomeBinding
import com.ozaltun.randomuser.ui.adapter.HomeFragmentAdapter
import com.ozaltun.randomuser.ui.viewmodel.HomeFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var adapter: HomeFragmentAdapter
    private lateinit var userList: List<UserModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.userList.observe(viewLifecycleOwner) {
            userList = it
            lifecycleScope.launch(Dispatchers.Main) {
                adapter = HomeFragmentAdapter(requireContext(), userList)
                binding.recyclerView.adapter = adapter
            }
        }
        viewModel.getUserList()
        return binding.root
    }

}
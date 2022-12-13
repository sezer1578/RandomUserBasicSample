package com.ozaltun.randomuser.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ozaltun.randomuser.data.model.UserModel
import com.ozaltun.randomuser.databinding.UserLayoutBinding
import com.ozaltun.randomuser.ui.view.HomeFragmentDirections

class HomeFragmentAdapter(var mContext: Context, var userList: List<UserModel>) :
    RecyclerView.Adapter<HomeFragmentAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(val binding: UserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            UserLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val data = userList[position]
        holder.binding.apply {
            holder.itemView.apply {
                tvName.text = "${data?.name?.first + "-" + data?.name?.last}"
                tvMail.text = "${data?.email}"
                tvPhone.text = "${data?.phone}"
                val imageLink = data?.picture?.thumbnail
                imageView.load(imageLink) {
                    crossfade(true)
                    crossfade(1000)
                }
                cardView.setOnClickListener {
                    val nav = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
                    Navigation.findNavController(it).navigate(nav)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
package com.example.locationsharingapp_dipti_ict_amad_l4_04_09

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.locationsharingapp_dipti_ict_amad_l4_04_09.databinding.ItemUserDiptiIctAmadL40409Binding


class UserAdapter_DIPTI_ICT_AMAD_L4_04_09(private var userDIPTIICTAMADL40409List: List<User_DIPTI_ICT_AMAD_L4_04_09>):RecyclerView.Adapter<UserAdapter_DIPTI_ICT_AMAD_L4_04_09.UserViewHolder>() {
    class UserViewHolder(private val binding: ItemUserDiptiIctAmadL40409Binding):RecyclerView.ViewHolder(binding.root){
        fun bind(userDIPTIICTAMADL40409:User_DIPTI_ICT_AMAD_L4_04_09){
            binding.apply {
                displayNameTxt.text = userDIPTIICTAMADL40409.displayName
                emailTxt.text = userDIPTIICTAMADL40409.email
                locationTxt.text = userDIPTIICTAMADL40409.location
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserDiptiIctAmadL40409Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return userDIPTIICTAMADL40409List.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userDIPTIICTAMADL40409List[position]

        holder.bind(user)

    }
    fun updateData(newList: List<User_DIPTI_ICT_AMAD_L4_04_09>) {
        userDIPTIICTAMADL40409List = newList
        notifyDataSetChanged()
    }
}
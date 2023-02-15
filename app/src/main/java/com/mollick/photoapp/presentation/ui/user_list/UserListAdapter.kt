package com.mollick.photoapp.presentation.ui.user_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mollick.photoapp.R
import com.mollick.photoapp.domain.model.User

/** Adapter to list down user details. */
class UserListAdapter (
    var userList: List<User>,
    private val listener: ItemClickListener
): RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = userList[position]
        holder.onBind(user)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(user.id)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun onBind(user: User) {
            view.findViewById<TextView>(R.id.name).text = user.name
            view.findViewById<TextView>(R.id.address).text = user.address.toString()
        }
    }

    interface ItemClickListener {
        fun onItemClicked(id: Int)
    }
}

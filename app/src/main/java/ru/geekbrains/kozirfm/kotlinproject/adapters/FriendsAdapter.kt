package ru.geekbrains.kozirfm.kotlinproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cell_friend.view.*
import ru.geekbrains.kozirfm.kotlinproject.R
import ru.geekbrains.kozirfm.kotlinproject.models.FriendModel

class FriendsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mFriendsList: ArrayList<FriendModel> = ArrayList()
    private var mSourceList: ArrayList<FriendModel> = ArrayList()

    fun setupFriendsList(friendsList: ArrayList<FriendModel>) {
        mSourceList.clear()
        mSourceList.addAll(friendsList)
        filter(query = "")
    }

    fun filter(query: String) {
        mFriendsList.clear()
        mSourceList.forEach {
            if (it.name.contains(query, ignoreCase = true) || it.surname.contains(
                    query,
                    ignoreCase = true
                )
            ) {
                mFriendsList.add(it)
            } else {
                it.mCity?.let { mCity ->
                    if (mCity.title.contains(query, ignoreCase = true)!!) {
                        mFriendsList.add(it)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_friend, parent, false)
        return FriendsViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendsViewHolder) {
            holder.bind(friendModel = mFriendsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mFriendsList.count()
    }


    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(friendModel: FriendModel) = with(itemView) {

            friendModel.avatar?.let {
                Glide.with(itemView.context)
                    .load(it)
                    .into(friendCivAvatar)
            }

            val fullName = "${friendModel.name} ${friendModel.surname}"
            friendTxtName.text = fullName

            friendModel.mCity?.title.let { friendTxtCity.text = it }

            if (friendModel.isOnline == 1) {
                friendImageOnline.visibility = View.VISIBLE
            } else {
                friendImageOnline.visibility = View.GONE
            }
        }
    }

}

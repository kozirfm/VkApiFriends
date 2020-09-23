package ru.geekbrains.kozirfm.kotlinproject.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_friends.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.geekbrains.kozirfm.kotlinproject.R
import ru.geekbrains.kozirfm.kotlinproject.adapters.FriendsAdapter
import ru.geekbrains.kozirfm.kotlinproject.models.FriendModel
import ru.geekbrains.kozirfm.kotlinproject.presenters.FriendsPresenter
import ru.geekbrains.kozirfm.kotlinproject.views.FriendsView

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    lateinit var mAdapter: FriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        friendsPresenter.loadFriends()

        initRecycler()

        txtFriendsSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mAdapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })


    }

    private fun initRecycler() {
        mAdapter = FriendsAdapter()
        recyclerFriends.adapter = mAdapter
        recyclerFriends.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerFriends.setHasFixedSize(true)
    }

    override fun showError(textResource: Int) {
        txtFriendsNoItems.text = getString(textResource)

    }

    override fun setupEmptyList() {
        recyclerFriends.visibility = View.GONE
        txtFriendsNoItems.visibility = View.VISIBLE
    }

    override fun setupFriendsList(friendsList: ArrayList<FriendModel>) {
        recyclerFriends.visibility = View.VISIBLE
        txtFriendsNoItems.visibility = View.GONE

        mAdapter.setupFriendsList(friendsList = friendsList)
    }

    override fun startLoading() {
        recyclerFriends.visibility = View.GONE
        txtFriendsNoItems.visibility = View.GONE
        cpvFriends.visibility = View.VISIBLE

    }

    override fun endLoading() {
        cpvFriends.visibility = View.GONE

    }
}
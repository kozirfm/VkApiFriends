package ru.geekbrains.kozirfm.kotlinproject.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.kozirfm.kotlinproject.R
import ru.geekbrains.kozirfm.kotlinproject.models.FriendModel
import ru.geekbrains.kozirfm.kotlinproject.providers.FriendsProvider
import ru.geekbrains.kozirfm.kotlinproject.providers.qwerty
import ru.geekbrains.kozirfm.kotlinproject.views.FriendsView

@InjectViewState
class FriendsPresenter : MvpPresenter<FriendsView>() {
    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(presenter = this).loadFriends()
        //testLoadFriends(hasFriends = true)
    }

    fun friendsLoaded(friendsList: ArrayList<FriendModel>) {
        viewState.endLoading()
        if (friendsList.count() == 0) {
            viewState.setupEmptyList()
            viewState.showError(textResource = R.string.friends_no_items)
        } else {
            viewState.setupFriendsList(friendsList = friendsList)
        }
    }

    fun showError(textResource: Int) {
        viewState.showError(textResource = textResource)
    }


}
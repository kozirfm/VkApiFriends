package ru.geekbrains.kozirfm.kotlinproject.presenters

import android.content.Intent
import android.os.Handler
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.kozirfm.kotlinproject.R
import ru.geekbrains.kozirfm.kotlinproject.views.LoginView

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    fun loginVk(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (data == null || !VK.onActivityResult(requestCode = requestCode,
                resultCode = resultCode,
                data = data,
                object : VKAuthCallback {
                    override fun onLogin(token: VKAccessToken) {
                        viewState.openFriends()
                    }

                    override fun onLoginFailed(errorCode: Int) {
                        viewState.showError(R.string.canceled)
                    }

                })
        ) {
            return false
        }
        return true
    }

}

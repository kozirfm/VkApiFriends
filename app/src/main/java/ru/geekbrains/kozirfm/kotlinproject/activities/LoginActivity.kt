package ru.geekbrains.kozirfm.kotlinproject.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiConfig
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.VKApiValidationHandler
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.activity_login.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.geekbrains.kozirfm.kotlinproject.R
import ru.geekbrains.kozirfm.kotlinproject.presenters.LoginPresenter
import ru.geekbrains.kozirfm.kotlinproject.views.LoginView

class LoginActivity : MvpAppCompatActivity(), LoginView {

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener {
            VK.login(this, arrayListOf(VKScope.FRIENDS))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (loginPresenter.loginVk(
                requestCode = requestCode,
                resultCode = resultCode,
                data = data
            )
        ) {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    override fun startLoading() {
        buttonLogin.visibility = View.GONE
        cpvLogin.visibility = View.VISIBLE
    }

    override fun endLoading() {
        buttonLogin.visibility = View.VISIBLE
        cpvLogin.visibility = View.GONE
    }

    override fun showError(textResource: Int) {
        Toast.makeText(applicationContext, getString(textResource), Toast.LENGTH_SHORT).show()
    }

    override fun openFriends() {
        startActivity(Intent(applicationContext, FriendsActivity::class.java))
    }

}

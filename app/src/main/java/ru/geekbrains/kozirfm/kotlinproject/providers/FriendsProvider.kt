package ru.geekbrains.kozirfm.kotlinproject.providers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.VKMethodCall
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject
import ru.geekbrains.kozirfm.kotlinproject.R
import ru.geekbrains.kozirfm.kotlinproject.models.FriendsList
import ru.geekbrains.kozirfm.kotlinproject.presenters.FriendsPresenter

class FriendsProvider(val presenter: FriendsPresenter) {

    fun loadFriends() {

        val request: VKRequest<JSONObject> = requestVK()
        request.addParam("fields", "city, photo_100")

        VK.execute(request, object : VKApiCallback<JSONObject> {
            override fun fail(error: Exception) {
                presenter.showError(R.string.no_response)
            }

            override fun success(result: JSONObject) {
                val response: JSONObject = result.getJSONObject("response")
                val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                val friendsList: FriendsList =
                    gson.fromJson(response.toString(), FriendsList::class.java)
                presenter.friendsLoaded(friendsList.friends)
            }

        })
    }

    private fun requestVK(): VKRequest<JSONObject> {
        return object : VKRequest<JSONObject>("friends.get") {

            override fun onExecute(manager: VKApiManager): JSONObject {
                val config = manager.config
                params["lang"] = "ru"
                params["device_id"] = config.deviceId.value
                params["v"] = config.version
                return manager.execute(
                    VKMethodCall.Builder()
                        .args(params)
                        .method(method)
                        .version(config.version)
                        .build(), this
                )
            }
        }
    }

}

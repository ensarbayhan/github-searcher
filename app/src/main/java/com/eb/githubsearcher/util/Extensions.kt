package com.eb.githubsearcher.util

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.eb.githubsearcher.models.BaseModel
import com.google.gson.GsonBuilder

/**
 * Created by ebayhan on 12/23/20.
 */
internal fun View.visible() {
    visibility = View.VISIBLE
}

internal fun View.gone() {
    visibility = View.GONE
}

internal fun View.showIf(visible: Boolean) {
    if (visible)
        visible()
    else
        gone()
}

fun <T : BaseModel> T.toPrettyJson(): String? {
    return GsonBuilder().setPrettyPrinting().create().toJson(this)
}

fun Activity.printMessage(message: String?) {
    toast(message)
    log(message)
}

fun Activity.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun log(message: String?) {
    Log.d("GithubSearcher", message ?: "")
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
            .load(url)
            .into(this)
}

fun EditText.onActionDone(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}

fun View.hideKeyboard() {
    val inputMethodManager = this.context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)
}
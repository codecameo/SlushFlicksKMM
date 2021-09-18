package com.sifat.slushflicks.viewaction

sealed class ViewAction(var isRead: Boolean = false)
object InitAction : ViewAction()

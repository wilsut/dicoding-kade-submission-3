package com.wilsut.footballleague.fragment

import com.wilsut.footballleague.model.Event

interface EventView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}
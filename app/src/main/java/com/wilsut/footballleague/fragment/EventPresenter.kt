package com.wilsut.footballleague.fragment

import com.google.gson.Gson
import com.wilsut.footballleague.api.ApiRepository
import com.wilsut.footballleague.api.TheSportDBApi
import com.wilsut.footballleague.model.EventResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventPresenter(
    private val view: EventView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getNextEvents(idLeague: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getNextEvents(idLeague)),
                EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }

    fun getPreviousEvents(idLeague: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getPreviousEvents(idLeague)),
                EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }

    fun searchEvents(query: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.searchEvents(query)),
                EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.event)
            }
        }
    }
}
package com.wilsut.footballleague.main

import com.wilsut.footballleague.model.Team

interface LeagueView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}
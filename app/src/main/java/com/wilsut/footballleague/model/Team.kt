package com.wilsut.footballleague.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam") val teamId: String? = null,
    @SerializedName("strTeam") val teamName: String? = null,
    @SerializedName("strTeamBadge") val teamBadge: String? = null,
    @SerializedName("strDescriptionEN") val teamDescription: String? = null,
    @SerializedName("idLeague") val leagueId: String? = null
)
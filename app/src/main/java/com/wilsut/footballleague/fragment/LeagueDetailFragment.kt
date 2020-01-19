package com.wilsut.footballleague.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import com.wilsut.footballleague.api.TheSportDBApi
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx
import org.json.JSONObject

class LeagueDetailFragment : Fragment() {

    private var idLeague: String? = ""
    private var teamName: String? = ""
    private var teamBadge: String? = ""
    private var teamDescription: String? = ""

    private fun getDescription(idLeague: String?, textView: TextView) {
        val queue = Volley.newRequestQueue(context)
        val url = TheSportDBApi.getLeagueDetails(idLeague)

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val jsonObject = JSONObject(response)
                if (!jsonObject.isNull("leagues")) {
                    val jsonArray = jsonObject.getJSONArray("leagues")
                    val teamJson = jsonArray.getJSONObject(0)
                    teamDescription = teamJson["strDescriptionEN"].toString()
                    textView.text = teamDescription
                }
            },
            Response.ErrorListener { })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        idLeague = arguments?.getString("id_league")
        teamName = arguments?.getString("league_name")
        teamBadge = arguments?.getString("badge")

        return LeagueDetailFragmentUI().createView(AnkoContext.create(ctx, this))
    }

    inner class LeagueDetailFragmentUI : AnkoComponent<LeagueDetailFragment> {
        override fun createView(ui: AnkoContext<LeagueDetailFragment>) = with(ui) {
            scrollView {
                lparams(matchParent, matchParent) {
                    padding = dip(16)
                }

                linearLayout {
                    lparams(matchParent, wrapContent) {
                        orientation = LinearLayout.VERTICAL
                    }

                    imageView {
                        teamBadge.let { Picasso.get().load(it).into(this) }
                    }.lparams(dip(120), dip(120)) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    textView {
                        text = teamName
                        textAppearance = android.R.style.TextAppearance_Medium
                    }.lparams(wrapContent, wrapContent) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    val description = textView {
                        text = teamDescription
                    }.lparams(wrapContent, wrapContent)

                    getDescription(idLeague, description)
                }
            }
        }
    }
}
package com.wilsut.footballleague.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class TeamActivity : AppCompatActivity() {

    private lateinit var teamName: String
    private lateinit var teamBadge: String
    private lateinit var teamDescription: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teamName = intent.getStringExtra("team_name")
        teamBadge = intent.getStringExtra("team_badge")
        teamDescription = intent.getStringExtra("team_description")
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

                textView {
                    text = teamDescription
                }.lparams(wrapContent, wrapContent)
            }
        }
    }
}

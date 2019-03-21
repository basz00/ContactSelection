package com.baz.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.LazyThreadSafetyMode.NONE

class MainActivity : AppCompatActivity() {

    private companion object {

        private const val CONTACT_SELECTION_TAG = "contactSelectionTag"
    }

    private val adapter by lazy(NONE) { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.setHasFixedSize(true)
        mainRecyclerView.adapter = adapter
        mainButton.setOnClickListener { openContactSelection() }
    }

    private fun openContactSelection() {
        //TODO add animation for smooth transition
        supportFragmentManager.beginTransaction()
            .add(R.id.mainContactSelectionFrame, ContactSelectionFragment.newInstance(), CONTACT_SELECTION_TAG)
    }
}

package com.example.notekeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_first.*

class MainActivity : AppCompatActivity() {
    private val logcatTag = "MainActivity"

    private var fragmentRefreshListener: FragmentRefreshListener? = null

    fun getFragmentRefreshListener(): FragmentRefreshListener? {
        return fragmentRefreshListener
    }

    fun setFragmentRefreshListener(fragmentRefreshListener: FragmentRefreshListener?) {
        this.fragmentRefreshListener = fragmentRefreshListener
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val oldValue = textview_first.text.toString().toInt()
            val newValue = oldValue * 2
            textview_first.text = newValue.toString()
            Snackbar.make(view, "Value $oldValue changed to $newValue", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null)
                .show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                if (getFragmentRefreshListener() != null) {
                    getFragmentRefreshListener()?.onFragmentNext();
                }
                true
            }
            R.id.action_prev -> {
                if (getFragmentRefreshListener() != null) {
                    getFragmentRefreshListener()?.onFragmentPrev();
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
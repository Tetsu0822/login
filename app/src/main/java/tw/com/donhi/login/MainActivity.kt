package tw.com.donhi.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import tw.com.donhi.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val LOGIN_REQ: Int = 11
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var logon:Boolean = false
    val TAG = MainActivity::class.java.simpleName
    var requestLogon = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        var user = getSharedPreferences("login", MODE_PRIVATE)
            .getString("user", null)
        var password = getSharedPreferences("login", MODE_PRIVATE)
            .getString("password", null)
        Log.d(TAG, "getData: User: $user , Password: $password ")
        if (result.resultCode == RESULT_OK) {
            val user = result.data?.getStringExtra("user")
            val password = result.data?.getStringExtra("password")
            Log.d(TAG, "onResult: id: $user , pwd: $password")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!logon) {
            val intent = Intent(this, LoginActivity::class.java)
            //startActivity(intent)
            startActivityForResult(intent, LOGIN_REQ)
            //requestLogon.launch(intent)

        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOGIN_REQ) {
            if (resultCode != RESULT_OK) {
                finish()
            }
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
            else -> super.onOptionsItemSelected(item)
        }
    }

}
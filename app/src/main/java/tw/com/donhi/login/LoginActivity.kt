package tw.com.donhi.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import tw.com.donhi.login.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val TAG = LoginActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun login(view: View) {
        var uid = binding.edUid.text.toString()
        var pwd = binding.edPwd.text.toString()
        //FirebaseDatabase.getInstance().getReference("users").child("userid").child("password")
        if ("jack".equals(uid) && "1234".equals(pwd)) {
            //Save to Local
            getSharedPreferences("login", MODE_PRIVATE)
                .edit()
                .putString("userId", uid)
                .putString("userPwd", pwd)
                .apply()
            setResult(RESULT_OK, intent.putExtra("user", uid))
            setResult(RESULT_OK, intent.putExtra("password", pwd))
            finish()
        }
    }
}
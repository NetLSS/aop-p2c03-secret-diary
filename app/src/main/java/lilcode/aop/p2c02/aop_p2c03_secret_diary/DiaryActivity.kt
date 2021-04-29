package lilcode.aop.p2c02.aop_p2c03_secret_diary

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper()) // 메인 루퍼를 넣어주면 메인 스레드와 연결

    private val _diaryEditText: EditText by lazy{
        findViewById<EditText>(R.id.diaryEditText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val diaryEditText = findViewById<EditText>(R.id.diaryEditText)
        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)

        diaryEditText.setText(detailPreferences.getString("detail", ""))



        val runnable = Runnable {
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit{
                putString("detail", diaryEditText.text.toString())
                apply()
            }

            Log.d("DiaryActivity", "SAVE!!! ${diaryEditText.text.toString()}")
        }

        // 수정 할때 마다 저장
        diaryEditText.addTextChangedListener {
            Log.d("DiaryActivity", "TextChanged :: $it")

            handler.removeCallbacks(runnable) // 이전에 팬딩되어있는 runnable이 있다면 제거
            //몇 초 이후에 runnable을 실행
            handler.postDelayed(runnable, 500) // 0.5 초 이후에 실행
        }
    }
}
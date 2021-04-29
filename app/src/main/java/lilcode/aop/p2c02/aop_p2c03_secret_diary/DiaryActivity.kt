package lilcode.aop.p2c02.aop_p2c03_secret_diary

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity() {

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
        }

        // 수정 할때 마다 저장
        diaryEditText.addTextChangedListener {
            detailPreferences.edit {
                putString("detail", diaryEditText.text.toString() /*it.toString()*/)
            }
        }
    }
}
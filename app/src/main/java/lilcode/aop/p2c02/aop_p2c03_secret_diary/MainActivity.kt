package lilcode.aop.p2c02.aop_p2c03_secret_diary

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {


    // MainActivity 생성 시점에 뷰가 전부 생성되지 않으므로 lazy 사용
    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val openButton: AppCompatButton by lazy{
        findViewById<AppCompatButton>(R.id.openButton)
    }

    private val changePasswordButton: AppCompatButton by lazy{
        findViewById<AppCompatButton>(R.id.openButton)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 호출을 해서 lazy 초기화 호출되도록
        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener{
            // 비밀 번호 데이터 저장에 사용될 수 있는 방식
            // 1. 로컬 db
            // 2. 파일 (shared preferences 등)
            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            // 여기 앱에서만 사용할 것이기 때문에 MODE_PRIVATE 로 사용

            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (passwordPreferences.getString("password", "000").equals(passwordFromUser)){
                // 패스워드 일치

                // TODO 다이어리 페이지 open
                //startActivity()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("실패!!")
                    .setMessage("비밀번호가 잘못되었습니다.")
                    .setPositiveButton("확인"){ _, _ -> }
                    .create()
                    .show()
            }

        }
    }
}
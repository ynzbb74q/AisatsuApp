package jp.techacademy.takayuki.ochiai.aisatsuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.app.TimePickerDialog
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTimePickerDialog.setOnClickListener{
            showTimePickerDialog()
        }
    }

    // TimePickerDialog表示
    private fun showTimePickerDialog() {
        val inputHour: String? = editHour.text.toString()
        val inputMinute: String? = editMinute.text.toString()

        // 整数チェック用パターン
        val regex = Regex("\\d+")

        // 入力値チェック(null, 整数, 範囲)
        if (inputHour.isNullOrEmpty() || inputMinute.isNullOrEmpty()
            || !regex.matches(inputHour) || !regex.matches(inputMinute)
            || !(inputHour.toInt() in 0..23 && inputMinute.toInt() in 0..59)) {
            // エラー通知ダイアログ表示
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("入力エラー")
            alertDialogBuilder.setMessage("入力値が不正です。再入力してください。")
            alertDialogBuilder.setPositiveButton("OK", null)
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()

            return
        }

        // TimePickerDialog表示
        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                greetResult.text = getGreetMessage(hour, minute)
            },
            inputHour.toInt(), inputMinute.toInt(), true
        )
        timePickerDialog.show()
    }

    // あいさつ文言取得
    private fun getGreetMessage(hour: Int, minute: Int): String {
        var greetMessage:String

        if (hour >= 2 && hour < 10) {
            greetMessage = "おはよう"
        } else if (hour >= 10 && hour < 18) {
            greetMessage = "こんにちは"
        } else {
            greetMessage = "こんばんは"
        }
        return greetMessage
    }
}

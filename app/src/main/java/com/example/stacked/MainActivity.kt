package com.example.stacked

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // declared UI elements which will contain dynamic content
        val inputYearlyGoal = findViewById<EditText>(R.id.input_one)
        val inputBooksMonth = findViewById<EditText>(R.id.input_two)
        val inputBooksYear = findViewById<EditText>(R.id.input_three)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate)
        val btnReset = findViewById<Button>(R.id.btn_reset)

        val tvMonthlyGoal = findViewById<TextView>(R.id.result_monthly_goal)
        val tvMonthlyProgress = findViewById<TextView>(R.id.result_monthly_progress)
        val tvYearlyProgress = findViewById<TextView>(R.id.result_yearly_progress)
        val tvBooksLeftMonth = findViewById<TextView>(R.id.result_books_left_month)
        val tvBooksLeftYear = findViewById<TextView>(R.id.result_books_left_year)

        // button click listener for when calculate button is pressed
        btnCalculate.setOnClickListener {
            // reads input from user and turns into integers
            val yearlyGoal = inputYearlyGoal.text.toString().toIntOrNull()
            val booksReadMonth = inputBooksMonth.text.toString().toIntOrNull()
            val booksReadYear = inputBooksYear.text.toString().toIntOrNull()

            // if all inputs are valid
            if (yearlyGoal != null && booksReadMonth != null && booksReadYear != null && yearlyGoal > 0) {
                // calculates the following calculations
                val monthlyGoal = yearlyGoal / 12
                val monthlyProgress = (booksReadMonth * 100) / monthlyGoal
                val yearlyProgress = (booksReadYear * 100) / yearlyGoal
                val booksLeftMonth = monthlyGoal - booksReadMonth
                val booksLeftYear = yearlyGoal - booksReadYear

                // populates the UI elements with the updated calculated results
                tvMonthlyGoal.text = "$monthlyGoal"

                // the following are conditional statements to check if reader has completed a goal to display a different message
                tvMonthlyProgress.text = if (booksLeftMonth <= 0)
                    "100%"
                else
                    "$monthlyProgress%"

                tvYearlyProgress.text = if (booksLeftYear <= 0)
                    "100%"
                else
                    "$yearlyProgress%"

                tvBooksLeftMonth.text = if (booksLeftMonth <= 0)
                    "Monthly reading goal? Done!"
                else
                    "$booksLeftMonth books left."

                tvBooksLeftYear.text = if (booksLeftYear <= 0)
                    "Yearly reading goal? Done!"
                else
                    "$booksLeftYear books left."

            } else {
                // message displayed if user does not input into a field
                Toast.makeText(this, "Please enter valid numbers in all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        // button click listener for when reset button is pressed
        btnReset.setOnClickListener {
            // clears the inputs
            inputYearlyGoal.text.clear()
            inputBooksMonth.text.clear()
            inputBooksYear.text.clear()

            // resets to default values
            tvMonthlyGoal.text = getString(R.string.result_monthly_goal)
            tvMonthlyProgress.text = getString(R.string.result_monthly_progress)
            tvYearlyProgress.text = getString(R.string.result_yearly_progress)
            tvBooksLeftMonth.text = getString(R.string.result_books_left_month)
            tvBooksLeftYear.text = getString(R.string.result_books_left_year)
        }

        val btnInfo = findViewById<ImageButton>(R.id.btn_info)

        // button click listener for when info button is pressed to reveal modal screen
        btnInfo.setOnClickListener {
            // inflates the modal layout
            val dialogView = layoutInflater.inflate(R.layout.info_modal, null)

            // creates the info dialog
            val alertDialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .create()

            // button click listener for when done button is pressed in modal screen
            alertDialog.window?.setBackgroundDrawableResource(R.drawable.modal)

            val btnDialogGotIt = dialogView.findViewById<Button>(R.id.btn_modal)
            btnDialogGotIt.setOnClickListener {
                alertDialog.dismiss() // closes the modal screen
            }

            alertDialog.show() // shows the modal screen

    }

}
}
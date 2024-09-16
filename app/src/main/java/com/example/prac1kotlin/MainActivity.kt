package com.example.prac1kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Date

class MainActivity : AppCompatActivity()
{
    // Объявление переменных для UI элементов и ExpenseManager
    private lateinit var expenseManager: ExpenseManager
    private lateinit var amountEditText: EditText
    private lateinit var categorySpinner: Spinner
    private lateinit var addButton: Button
    private lateinit var displayTextView: TextView



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация expenseManager
        expenseManager = ExpenseManager()

        // Привязка UI элементов к переменным
        amountEditText = findViewById(R.id.amountEditText)
        categorySpinner = findViewById(R.id.categorySpinner)
        addButton = findViewById(R.id.addButton)
        displayTextView = findViewById(R.id.displayTextView)

        // Установка слушателя для кнопки добавления расхода

        addButton.setOnClickListener {
            // Получение суммы из EditText
            val amount = amountEditText.text.toString().toDoubleOrNull()

            // Получение выбранной категории из Spinner
            val category = categorySpinner.selectedItem.toString()

            if (amount != null && amount > 0)
            {
                // Создание нового объекта Expense
                val expense = Expense(amount, category, Date())

                // Добавление расхода в ExpenseManager
                expenseManager.addExpense(expense)

                // Обновление отображения
                updateDisplay()

                // Очистка поля ввода суммы
                amountEditText.text.clear()
            }
        }
        updateDisplay()
    }

    // Метод для обновления отображения расходов

    private fun updateDisplay() {

        // Получение всех расходов
        val allExpenses = expenseManager.getAllExpenses()

        // Получение сумм по категориям
        val categorySums = expenseManager.getCategorySums()

        // Формирование текста для отображения
        val displayText = StringBuilder()
        displayText.append("Все затраты:\n")

        // Вызывается метод getInfo().
        // Результат добавляется к displayText.
        // Затем добавляется символ новой строки \n.

        allExpenses.forEach { displayText.append(it.getInfo()).append("\n") }
        displayText.append("\nСумма по категории:\n")

        // Для каждой пары ключ-значение в словаре:
        // category - это ключ (строка с названием категории).
        // sum - это значение (сумма расходов в этой категории).
        // Формируется строка с категорией и отформатированной суммой, которая добавляется к displayText.

        categorySums.forEach { (category, sum) ->
            displayText.append("$category: $%.2f\n".format(sum))
        }

        // Установка сформированного текста в TextView
        displayTextView.text = displayText.toString()
    }
}

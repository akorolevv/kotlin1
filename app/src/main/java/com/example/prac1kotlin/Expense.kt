package com.example.prac1kotlin

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Конкретный расход (НЕ КАТЕГОРИТЯ)
class Expense(private val amount: Double, private val category: String, private val date: Date) // три поля, первичный конструктор
{
    // геттер вывода всей информации по конкретной трате
    fun getInfo(): String
    {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return "Сумма: $%.2f, Категория: %s, Дата: %s".format(amount, category, dateFormat.format(date))
    }

    // геттеры для получения категории и суммы
    fun getAmount(): Double = amount
    fun getCategory(): String = category
}
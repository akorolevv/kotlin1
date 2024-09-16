package com.example.prac1kotlin


// класс для управления категориями расходов
class ExpenseManager
{

    private val expenses = mutableListOf<Expense>() // создание изменяемого списка, где каждый элемент является каким либо расходом

    // функция для добавления расхода в список расходов
    fun addExpense(expense: Expense)
    {
        expenses.add(expense)
    }

    // геттер чтобы получить список всех трат
    fun getAllExpenses(): List<Expense> = expenses

    // функция подсчета суммы трат для каждой категории
    fun getCategorySums(): Map<String, Double>
    {
        val categorySums = mutableMapOf<String, Double>() // создаем изменяемый список где ключ - это категория, а значение - сумма трат по категории

        // проходимся по конкретным тратам
        for (expense in expenses)
        {

            // определеяем их категорию и сумму расхода
            val category = expense.getCategory()
            val amount = expense.getAmount()

            // по ключу (category) списка, получаем затрату по категории с помощью метода getOrDefault, 0.0 - дефолтное значение, если по ключу нету значения
            val currentSum = categorySums.getOrDefault(category, 0.0)

            // прибавляем полученное значение и текущее
            categorySums[category] = currentSum + amount
        }

        return categorySums
    }
}
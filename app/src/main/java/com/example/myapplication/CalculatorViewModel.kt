package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    companion object {
        private const val MAX_DIGITS = 10
        private const val DEFAULT_INPUT_NUM = "0"
        private const val DOT = "."
    }

    val sum = MutableLiveData(DEFAULT_INPUT_NUM)
    val inputNum = MutableLiveData(DEFAULT_INPUT_NUM)
    private val numbers = MutableLiveData<List<String>>(emptyList())

    fun onNumClicked(input: String) {
        val inputValue = inputNum.value ?: DEFAULT_INPUT_NUM
        val newInput = when {
            inputValue.count { it.isDigit() } + 1 > MAX_DIGITS -> inputValue
            inputValue.contains(DOT) && input == DOT -> inputValue
            inputValue == DEFAULT_INPUT_NUM && input == DOT -> inputValue + DOT
            inputValue == DEFAULT_INPUT_NUM -> input
            else -> inputValue + input
        }
        inputNum.value = newInput
    }

    fun push() {
        val inputValue = inputNum.value ?: DEFAULT_INPUT_NUM
        if (inputValue == DEFAULT_INPUT_NUM) return

        val list = numbers.value?.toMutableList() ?: mutableListOf()
        list.add(inputValue)
        numbers.value = list
        calculateSum()
        clearInput()
    }

    fun pop() {
        val list = numbers.value?.toMutableList() ?: mutableListOf()
        if (list.isEmpty()) return

        list.removeLast()
        numbers.value = list
        calculateSum()
    }

    private fun calculateSum() {
        val dSum = numbers.value?.sumOf {
            it.toDoubleOrNull() ?: 0.0
        } ?: 0.0
        sum.value = dSum.toString()
    }

    private fun clearInput() {
        inputNum.value = DEFAULT_INPUT_NUM
    }
}
@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sum = 0.0
    for (element in v) {
        sum += Math.pow(element, 2.0)
    }
    return Math.sqrt(sum)
}


/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var sum = 0.0
    if (list.isEmpty()) return 0.0
    for (element in list) {
        sum += element
    }
    return sum / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val average = mean(list)
    for (i in 0 until list.size) {
        list[i] -= average
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var sum = 0
    for (i in 0 until a.size) {
        sum += a[i] * b[i]
    }
    return sum
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty()) return 0
    var sum = p[0]
    for (i in 1 until p.size) {
        sum += p[i] * Math.pow(x.toDouble(),i.toDouble()).toInt();
    }
    return sum
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sum = 0
    for (i in 0 until list.size) {
        list[i] += sum
        sum = list[i]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val answer = mutableListOf<Int>()
    var f = 2
    var number = n
    while (number > 1) {
        if (number % f == 0) {
            answer.add(f)
            number /= f
            f = 1
        }
        f++
    }
    return answer
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int>  {
    val result = mutableListOf<Int>()
    var number = n
    if (number == 0) result.add(0)
    while (number > 0) {
        val remainder = number % base
        result.add(remainder)
        number /= base
    }
    return result.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val convert = convert(n, base) /*con = convert*/
    val answer = StringBuilder()
    for (i in convert) {
        if (i < 10) answer.append(i)
        else {
            answer.append('a'.plus(i - 10))
        }
    }
    return answer.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var answer = 0
    for (i in 0 until digits.size) {
        answer = answer * base + digits[i]
    }
    return answer
}
/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val digits = mutableListOf<Int>()
    for (i in str) {
        if (i.isDigit()) digits.add(i.minus('0'))
        else {
            digits.add(i.minus('a') + 10)
        }
    }
    return decimal(digits, base)
}
/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val a = n / 1000
    val b = n / 100 % 10
    val c = n / 10 % 10
    val d = n % 10
    val answer = StringBuilder()
    val romannum1 = listOf("C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    val romannum2 = listOf("X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val romannum3 = listOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
    if (a != 0) {
        for (i in 1..a) answer.append("M")
    }
    if (b != 0) answer.append(romannum1[b - 1])
    if (c != 0) answer.append(romannum2[c - 1])
    if (d != 0) answer.append(romannum3[d - 1])
    return answer.toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String  {
    val result = StringBuilder()
/*Найдем цифры число n.Например 123456*/
    val r1 = n % 10 /*r1 = 6*/
    val r2 = n / 10 % 10 /*r2 = 5*/
    val r3 = n / 100 % 10 /*r3 = 4*/
    val r4 = n / 1000 % 10 /*r4 = 3*/
    val r5 = n / 10000 % 10 /*r5 = 2*/
    val r6 = n / 100000 % 10 /*r6 = 1*/
/*cоздаем списки */
    val list1 = listOf("", "один ", "два ", "три ", "четыре ", "пять ", "шесть ", "семь ", "восемь ", "девять ")
    val list2 = listOf("десять ", "одиннадцать ", "двенадцать ", "тринадцать ", "четырнадцать ", "пятнадцать ",
        "шестнадцать ", "семнадцать ", "восемнадцать ", "девятнадцать ")
    val list3 = listOf("", "десять ", "двадцать ", "тридцать ", "сорок ", "пятьдесят ", "шестьдесят ",
        "семьдесят ", "восемьдесят ", "девяносто ")
    val list4 = listOf("", "сто ", "двести ", "триста ", "четыреста ", "пятьсот ", "шестьсот ", "семьсот ", "восемьсот ", "девятьсот ")
    val list5 = listOf("тысяч ", "тысяча ", "тысячи ")
    val list6 = listOf("", "одна ", "две ", "три ", "четыре ", "пять ", "шесть ", "семь ", "восемь ", "девять ")
/*Начнем проверят цифры и используем индекси листов.*/
    result.append(list4[r6])
    if (r5 != 0) {
        if (r5 == 1 && r4 != 0) result.append(list2[r4] + list5[0])
        if (r5 != 1) result.append(list3[r5])
        if (r5 != 1 && r4 == 0) result.append(list5[0])
    }
    if (r5 != 1 && r4 != 0) {
        if (r4 == 1) result.append(list6[r4] + list5[1])
        if (r4 in 2..4) result.append(list6[r4] + list5[2])
        if (r4 in 5..9) result.append(list6[r4] + list5[0])
    }
    if (r6 != 0 && r5 == 0 && r4 == 0) result.append(list5[0])
    result.append(list4[r3])
    if (r2 != 1) result.append(list3[r2])
    if (r2 == 1) result.append(list2[r1])
    if (r2 != 1 && r1 != 0) result.append(list1[r1])
    return result.trim().toString()
}
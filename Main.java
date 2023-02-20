package EntryTask;

/*
Создай консольное приложение “Калькулятор”.
 Приложение должно читать из консоли введенные пользователем строки, числа, арифметические операции проводимые между ними и выводить в консоль результат их выполнения.
Реализуй класс Main с методом public static String calc(String input).
Метод должен принимать строку с арифметическим выражением между двумя числами и возвращать строку с результатом их выполнения. Ты можешь добавлять свои импорты, классы и методы.
 Добавленные классы не должны иметь модификаторы доступа (public или другие)

Требования:
Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b.
Данные передаются в одну строку (смотри пример)! Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.

Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.

Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не ограничиваются по величине и могут быть любыми.

Калькулятор умеет работать только с целыми числами.

Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.

При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно, при вводе арабских - ответ ожидается арабскими.

При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.

При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.

Результатом операции деления является целое число, остаток отбрасывается.

Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.
Результатом работы калькулятора с римскими числами могут быть только положительные числа, если результат работы меньше единицы, выбрасывается исключение
 */

public class Main {
    enum RomanNumeral {
        I(1), II(2), III(3), IV(4), V(5), VI(6),
        VII(7), VIII(8), IX(9), X(10), XL(40), L(50),
        XC(90), C(100);
        private int arabianValue;

        RomanNumeral(int arabianValue) {
            this.arabianValue = arabianValue;
        }

        public int getArabianValue() {
            return arabianValue;
        }
    }

    public static String calc(String input) throws Exception {
        String result;
        String[] strings = input.split(" ");

        if (strings.length > 3 || strings.length < 3) {
            throw new Exception("Неверный формат строки: операция возможна только с двумя операндами и одним оператором");
        }

        int firstNumber;
        int secondNumber;
        int numberOfRomanNumbers = 0;

        try {
            firstNumber = Integer.parseInt(strings[0]);
        } catch (NumberFormatException e) {
            try {
                firstNumber = RomanNumeral.valueOf(strings[0]).getArabianValue();
                numberOfRomanNumbers++;
            } catch (IllegalArgumentException s) {
                throw new Exception("Было введено нецелое число либо римское число выше 10 (X)");
            }
        }

        try {
            secondNumber = Integer.parseInt(strings[2]);
        } catch (NumberFormatException e) {
            try {
                secondNumber = RomanNumeral.valueOf(strings[2]).getArabianValue();
                numberOfRomanNumbers++;
            } catch (IllegalArgumentException s) {
                throw new Exception("Было введено нецелое число либо римское число выше 10 (X)");
            }
        }
        if ((firstNumber < 0 || firstNumber > 10) || (secondNumber < 0 || secondNumber > 10)) {
            throw new Exception("Операция возможна с числами только от 1 до 10 включительно");
        }
        if (numberOfRomanNumbers == 1) {
            throw new Exception("Операция возможна только c использованием одной системы счисления одновременно");
        }
        int resultOfOperation;
        switch (strings[1]) {
            case ("+"): {
                resultOfOperation = firstNumber + secondNumber;
                break;
            }
            case ("-"): {
                resultOfOperation = firstNumber - secondNumber;
                break;
            }
            case ("*"): {
                resultOfOperation = firstNumber * secondNumber;
                break;
            }
            case ("/"): {
                resultOfOperation = firstNumber / secondNumber;
                break;
            }
            default:
                throw new Exception("Доступны только операции сложения, вычитания, умножения и деления с двумя числами");
        }

        if (numberOfRomanNumbers == 0) {
            result = String.valueOf(resultOfOperation);
        } else if (numberOfRomanNumbers == 2 && resultOfOperation <= 0) {
            throw new Exception("В римской системе используются только положительные числа");
        } else {
            result = intToRoman(resultOfOperation);
        }

        return result;
    }

    public static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int times = 0;
        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] ints = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num = num % ints[i];
            while (times > 0) {
                result.append(romans[i]);
                times--;
            }
        }
        return result.toString();
    }
    public static void main(String[] args) throws Exception {
        System.out.println(calc("IX * IX"));
    }
}

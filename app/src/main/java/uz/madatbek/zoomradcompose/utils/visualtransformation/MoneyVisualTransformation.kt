package uz.madatbek.zoomradcompose.utils.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class MoneyVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Функция для форматирования текста
        val formattedText = formatMoney(text.text)

        // Возвращаем преобразованный текст и соответствующее OffsetMapping
        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = MoneyOffsetMapping(text.text, formattedText)
        )
    }

    private fun formatMoney(input: String): String {
        val builder = StringBuilder()
        var count = 0

        for (i in input.length - 1 downTo 0) {
            builder.append(input[i])
            count++
            if (count == 3 && i != 0) {
                builder.append(' ')
                count = 0
            }
        }

        return builder.reverse().toString()
    }

    // Класс для сопоставления исходных и преобразованных позиций
    private class MoneyOffsetMapping(private val original: String, private val transformed: String) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            var transformedOffset = offset
            for (i in 0 until offset) {
                if (i < transformed.length && transformed[i] == ' ') {
                    transformedOffset++
                }
            }
            return transformedOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            var originalOffset = offset
            for (i in 0 until offset) {
                if (i < transformed.length && transformed[i] == ' ') {
                    originalOffset--
                }
            }
            return originalOffset
        }
    }
}

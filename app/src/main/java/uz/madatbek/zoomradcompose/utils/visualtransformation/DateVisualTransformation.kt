package uz.madatbek.zoomradcompose.utils.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object DateVisualTransformation:VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Преобразование текста в формат MM/YY
        val inputText = text.text
        val formattedText = StringBuilder()

        for (i in inputText.indices) {
            if (i == 2) {
                formattedText.append('\\')
            }
            formattedText.append(inputText[i])
        }

        val out = formattedText.toString()
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return if (offset <= 2) offset else offset + 1
            }

            override fun transformedToOriginal(offset: Int): Int {
                return if (offset <= 2) offset else offset - 1
            }
        }

        return TransformedText(AnnotatedString(out), offsetMapping)
    }
}
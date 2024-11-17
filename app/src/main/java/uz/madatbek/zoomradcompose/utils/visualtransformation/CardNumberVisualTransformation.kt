package uz.madatbek.zoomradcompose.utils.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }
        val stringBuilder = StringBuilder()

        digits.forEachIndexed { index, c ->
            stringBuilder.append(c)
            if (index % 4 == 3 && index != digits.lastIndex) {
                stringBuilder.append(' ')
            }
        }

        val newText = stringBuilder.toString()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > digits.length) return newText.length

                val spacesBeforeOffset = (offset - 1) / 4
                return offset + spacesBeforeOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > newText.length) return digits.length

                val spacesBeforeOffset = (offset - 1) / 5
                return offset - spacesBeforeOffset
            }
        }

        return TransformedText(AnnotatedString(newText), offsetMapping)
    }
}
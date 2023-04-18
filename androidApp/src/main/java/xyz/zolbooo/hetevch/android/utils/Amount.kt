package xyz.zolbooo.hetevch.android.utils

import android.icu.text.DecimalFormat
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.util.*

private val mntFormatter = DecimalFormat
    .getCurrencyInstance(Locale("mn", "MN"))
    .apply {
        maximumFractionDigits = 0
    }

fun Long.formatMNT(): String = mntFormatter.format(this)

private fun String.nthDigitIndex(n: Int): Int {
    var count = 0
    for (i in indices) {
        if (i >= length) {
            return length
        }
        if (this[i].isDigit()) {
            count += 1
        }
        if (count == n) {
            return i
        }
    }
    return -1
}

private fun String.countDigits(range: IntRange): Int {
    var count = 0
    for (i in range) {
        if (i >= length) {
            break
        }
        if (this[i].isDigit()) {
            count += 1
        }
    }
    return count
}

object AmountVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val original = text.text
        val amount = original.toLongOrNull() ?: return TransformedText(
            AnnotatedString(""),
            OffsetMapping.Identity
        )
        val formatted = amount.formatMNT()
        return TransformedText(
            AnnotatedString(formatted),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (formatted.isEmpty()) {
                        return 0
                    }
                    val currentDigit = original.countDigits(0..offset)
                    val originalIndex = original.nthDigitIndex(currentDigit)
                    val formattedIndex = formatted.nthDigitIndex(currentDigit)
                    return when {
                        offset == originalIndex -> formattedIndex
                        offset < originalIndex -> formattedIndex - 1
                        else -> formattedIndex + 1
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val currentDigit = original.countDigits(0..offset)
                    val formattedIndex = formatted.nthDigitIndex(currentDigit)
                    val originalIndex = original.nthDigitIndex(currentDigit)
                    return when {
                        offset == formattedIndex -> originalIndex
                        offset < formattedIndex -> originalIndex - 1
                        else -> originalIndex + 1
                    }
                }
            }
        )
    }
}

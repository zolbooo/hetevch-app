package xyz.zolbooo.hetevch.android.utils

import android.icu.text.DecimalFormat
import java.util.*

private val mntFormatter = DecimalFormat
    .getCurrencyInstance(Locale("mn", "MN"))
    .apply {
        maximumFractionDigits = 0
    }

fun Int.formatMNT(): String = mntFormatter.format(this)
fun Long.formatMNT(): String = mntFormatter.format(this)

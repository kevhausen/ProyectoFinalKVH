package com.example.proyectofinalkvh.view

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

//ACA HAY FUNCIONES BOLD, ITALIC Y COLOR PARA CAMBIAR EL ESTILO DEL TEXTO DE FORMA PROGRAMATICA(NO PESCAR LAS OTRAS FUNCIONES RARAS SPANNABLE)

private fun Spannable.openTags(tags: Array<out Any>) {
    tags.forEach { tag ->
        setSpan(tag, 0, 0, Spannable.SPAN_MARK_MARK)
    }
}
private fun Spannable.closeTags(tags: Array<out Any>) {
    tags.forEach { tag ->
        if (length > 0) {
            setSpan(tag, 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        } else {
            removeSpan(tag)
        }
    }
}
private fun apply(content: Array<out CharSequence>, vararg tags: Any): CharSequence {
    return SpannableStringBuilder().apply {
        openTags(tags)
        content.forEach { charSequence ->
            append(charSequence)
        }
        closeTags(tags)
    }
}

fun bold(vararg content: CharSequence): CharSequence = apply(content, StyleSpan(Typeface.BOLD))
fun italic(vararg content: CharSequence): CharSequence = apply(content, StyleSpan(Typeface.ITALIC))
fun color(color: Int, vararg content: CharSequence): CharSequence =
    apply(content, ForegroundColorSpan(color))

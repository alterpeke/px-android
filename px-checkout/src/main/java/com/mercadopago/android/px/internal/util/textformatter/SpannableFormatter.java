package com.mercadopago.android.px.internal.util.textformatter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import com.mercadolibre.android.ui.font.Font;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.util.ViewUtils;

public class SpannableFormatter extends ChainFormatter {

    private int textColor;
    private final Context context;
    private final SpannableStringBuilder spannableStringBuilder;
    private boolean semiBoldStyle;

    public SpannableFormatter(@NonNull final SpannableStringBuilder spannableStringBuilder,
        @NonNull final Context context) {
        this.spannableStringBuilder = spannableStringBuilder;
        this.context = context;
        semiBoldStyle = false;
    }

    public SpannableFormatter withTextColor(final int color) {
        textColor = color;
        return this;
    }

    public SpannableFormatter withSemiBoldStyle() {
        semiBoldStyle = true;
        return this;
    }

    public Spannable apply(@StringRes final int resId) {
        return apply(context.getString(resId));
    }

    @Override
    public Spannable apply(final CharSequence text) {
        final int indexStart = spannableStringBuilder.length();

        final int holder = R.string.px_string_holder;
        final CharSequence charSequence = context.getResources().getString(holder, text);
        spannableStringBuilder.append(charSequence);

        final int length = charSequence.length();

        ViewUtils.setColorInSpannable(textColor, indexStart, indexStart + length, spannableStringBuilder);
        updateTextStyle(indexStart, indexStart + length);

        return spannableStringBuilder;
    }

    private void updateTextStyle(final int indexStart, final int indexEnd) {
        if (semiBoldStyle) {
            ViewUtils.setSemiBoldFontInSpannable(indexStart, indexEnd, spannableStringBuilder, context);
        } else {
            ViewUtils
                .setFontInSpannable(indexStart, indexEnd, spannableStringBuilder, Font.REGULAR.getFontPath(), context);
        }
    }
}
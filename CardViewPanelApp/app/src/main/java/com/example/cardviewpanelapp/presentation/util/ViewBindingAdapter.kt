package com.example.cardviewpanelapp.presentation.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.cardviewpanelapp.R

@BindingAdapter("android:visibility")
fun visibility(view: View, visible: Boolean?) = when (visible) {
    true -> view.visibility = View.VISIBLE
    else -> view.visibility = View.GONE
}

@Suppress("UNUSED_PARAMETER")
@BindingAdapter(
    value = ["imageUrl", "iconResourceName", "roundedStyle", "iconColor", "placeHolder"],
    requireAll = false
)
fun imageUrl(
    view: ImageView,
    imageUrl: String?,
    iconResourceName: String?,
    roundedStyle: Boolean?,
    iconColor: Int?,
    placeHolder: Int = 0
) {
    if (iconResourceName.isNullOrEmpty() && imageUrl.isNullOrEmpty()) {
        return
    }

    try {
        if (!imageUrl.isNullOrEmpty()) {
            if (roundedStyle == true) {
                Glide.with(view).load(imageUrl).apply(
                    RequestOptions.bitmapTransform(
                        RoundedCorners(
                            view.resources.getDimension(
                                R.dimen.cornerRadius
                            ).toInt()
                        )
                    )
                )
                    .override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
                    .into(view)
            } else {
                Glide
                    .with(view)
                    .load(imageUrl)
                    .override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
                    .into(view)
            }
        } else if (!iconResourceName.isNullOrEmpty()) {
            val drawable = view.context.getDrawable(
                view.context.resources.getIdentifier(
                    iconResourceName,
                    "drawable",
                    view.context.packageName
                )
            )

            iconColor?.let {
                view.setColorFilter(it)
                drawable?.setTint(it)
            }

            //region resize svg icon for better quality
            view.setImageDrawable(null)
            view.background = null
            view.requestLayout()
            val customWidth = drawable?.let {
                it.intrinsicWidth / it.intrinsicHeight * view.height
            } ?: 0

            if (customWidth > 0) {
                view.layoutParams.width = customWidth
                view.background = drawable
            } else {
                Glide
                    .with(view)
                    .load(drawable)
                    .override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
//                .override(myWidth, view.height)
                    .into(view)
            }
            //endregion
        }
    } catch (e: Exception) {
//        Timber.d("ViewBindingAdapter_TAG: iconUrl: $imageUrl, iconResourceName: $iconResourceName")
    }
}
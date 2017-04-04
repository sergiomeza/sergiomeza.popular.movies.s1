package com.sergiomeza.popularmovies.stage1.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.sergiomeza.popularmovies.stage1.R

/**
 * Created by sergiomeza on 4/4/17.
 * Error view showing the appIcon with a textView Message
 */
class ErrorView(val mContext : Context) {
    fun init(mMessage: String = mContext.getString(R.string.error_empty_data)): View {
        val mView = LayoutInflater.from(mContext).inflate(R.layout.app_status_view, null)
        val mText = mView.findViewById(R.id.mTxtMessageStatus) as TextView
        mText.text = mMessage
        return mView
    }
}
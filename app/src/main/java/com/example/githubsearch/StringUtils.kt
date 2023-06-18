package com.example.githubsearch

import android.content.Context
import android.content.Intent
import android.net.Uri


fun String.openUrlInBrowser(context: Context){
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(this)
    context.startActivity(intent)
}

fun String.sendEmailToThisAddress(context: Context){
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:")
    intent.putExtra(Intent.EXTRA_EMAIL, this)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent);
    }
}
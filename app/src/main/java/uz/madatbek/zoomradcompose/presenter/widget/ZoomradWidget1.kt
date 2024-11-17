package uz.madatbek.zoomradcompose.presenter.widget

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.widget.RemoteViews
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.MainActivity


/**
 * Implementation of App Widget functionality.
 */
class ZoomradWidget1 : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

@SuppressLint("NewApi")
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.zoomrad_widget1)
    val intent= Intent(context, MainActivity::class.java)
    val pendingIntent=PendingIntent.getActivity(context,34,intent,PendingIntent.FLAG_IMMUTABLE)

    views.setOnClickPendingIntent(R.id.rood,pendingIntent)
    appWidgetManager.updateAppWidget(appWidgetId, views)
}






val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
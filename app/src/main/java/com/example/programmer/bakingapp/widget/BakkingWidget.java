package com.example.programmer.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.programmer.bakingapp.R;
import com.example.programmer.bakingapp.RecipeDetielActivity;
import com.example.programmer.bakingapp.RecipesActivity;

/**
 * Implementation of App Widget functionality.
 */
public class BakkingWidget extends AppWidgetProvider {
    private static String text = null;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,

                                int appWidgetId) {


        CharSequence widgetText = null;
        // Construct the RemoteViews object

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bakking_widget);
        if (RecipeDetielActivity.ss == null) {
            widgetText = context.getString(R.string.appwidget_text);

        } else {
            widgetText = RecipeDetielActivity.ss;
        }
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

        Intent intent = new Intent(context, RecipesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

        }


    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


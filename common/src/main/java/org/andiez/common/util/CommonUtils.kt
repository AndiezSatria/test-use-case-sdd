package org.andiez.common.util

import org.json.JSONObject
import retrofit2.HttpException
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Created by AndiezSatria on 11/10/2023.
 */
object CommonUtils {

    fun getErrorResponse(throwable: Throwable): String {
        if (throwable is HttpException) {
            val jsonData: String = throwable.response()?.errorBody()?.string().toString()
            return if (jsonData.contains("message")) {
                try {
                    val o = JSONObject(jsonData)
                    o.getString("message")
                } catch (e: Exception) {
                    e.message.toString()
                }
            } else {
                when (throwable.code()) {
                    502 -> "Bad Gateaway"
                    401 -> "Unauthorised User"
                    500 -> "Internal Server Error"
                    else -> "Something Went Wrong API is not responding properly!"
                }
            }
        } else {
            return throwable.message.toString()
        }
    }

    fun formatThousands(input: Long): String {
        val numberFormat = NumberFormat.getInstance(Locale("in", "IN"))
        return numberFormat.format(input)
    }

    fun getCalendarString(format: String, calendar: Calendar): String {
        val formatter = SimpleDateFormat(format, Locale("ID", "id"))
        return formatter.format(calendar.time)
    }

    fun getCalendarString(format: String, timeInMillis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        val formatter = SimpleDateFormat(format, Locale("ID", "id"))
        return formatter.format(calendar.time)
    }
}
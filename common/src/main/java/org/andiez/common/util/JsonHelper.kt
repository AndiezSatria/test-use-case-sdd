package org.andiez.common.util

import android.content.Context
import com.github.ajalt.timberkt.d
import org.andiez.common.R
import org.andiez.common.data.source.remote.model.LineDataResponse
import org.andiez.common.data.source.remote.model.LineEntryResponse
import org.andiez.common.data.source.remote.model.PieDataResponse
import org.andiez.common.data.source.remote.model.PieDataEntryResponse
import org.andiez.common.data.source.remote.model.TransactionResponse
import org.json.JSONArray
import java.io.IOException

/**
 * Created by AndiezSatria on 12/10/2023.
 */
class JsonHelper(private val context: Context) {
    private fun parsingFileToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.data).bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getPieDataFromName(chartName: String): PieDataEntryResponse? {
        var result: PieDataEntryResponse? = null
        val responseObject = JSONArray(parsingFileToString().toString())
        val jsonObject = responseObject.getJSONObject(0)

        val data = jsonObject.getJSONArray("data")
        for (i in 0 until data.length()) {
            val pieEntry = data.getJSONObject(i)

            val label = pieEntry.getString("label")
            if (label.contains(chartName, ignoreCase = true)) {
                val percentage = pieEntry.getString("percentage").toDoubleOrNull() ?: 0.0

                val transactionData = pieEntry.getJSONArray("data")
                val listTransaction = ArrayList<TransactionResponse>()

                for (j in 0 until transactionData.length()) {
                    val transaction = transactionData.getJSONObject(j)

                    d { "$transaction" }
                    val transactionDate = transaction.getString("trx_date")
                    val transactionNominal = transaction.getLong("nominal")
                    listTransaction.add(TransactionResponse(transactionDate, transactionNominal))
                }
                result = PieDataEntryResponse(label, percentage, listTransaction)
            }
        }
        return result
    }

    fun loadPieData(): PieDataResponse {
        val responseObject = JSONArray(parsingFileToString().toString())
        val jsonObject = responseObject.getJSONObject(0)

        val type = jsonObject.getString("type")
        val data = jsonObject.getJSONArray("data")
        val listData = ArrayList<PieDataEntryResponse>()

        for (i in 0 until data.length()) {
            val pieEntry = data.getJSONObject(i)

            val label = pieEntry.getString("label")
            val percentage = pieEntry.getString("percentage").toDoubleOrNull() ?: 0.0

            val transactionData = pieEntry.getJSONArray("data")
            val listTransaction = ArrayList<TransactionResponse>()

            for (j in 0 until transactionData.length()) {
                val transaction = transactionData.getJSONObject(j)

                d { "$transaction" }
                val transactionDate = transaction.getString("trx_date")
                val transactionNominal = transaction.getLong("nominal")
                listTransaction.add(TransactionResponse(transactionDate, transactionNominal))
            }
            listData.add(PieDataEntryResponse(label, percentage, listTransaction))
        }
        return PieDataResponse(type, listData)

    }

    fun loadLineData(): LineDataResponse {
        val responseObject = JSONArray(parsingFileToString().toString())
        val jsonObject = responseObject.getJSONObject(1)

        val type = jsonObject.getString("type")
        val data = jsonObject.getJSONObject("data")

        val months = data.getJSONArray("month")
        val listData = ArrayList<Int>()
        for (i in 0 until months.length()) {
            val month = months.getInt(i)
            listData.add(month)
        }
        val lineEntry = LineEntryResponse(listData)
        return LineDataResponse(type, lineEntry)
    }
}
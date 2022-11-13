package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.models.DayForecast
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ForecastAdapter(private val data: List<DayForecast>) : RecyclerView.Adapter<ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_forecast_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(data[position])


    }

    override fun getItemCount(): Int = data.size

}

class  ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val forecastTemp: TextView
    private val forecastDayForecast: TextView
    private val forecastMin: TextView
    private val forecastMax: TextView
    private val forecastSunSet: TextView
    private val forecastSunRise: TextView

    init {
        forecastTemp = view.findViewById(R.id.forecast_temp)
        forecastDayForecast = view.findViewById(R.id.forecast_date)
        forecastMin = view.findViewById(R.id.forecast_low_temp)
        forecastMax = view.findViewById(R.id.forecast_high_temp)
        forecastSunSet = view.findViewById(R.id.forecast_sunset)
        forecastSunRise = view.findViewById(R.id.forecast_sunrise)
    }

    fun bind(data: DayForecast) {
        ///forecastTemp.text = "temp " + data.temp.toString()
        forecastDayForecast.text = date(data.date)
        forecastMin.text = "low" + data.temp.min.toString()
        forecastMax.text = "high" + data.temp.max.toString()
        forecastSunSet.text = "sunset" + time(data.sunset).toString()
        forecastSunRise.text = "sunrise" + time(data.sunrise).toString()


    }

    fun date(date: Long): String {


        val formatter = DateTimeFormatter.ofPattern("MMM dd")
        val dateTime = LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.of("-5"))
        val formattedDate = formatter.format(dateTime)

        return formattedDate
    }

    fun time(time: Long): String {
        val formatter = DateTimeFormatter.ofPattern("MMM dd")
        val dateTime = LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.of("-5"))
        formatter.format(dateTime)

        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")


        return timeFormatter.format(dateTime)


    }
}


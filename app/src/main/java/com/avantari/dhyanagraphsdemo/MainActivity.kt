package com.avantari.dhyanagraphsdemo

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val minHrArray by lazy {
        ArrayList<Float>().apply {
            add(45f)
            add(50f)
            add(55f)
            add(50f)
            add(40f)
            add(60f)
            add(52f)
        }
    }

    private val maxHrArray by lazy {
        ArrayList<Float>().apply {
            add(75f)
            add(78f)
            add(80f)
            add(90f)
            add(85f)
            add(80f)
            add(70f)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configChart()
        setDataSet()
    }

    private fun configChart() {
        chart.description.isEnabled = false
        chart.setMaxVisibleValueCount(7)

        chart.setPinchZoom(false)
        chart.setScaleEnabled(false)
        chart.setDrawGridBackground(false)

        val xAxis: XAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setLabelCount(7, false)
        xAxis.setDrawGridLines(false)

        val leftAxis: YAxis = chart.axisLeft
//        leftAxis.setEnabled(false);
        //        leftAxis.setEnabled(false);
        leftAxis.setLabelCount(7, false)
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawAxisLine(false)

        val rightAxis: YAxis = chart.axisRight
        rightAxis.isEnabled = false
        chart.legend.isEnabled = false
    }

    private fun setDataSet() {
        val values = ArrayList<CandleEntry>()
        minHrArray.forEachIndexed { index, minHr ->
            val low = minHr
            val heigh = maxHrArray[index]
            values.add(CandleEntry(index.toFloat(), heigh, low, (heigh + low) / 2f, (heigh + low) / 2f, null))
        }

        val set1 = CandleDataSet(values, "Data Set")

        set1.setDrawIcons(false)
        set1.axisDependency = YAxis.AxisDependency.LEFT
        set1.shadowColor = Color.parseColor("#08000000")
        set1.shadowWidth = 1f
        set1.setDrawValues(false)
        set1.setDrawVerticalHighlightIndicator(false)
        set1.setDrawHorizontalHighlightIndicator(false)
        set1.decreasingColor = Color.RED
        set1.decreasingPaintStyle = Paint.Style.FILL
        set1.increasingColor = Color.rgb(122, 242, 84)
        set1.increasingPaintStyle = Paint.Style.STROKE
        set1.neutralColor = Color.TRANSPARENT
        val data = CandleData(set1)

        chart.data = data
        chart.invalidate()
    }
}
package com.avantari.dhyanagraphsdemo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_line_chart.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.random.Random

class LineChartActivity : AppCompatActivity() {
    private val TAG = LineChartActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_chart)
        configChart()
        setDummyData()
    }

    private fun setDummyData() {
        chart.xAxis.textColor = Color.parseColor("#4D000000")
        chart.axisLeft.textColor = Color.parseColor("#4D000000")
        ArrayList<VegalToneModel>().apply {
            repeat(7) {
                val v1 = Random.nextInt(-100, 100).toFloat()
                add(VegalToneModel(0L, v1))
            }
        }.also {
            Log.d(TAG,"First List : ${it.map { it.lfhf_ratio }.toList()}")
            Log.d(TAG,"Second List : ${getFullPoints(it.map { it.lfhf_ratio }).toList()}")
            setChartData(getFullPoints(it.map { it.lfhf_ratio }).mapTo(arrayListOf()) { VegalToneModel(0L,it) })
        }
        chart.data.isHighlightEnabled = false
    }

    private fun configChart() {

        chart.description.isEnabled = false
        chart.isDragEnabled = false
        chart.setTouchEnabled(true)
        chart.setDrawGridBackground(false)
        chart.setScaleEnabled(false)
        chart.setPinchZoom(false)

        chart.setNoDataText("Loading graph...")
        chart.setNoDataTextTypeface(ResourcesCompat.getFont(this, R.font.helvetica_neue))
        chart.setNoDataTextColor(Color.BLACK)


        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
//        xAxis.axisMinimum = 0f
        xAxis.typeface = ResourcesCompat.getFont(this, R.font.helvetica_neue)
        xAxis.textColor = Color.parseColor("#4D000000")
        xAxis.textSize = 7f


        val yAxis = chart.axisLeft
        yAxis.setDrawGridLines(false)
        yAxis.setDrawAxisLine(false)
        yAxis.setDrawZeroLine(true)
        yAxis.typeface = ResourcesCompat.getFont(this, R.font.helvetica_neue)
        yAxis.textColor = Color.parseColor("#4D000000")
        yAxis.textSize = 7f


        chart.axisRight.isEnabled = false
        chart.legend.isEnabled = false

    }

    private fun setChartData(values: ArrayList<VegalToneModel>) {


        chart.xAxis.valueFormatter =
            IndexAxisValueFormatter(listOf("S", "M", "T", "W", "T", "F", "S"))
        chart.xAxis.axisMaximum = values.size.toFloat()
        chart.xAxis.labelCount = values.size
        val maxValue = max(
            abs(values.map { it.lfhf_ratio }.min()?.toDouble() ?: 0.0),
            values.map { it.lfhf_ratio }.max()?.toDouble() ?: 0.0
        ).toFloat()
        chart.axisLeft.axisMinimum = (if (maxValue != 0f) maxValue * -1f else 0f) - 10
        chart.axisLeft.axisMaximum = maxValue + 10


        val entries = ArrayList<Entry>().apply {
            values.forEachIndexed { index, value ->
                add(Entry(index.toFloat(), value.lfhf_ratio))
            }
        }

        val dataSets = ArrayList<ILineDataSet>().apply {
            add(getDataSet(entries))
        }

        chart.data = LineData(dataSets)

        chart.invalidate()
    }

    private fun getDataSet(data: ArrayList<Entry>): LineDataSet {
        return LineDataSet(data, "").apply {
            setDrawIcons(false)
            setDrawCircleHole(false)
            setDrawCircles(false)
            setDrawValues(false)
            setDrawVerticalHighlightIndicator(true)
            setDrawHorizontalHighlightIndicator(false)
            highLightColor = Color.TRANSPARENT
            highlightLineWidth = 1f
            isHighlightEnabled = false
            this.color = Color.parseColor("#F3F6FC")
            lineWidth = 0.2f
            setDrawFilled(true)
            setFillFormatter { _, _ -> 0f }
            fillDrawable =
                ContextCompat.getDrawable(this@LineChartActivity, R.drawable.ic_vagal_graph_lines)
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }
    }

    private fun getFullPoints(inputData: List<Float>): ArrayList<Float> {
        val result = ArrayList<Float>().apply {
            addAll(inputData)
        }
        var index = 0
        while (true) {
            if (index == result.lastIndex)
                break
            if ((result[index] < 0f && result[index + 1] > 0f) || (result[index] > 0f && result[index + 1] < 0f)) {
                result.add(index + 1, 0f)
            }
            index++
        }
        return result
    }

    private fun getRelaxedSegments(inputData: ArrayList<Entry>): ArrayList<ArrayList<Entry>> {
        val segments = ArrayList<ArrayList<Entry>>()
        var startIndex = 0
        while (true) {
            val segment = ArrayList<Entry>()
            if (startIndex == inputData.size) {
                break
            }
            segment.add(inputData[startIndex])
            val temp = startIndex
            loop@ for (i in temp until inputData.size) {
                if (inputData[i].y > 0f) {
                    segment.add(inputData[i])
                    startIndex++
                } else {
                    startIndex++
                    break@loop
                }
            }

            if (segment.size > 1) {
                segments.add(segment)
            }
        }
        return segments
    }

    private fun getPoints(start: Int, end: Int, inTime: Int, outTime: Int): ArrayList<Float> {
        return ArrayList<Float>().apply {
            val diff = end - start
            val inOffset = diff.toFloat() / inTime.toFloat()
            (0 until inTime).forEach {
                add(start + (inOffset * it))
            }

            val outOffset = diff.toFloat() / outTime.toFloat()
            (0..outTime).forEach {
                add(end - (outOffset * it))
            }
        }

    }
}
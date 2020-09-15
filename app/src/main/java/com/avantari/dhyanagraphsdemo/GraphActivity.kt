package com.avantari.dhyanagraphsdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_graph.*
import kotlinx.android.synthetic.main.activity_graph.chart
import kotlinx.android.synthetic.main.activity_line_chart.*
import kotlin.math.abs
import kotlin.math.max

class GraphActivity : AppCompatActivity() {
    private val TAG = GraphActivity::class.java.simpleName
    private val startHr = 70
    private val endHr = 80
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        configChart()
        setChartData(data.map { it.first.toString().toFloat() })

    }

    private fun configChart() {

        chart.description.isEnabled = false
        chart.isDragEnabled = true
        chart.setTouchEnabled(true)
        chart.setDrawGridBackground(false)
        chart.setScaleEnabled(true)
        chart.setPinchZoom(false)

        chart.setNoDataText("Loading graph...")
        chart.setNoDataTextTypeface(ResourcesCompat.getFont(this, R.font.helvetica_neue))
        chart.setNoDataTextColor(Color.BLACK)


        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.axisMinimum = 0f
        xAxis.typeface = ResourcesCompat.getFont(this, R.font.helvetica_neue)
        xAxis.textColor = Color.parseColor("#4D000000")
        xAxis.textSize = 7f


        val yAxis = chart.axisLeft
        yAxis.setDrawGridLines(false)
        yAxis.setDrawAxisLine(false)
        yAxis.typeface = ResourcesCompat.getFont(this, R.font.helvetica_neue)
        yAxis.textColor = Color.parseColor("#4D000000")
        yAxis.textSize = 7f


        chart.axisRight.isEnabled = false
        chart.legend.isEnabled = false

    }

    private fun setChartData(values: List<Float>) {

//        chart.xAxis.axisMinimum = values.min()!!

        val entries = ArrayList<Entry>().apply {
            values.forEachIndexed { index, value ->
                Log.d(TAG, "X,Y : $index,$value")
                add(Entry(index.toFloat(), value))
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
            this.color = Color.BLACK
            lineWidth = 0.2f
//            setDrawFilled(true)
//            setFillFormatter { _, _ -> 0f }
//            fillDrawable =
//                ContextCompat.getDrawable(this@GraphActivity, R.drawable.ic_graph_lines_blue)
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }
    }

    private fun getPoints(
        start: Int,
        end: Int,
        inTime: Int,
        outTime: Int,
        inHold: Int,
        outHold: Int
    ): ArrayList<Float> {
        return ArrayList<Float>().apply {
            val diff = end - start
            val inOffset = diff.toFloat() / inTime.toFloat()
            (0 until inTime).forEach {
                add(start + (inOffset * it))
            }

            (0 until inHold * 10).forEach {
                add(end.toFloat())
            }

            val outOffset = diff.toFloat() / outTime.toFloat()
            (0..outTime).forEach {
                add(end - (outOffset * it))
            }
            (0 until outHold * 10).forEach {
                add(start.toFloat())
            }
        }

    }

    private val data = arrayOf(
        Pair(80, 6),
        Pair(84.086, 7),
        Pair(94.782, 8),
        Pair(108, 9),
        Pair(118.7, 10),
        Pair(122.78, 11),
        Pair(118.7, 12),
        Pair(108, 13),
        Pair(94.782, 14),
        Pair(84.086, 15),
        Pair(80, 16),
        Pair(80, 16),
        Pair(82.866, 17),
        Pair(90.696, 18),
        Pair(101.39, 19),
        Pair(112.09, 20),
        Pair(119.92, 21),
        Pair(122.78, 22),
        Pair(119.92, 23),
        Pair(112.09, 24),
        Pair(101.39, 25),
        Pair(90.696, 26),
        Pair(82.866, 27),
        Pair(80, 28),
        Pair(80, 28),
        Pair(82.119, 29),
        Pair(88.054, 30),
        Pair(96.632, 31),
        Pair(106.15, 32),
        Pair(114.73, 33),
        Pair(120.67, 34),
        Pair(122.78, 35),
        Pair(120.67, 36),
        Pair(114.73, 37),
        Pair(106.15, 38),
        Pair(96.632, 39),
        Pair(88.054, 40),
        Pair(82.119, 41),
        Pair(80, 42),
        Pair(80, 42),
        Pair(82.119, 43),
        Pair(88.054, 44),
        Pair(96.632, 45),
        Pair(106.15, 46),
        Pair(114.73, 47),
        Pair(120.67, 48),
        Pair(122.78, 49),
        Pair(120.67, 50),
        Pair(114.73, 51),
        Pair(106.15, 52),
        Pair(96.632, 53),
        Pair(88.054, 54),
        Pair(82.119, 55),
        Pair(80, 56),
        Pair(80, 56),
        Pair(81.628, 57),
        Pair(86.266, 58),
        Pair(93.206, 59),
        Pair(101.39, 60),
        Pair(109.58, 61),
        Pair(116.52, 62),
        Pair(121.16, 63),
        Pair(122.78, 64),
        Pair(121.16, 65),
        Pair(116.52, 66),
        Pair(109.58, 67),
        Pair(101.39, 68),
        Pair(93.206, 69),
        Pair(86.266, 70),
        Pair(81.628, 71),
        Pair(80, 72),
        Pair(80, 72),
        Pair(81.628, 73),
        Pair(86.266, 74),
        Pair(93.206, 75),
        Pair(101.39, 76),
        Pair(109.58, 77),
        Pair(116.52, 78),
        Pair(121.16, 79),
        Pair(122.78, 80),
        Pair(121.16, 81),
        Pair(116.52, 82),
        Pair(109.58, 83),
        Pair(101.39, 84),
        Pair(93.206, 85),
        Pair(86.266, 86),
        Pair(81.628, 87),
        Pair(80, 88),
        Pair(80, 88),
        Pair(81.29, 89),
        Pair(85.005, 90),
        Pair(90.696, 91),
        Pair(97.678, 92),
        Pair(105.11, 93),
        Pair(112.09, 94),
        Pair(117.78, 95),
        Pair(121.49, 96),
        Pair(122.78, 97),
        Pair(121.49, 98),
        Pair(117.78, 99),
        Pair(112.09, 100),
        Pair(105.11, 101),
        Pair(97.678, 102),
        Pair(90.696, 103),
        Pair(85.005, 104),
        Pair(81.29, 105),
        Pair(80, 106),
        Pair(80, 106),
        Pair(81.29, 107),
        Pair(85.005, 108),
        Pair(90.696, 109),
        Pair(97.678, 110),
        Pair(105.11, 111),
        Pair(112.09, 112),
        Pair(117.78, 113),
        Pair(121.49, 114),
        Pair(122.78, 115),
        Pair(121.49, 116),
        Pair(117.78, 117),
        Pair(112.09, 118),
        Pair(105.11, 119),
        Pair(97.678, 120),
        Pair(90.696, 121),
        Pair(85.005, 122),
        Pair(81.29, 123),
        Pair(80, 124),
        Pair(80, 124),
        Pair(81.628, 125),
        Pair(86.266, 126),
        Pair(93.206, 127),
        Pair(101.39, 128),
        Pair(109.58, 129),
        Pair(116.52, 130),
        Pair(121.16, 131),
        Pair(122.78, 132),
        Pair(121.16, 133),
        Pair(116.52, 134),
        Pair(109.58, 135),
        Pair(101.39, 136),
        Pair(93.206, 137),
        Pair(86.266, 138),
        Pair(81.628, 139),
        Pair(80, 140),
        Pair(80, 140),
        Pair(81.628, 141),
        Pair(86.266, 142),
        Pair(93.206, 143),
        Pair(101.39, 144),
        Pair(109.58, 145),
        Pair(116.52, 146),
        Pair(121.16, 147),
        Pair(122.78, 148),
        Pair(121.16, 149),
        Pair(116.52, 150),
        Pair(109.58, 151),
        Pair(101.39, 152),
        Pair(93.206, 153),
        Pair(86.266, 154),
        Pair(81.628, 155),
        Pair(80, 156),
        Pair(80, 156),
        Pair(82.119, 157),
        Pair(88.054, 158),
        Pair(96.632, 159),
        Pair(106.15, 160),
        Pair(114.73, 161),
        Pair(120.67, 162),
        Pair(122.78, 163),
        Pair(120.67, 164),
        Pair(114.73, 165),
        Pair(106.15, 166),
        Pair(96.632, 167),
        Pair(88.054, 168),
        Pair(82.119, 169),
        Pair(80, 170),
        Pair(80, 170),
        Pair(82.119, 171),
        Pair(88.054, 172),
        Pair(96.632, 173),
        Pair(106.15, 174),
        Pair(114.73, 175),
        Pair(120.67, 176),
        Pair(122.78, 177),
        Pair(120.67, 178),
        Pair(114.73, 179),
        Pair(106.15, 180),
        Pair(96.632, 181),
        Pair(88.054, 182),
        Pair(82.119, 183),
        Pair(80, 184),
        Pair(80, 184),
        Pair(82.866, 185),
        Pair(90.696, 186),
        Pair(101.39, 187),
        Pair(112.09, 188),
        Pair(119.92, 189),
        Pair(122.78, 190),
        Pair(119.92, 191),
        Pair(112.09, 192),
        Pair(101.39, 193),
        Pair(90.696, 194),
        Pair(82.866, 195),
        Pair(80, 196),
        Pair(80, 196),
        Pair(84.086, 197),
        Pair(94.782, 198),
        Pair(108, 199),
        Pair(118.7, 200),
        Pair(122.78, 201),
        Pair(118.7, 202),
        Pair(108, 203),
        Pair(94.782, 204),
        Pair(84.086, 205),
        Pair(80, 206),
        Pair(80, 206),
        Pair(84.086, 207),
        Pair(94.782, 208),
        Pair(108, 209),
        Pair(118.7, 210),
        Pair(122.78, 211),
        Pair(118.7, 212),
        Pair(108, 213),
        Pair(94.782, 214),
        Pair(84.086, 215),
        Pair(80, 216)
    )

    private val relaxData = arrayOf(
        Pair(82.428,6),
        Pair(80.662,7),
        Pair(82.344,8),
        Pair(84.471,9),
        Pair(83.796,10),
        Pair(82.677,11),
        Pair(82.305,12),
        Pair(82.572,13),
        Pair(81.804,14),
        Pair(80.989,15),
        Pair(81.792,16),
        Pair(82.713,16),
        Pair(81.256,17),
        Pair(81.647,18),
        Pair(80.624,19),
        Pair(80.622,20),
        Pair(82.317,21),
        Pair(82.588,22),
        Pair(84.134,23),
        Pair(85.285,24),
        Pair(84.679,25),
        Pair(81.789,26),
        Pair(80.336,27),
        Pair(82.819,28),
        Pair(82.797,28),
        Pair(82.802,29),
        Pair(83.095,30),
        Pair(80.307,31),
        Pair(83.63,32),
        Pair(82.94,33),
        Pair(81.962,34),
        Pair(81.493,35),
        Pair(85.019,36),
        Pair(81.499,37),
        Pair(81.04,38),
        Pair(83.107,39),
        Pair(83.848,40),
        Pair(81.682,41),
        Pair(82.514,42),
        Pair(79.768,42),
        Pair(83.866,43),
        Pair(82.081,44),
        Pair(83.176,45),
        Pair(80.405,46),
        Pair(84.67,47),
        Pair(84.644,48),
        Pair(81.601,49),
        Pair(83.526,50),
        Pair(82.253,51),
        Pair(82.955,52),
        Pair(81.772,53),
        Pair(81.452,54),
        Pair(80.003,55),
        Pair(80.277,56),
        Pair(79.103,56),
        Pair(83.694,57),
        Pair(82.561,58),
        Pair(84.28,59),
        Pair(80.818,60),
        Pair(84.988,61),
        Pair(84.68,62),
        Pair(83.811,63),
        Pair(83.2,64),
        Pair(82.212,65),
        Pair(84.467,66),
        Pair(81.526,67),
        Pair(80.321,68),
        Pair(83.454,69),
        Pair(82.649,70),
        Pair(82.652,71),
        Pair(82.21,72),
        Pair(81.095,72),
        Pair(81.03,73),
        Pair(83.374,74),
        Pair(81.204,75),
        Pair(84.073,76),
        Pair(84.328,77),
        Pair(84.968,78),
        Pair(83.452,79),
        Pair(84.178,80),
        Pair(85.678,81),
        Pair(82.927,82),
        Pair(80.683,83),
        Pair(84.334,84),
        Pair(82.773,85),
        Pair(81.068,86),
        Pair(84.061,87),
        Pair(80.121,88),
        Pair(82.262,88),
        Pair(82.085,89),
        Pair(81.17,90),
        Pair(80.211,91),
        Pair(79.952,92),
        Pair(82.279,93),
        Pair(81.421,94),
        Pair(84.395,95),
        Pair(82.792,96),
        Pair(85.208,97),
        Pair(84.611,98),
        Pair(83.621,99),
        Pair(81.384,100),
        Pair(84.961,101),
        Pair(81.153,102),
        Pair(84.123,103),
        Pair(80.353,104),
        Pair(80.928,105),
        Pair(79.438,106),
        Pair(82.201,106),
        Pair(79.963,107),
        Pair(79.459,108),
        Pair(83.116,109),
        Pair(81.564,110),
        Pair(83.477,111),
        Pair(82.419,112),
        Pair(83.903,113),
        Pair(81.048,114),
        Pair(85.553,115),
        Pair(84.942,116),
        Pair(84.495,117),
        Pair(84.566,118),
        Pair(82.09,119),
        Pair(82.913,120),
        Pair(82.377,121),
        Pair(81.884,122),
        Pair(80.436,123),
        Pair(80.243,124),
        Pair(81.258,124),
        Pair(80.215,125),
        Pair(83.315,126),
        Pair(84.548,127),
        Pair(80.15,128),
        Pair(83.061,129),
        Pair(81.142,130),
        Pair(84.934,131),
        Pair(85.946,132),
        Pair(81.259,133),
        Pair(85.404,134),
        Pair(80.474,135),
        Pair(83.419,136),
        Pair(83.536,137),
        Pair(81.964,138),
        Pair(83.503,139),
        Pair(83.495,140),
        Pair(82.13,140),
        Pair(79.765,141),
        Pair(80.382,142),
        Pair(80.528,143),
        Pair(80.209,144),
        Pair(80.917,145),
        Pair(83.789,146),
        Pair(85.622,147),
        Pair(82.772,148),
        Pair(82.977,149),
        Pair(85.629,150),
        Pair(85.111,151),
        Pair(83.383,152),
        Pair(84.559,153),
        Pair(83.127,154),
        Pair(80.76,155),
        Pair(82.312,156),
        Pair(80.221,156),
        Pair(80.577,157),
        Pair(82.777,158),
        Pair(82.417,159),
        Pair(82.28,160),
        Pair(83.637,161),
        Pair(84.654,162),
        Pair(83.918,163),
        Pair(83.66,164),
        Pair(83.541,165),
        Pair(82.782,166),
        Pair(80.19,167),
        Pair(82.974,168),
        Pair(84.08,169),
        Pair(80.773,170),
        Pair(83.856,170),
        Pair(80.831,171),
        Pair(83.809,172),
        Pair(82.051,173),
        Pair(82.29,174),
        Pair(81.712,175),
        Pair(81.529,176),
        Pair(82.545,177),
        Pair(84.531,178),
        Pair(84.538,179),
        Pair(83.691,180),
        Pair(79.826,181),
        Pair(83.593,182),
        Pair(83.711,183),
        Pair(82.855,184),
        Pair(79.213,184),
        Pair(81.025,185),
        Pair(83.022,186),
        Pair(83.648,187),
        Pair(81.621,188),
        Pair(82.211,189),
        Pair(84.365,190),
        Pair(83.253,191),
        Pair(83.619,192),
        Pair(81.182,193),
        Pair(80.386,194),
        Pair(83.282,195),
        Pair(82.835,196),
        Pair(83.672,196),
        Pair(79.73,197),
        Pair(80.602,198),
        Pair(80.804,199),
        Pair(83.258,200),
        Pair(81.966,201),
        Pair(85.288,202),
        Pair(80.804,203),
        Pair(79.912,204),
        Pair(81.977,205),
        Pair(82.862,206),
        Pair(80.56,206),
        Pair(80.086,207),
        Pair(81.386,208),
        Pair(81.36,209),
        Pair(83.36,210),
        Pair(85.532,211),
        Pair(83.954,212),
        Pair(80.817,213),
        Pair(81.645,214),
        Pair(79.464,215),
        Pair(81.506,216)

    )
}
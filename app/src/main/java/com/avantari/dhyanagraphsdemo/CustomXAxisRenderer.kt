package com.avantari.dhyanagraphsdemo

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas;
import android.graphics.Color
import androidx.core.content.res.ResourcesCompat

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;


class CustomXAxisRenderer(
    val context: Context,
    viewPortHandler: ViewPortHandler?,
    xAxis: XAxis?,
    trans: Transformer?,
    private val boldPosition: Int
) : XAxisRenderer(viewPortHandler, xAxis, trans) {

    override fun drawLabels(c: Canvas?, pos: Float, anchor: MPPointF?) {
        val labelRotationAngleDegrees = mXAxis.labelRotationAngle
        val centeringEnabled = mXAxis.isCenterAxisLabelsEnabled
        val positions = FloatArray(mXAxis.mEntryCount * 2)
        run {
            var i = 0
            while (i < positions.size) {


                // only fill x values
                if (centeringEnabled) {
                    positions[i] = mXAxis.mCenteredEntries[i / 2]
                } else {
                    positions[i] = mXAxis.mEntries[i / 2]
                }
                i += 2
            }
        }
        mTrans.pointValuesToPixel(positions)
        var i = 0
        while (i < positions.size) {
            var x = positions[i]
            if (mViewPortHandler.isInBoundsX(x)) {
                val label =
                    mXAxis.valueFormatter.getFormattedValue(mXAxis.mEntries[i / 2])
                if (mXAxis.mEntries[i / 2].toInt() == boldPosition) {
                    mAxisLabelPaint.color = Color.BLACK
                    mAxisLabelPaint.textSize = 9f.dpToPx()
                    mAxisLabelPaint.typeface =
                        ResourcesCompat.getFont(context, R.font.helvetica_neue_bold)
                } else {
                    mAxisLabelPaint.color = Color.parseColor("#4D000000")
                    mAxisLabelPaint.textSize = 7f.dpToPx()
                    mAxisLabelPaint.typeface =
                        ResourcesCompat.getFont(context, R.font.helvetica_neue)
                }
                if (mXAxis.isAvoidFirstLastClippingEnabled) {

                    // avoid clipping of the last
                    if (i == mXAxis.mEntryCount - 1 && mXAxis.mEntryCount > 1) {
                        val width: Float = Utils.calcTextWidth(mAxisLabelPaint, label).toFloat()
                        if (width > mViewPortHandler.offsetRight() * 2
                            && x + width > mViewPortHandler.chartWidth
                        ) x -= width / 2

                        // avoid clipping of the first
                    } else if (i == 0) {
                        val width: Float = Utils.calcTextWidth(mAxisLabelPaint, label).toFloat()
                        x += width / 2
                    }
                }
                drawLabel(c, label, x, pos, anchor, labelRotationAngleDegrees)
            }
            i += 2
        }
    }
}

fun Float.dpToPx(): Float {
    return ((this * Resources.getSystem().displayMetrics.density))
}
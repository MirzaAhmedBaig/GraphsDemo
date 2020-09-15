package com.avantari.dhyanagraphsdemo

import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class CustomFillFormatter :IFillFormatter{
    override fun getFillLinePosition(
        dataSet: ILineDataSet?,
        dataProvider: LineDataProvider?
    ): Float {
        TODO("Not yet implemented")
    }
}



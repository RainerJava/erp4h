/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ChartUtils.java
*
*     記述				：
*     
*     作成日			：2010/03/23   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.common.utils;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.AbstractXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

/**
 * @author PC13
 *
 */
public class ChartUtils {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ChartUtils() {
	}
	
	public static JPanel getChartPanel(XYDataset dataset) {
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
	
	/**
	* Creates a chart.
	*
	* @param dataset the data for the chart.
	*
	* @return a chart.
	*/
	private static JFreeChart createChart(XYDataset dataset) {
		// create the chart...
		JFreeChart chart = ChartFactory.createXYLineChart(
			"", // chart title
			"", // x axis label
			"", // y axis label
			dataset, // data
			PlotOrientation.VERTICAL,
			true, // include legend
			true, // tooltips
			false // urls
		);
		// get a reference to the plot for further customisation...
		XYPlot plot = (XYPlot) chart.getPlot();
		//plot.setBackgroundPaint(Color.lightGray);
		plot.setAxisOffset(new RectangleInsets(30, 5.0, 5.0, 5.0));
//		plot.setDomainGridlinePaint(Color.white);
//		plot.setRangeGridlinePaint(Color.white);
		
        // add a labelled marker for the bid start price...
        Marker upValue = new ValueMarker(2.0);
        upValue.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        upValue.setPaint(Color.green);
        upValue.setLabel("High value");
        upValue.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        upValue.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(upValue);

        // add a labelled marker for the target price...
        Marker aveValue = new ValueMarker(4.5);
        aveValue.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        aveValue.setPaint(Color.BLUE);
        aveValue.setLabel("Target value");
        aveValue.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        aveValue.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);
        plot.addRangeMarker(aveValue);
        
        Marker lowValue = new ValueMarker(6.0);
        lowValue.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        lowValue.setPaint(Color.red);
        lowValue.setLabel("Low Price");
        lowValue.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        lowValue.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);
        plot.addRangeMarker(lowValue);	       
		
		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
		renderer.setShapesVisible(true);
		renderer.setShapesFilled(true);
		// change the auto tick unit selection to integer units only...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// OPTIONAL CUSTOMISATION COMPLETED.
		renderer.setItemLabelsVisible(true);
		// use one or the other of the following lines to see the
		// different modes for the label generator...
		renderer.setItemLabelGenerator(new LabelGenerator());	
		
		return chart;
	}
	
	/**
	* A custom label generator.
	*/
	static class LabelGenerator extends AbstractXYItemLabelGenerator implements XYItemLabelGenerator {

		/** */
		private static final long serialVersionUID = 1L;

		/**
		* Creates a new label generator that displays the item value and a
		* percentage relative to the value in the same series for the
		* specified category.
		*
		* @param category the category index (zero-based).
		*/
		public LabelGenerator() {
		}
		
		/* (non-Javadoc)
		 * @see org.jfree.chart.labels.XYItemLabelGenerator#generateLabel(org.jfree.data.xy.XYDataset, int, int)
		 */
		public String generateLabel(XYDataset arg0, int arg1, int arg2) {
			
			String result = "";
			
			if (arg0 != null) {
				result = "" + arg0.getYValue(arg1, arg2);
			}
			
			return result;
		}
	}		
}

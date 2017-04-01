package com.ail.crxmarkets.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
public class ChartView implements Serializable {

	private static final long serialVersionUID = 7689588979918046899L;
	private BarChartModel barModel;

	public BarChartModel getBarModel() {
		return barModel;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries surface = new ChartSeries();
		surface.setLabel("Surface");
		surface.set(1, 120);
		surface.set(2, 100);
		surface.set(3, 44);
		surface.set(4, 150);
		surface.set(5, 25);

		ChartSeries water = new ChartSeries();
		water.setLabel("Water");
		water.set(1, 52);
		water.set(2, 60);
		water.set(3, 110);
		water.set(4, 135);
		water.set(5, 120);

		model.addSeries(surface);
		model.addSeries(water);

		model.setStacked(true);

		return model;
	}

	private void createBarModels() {
		createBarModel();
	}

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setTitle("Rainy Hills");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Point");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Height");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

}
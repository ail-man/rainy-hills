package com.ail.crxmarkets.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.exception.ApplicationException;
import com.ail.crxmarkets.jsf.FacesUtils;
import com.ail.crxmarkets.model.Surface;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullTower;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullVessel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "mbRainyHills")
@ViewScoped
public class MbRainyHills {

	@SuppressWarnings("WeakerAccess")
	public static final String PAGE_NAME = "rainyHills";

	private static final String CHART_SERIES_SURFACE_LABEL = "Surface";
	private static final String CHART_SERIES_SURFACE_COLOR = "EAA228";
	private static final String CHART_SERIES_WATER_LABEL = "Water";
	private static final String CHART_SERIES_WATER_COLOR = "4BB2C5";
	private static final String BAR_MODEL_TITLE = "Rainy Hills";
	private static final String BAR_MODEL_LEGEND_POSITION = "ne";
	private static final String BAR_MODEL_X_LABEL = "Point";
	private static final String BAR_MODEL_Y_LABEL = "Height";

	private static final Logger log = LoggerFactory.getLogger(MbMain.class);

	private BarChartModel stackedVerticalModel;
	private Surface surface;
	private long calculationTime;
	private int surfaceLength;
	private int surfaceMinHeight;
	private int surfaceMaxHeight;
	private CalculationMethod calculationMethod;
	private String textArea;

	// TODO реализовать неоптимизированный алгоритм методом башен
	// TODO подключить Spring (под вопросом)
	// TODO подключить логинку с JAAS
	@PostConstruct
	public void init() {
		log.debug("Init MBean {} success", this.getClass().getName());
		surfaceLength = 1;
		surfaceMinHeight = 0;
		surfaceMaxHeight = 0;
		calculationMethod = CalculationMethod.VESSEL;
		surface = Surface.random(surfaceLength, surfaceMinHeight, surfaceMaxHeight);
		updateBarModel();
	}

	private void updateBarModel() {
		stackedVerticalModel = new BarChartModel();
		stackedVerticalModel.setSeriesColors(CHART_SERIES_SURFACE_COLOR + "," + CHART_SERIES_WATER_COLOR);

		ChartSeries surfaceChartSeries = new ChartSeries();
		surfaceChartSeries.setLabel(CHART_SERIES_SURFACE_LABEL);
		for (int i = 0; i < surface.getSurface().length; i++) {
			surfaceChartSeries.set(i + 1, surface.getSurface()[i]);
		}

		ChartSeries waterChartSeries = new ChartSeries();
		waterChartSeries.setLabel(CHART_SERIES_WATER_LABEL);
		for (int i = 0; i < surface.getWater().length; i++) {
			waterChartSeries.set(i, surface.getWater()[i]);
		}

		stackedVerticalModel.addSeries(surfaceChartSeries);
		stackedVerticalModel.addSeries(waterChartSeries);

		stackedVerticalModel.setStacked(true);

		stackedVerticalModel.setBarMargin(0);
		stackedVerticalModel.setBarPadding(0);

		stackedVerticalModel.setTitle(BAR_MODEL_TITLE);
		stackedVerticalModel.setLegendPosition(BAR_MODEL_LEGEND_POSITION);

		Axis xAxis = stackedVerticalModel.getAxis(AxisType.X);
		xAxis.setLabel(BAR_MODEL_X_LABEL);

		Axis yAxis = stackedVerticalModel.getAxis(AxisType.Y);
		yAxis.setLabel(BAR_MODEL_Y_LABEL);
		yAxis.setMin(0);
		yAxis.setMax(surfaceMaxHeight);
	}

	public void generate() {
		surface = Surface.random(surfaceLength, surfaceMinHeight, surfaceMaxHeight);
		textArea = Utils.printAsText(surface.getSurface());
		updateBarModel();
	}

	public void calculate() {
		long startTime = System.nanoTime();
		surface.fillWater(getWaterFillMethod(), null);
		calculationTime = System.nanoTime() - startTime;
		updateBarModel();
	}

	public void draw() {
		try {
			int[] surfArr = Utils.parseIntArray(textArea);
			surface = new Surface(surfArr);
			surfaceLength = surfArr.length;
			surfaceMaxHeight = Utils.max(surfArr);
		} catch (ApplicationException e) {
			FacesUtils.error(e.getMessage());
		}
		updateBarModel();
	}

	private WaterFillMethod getWaterFillMethod() {
		switch (calculationMethod) {
		case VESSEL:
			return new WFMFullVessel();
		case TOWER:
			return new WFMFullTower();
		default:
			return new WFMFullTower();
		}
	}

	public BarChartModel getStackedVerticalModel() {
		return stackedVerticalModel;
	}

	public long getTotalWaterAmount() {
		return surface.getTotalWater();
	}

	public long getCalcuationTime() {
		return calculationTime;
	}

	public int getSurfaceLength() {
		return surfaceLength;
	}

	public void setSurfaceLength(int surfaceLength) {
		this.surfaceLength = surfaceLength;
	}

	public int getSurfaceMinHeight() {
		return surfaceMinHeight;
	}

	public void setSurfaceMinHeight(int surfaceMinHeight) {
		this.surfaceMinHeight = surfaceMinHeight;
	}

	public int getSurfaceMaxHeight() {
		return surfaceMaxHeight;
	}

	public void setSurfaceMaxHeight(int surfaceMaxHeight) {
		this.surfaceMaxHeight = surfaceMaxHeight;
	}

	public CalculationMethod getCalculationMethod() {
		return calculationMethod;
	}

	public void setCalculationMethod(CalculationMethod calculationMethod) {
		this.calculationMethod = calculationMethod;
	}

	public String getTextArea() {
		return textArea;
	}

	public void setTextArea(String textArea) {
		this.textArea = textArea;
	}
}

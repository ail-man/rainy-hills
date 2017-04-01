package com.ail.crxmarkets.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.exception.ApplicationException;
import com.ail.crxmarkets.jsf.FacesUtils;
import com.ail.crxmarkets.model.Surface;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullTowerOptimized;
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

	private static final Logger log = LoggerFactory.getLogger(MbMain.class);

	private static final String CHART_SERIES_SURFACE_LABEL = "Surface";
	private static final String CHART_SERIES_SURFACE_COLOR = "EAA228";
	private static final String CHART_SERIES_WATER_LABEL = "Water";
	private static final String CHART_SERIES_WATER_COLOR = "4BB2C5";
	private static final String BAR_MODEL_TITLE = "Rainy Hills";
	private static final String BAR_MODEL_LEGEND_POSITION = "ne";
	private static final String BAR_MODEL_X_LABEL = "Point";
	private static final String BAR_MODEL_Y_LABEL = "Height";
	private static final String TICK_FORMAT = "%d";
	private static final int BAR_MARGIN = 0;
	private static final int BAR_PADDING = 0;
	private static final int DEFAULT_SURFACE_LENGTH = 50;
	private static final int DEFAULT_SURFACE_MIN_HEIGHT = 0;
	private static final int DEFAULT_SURFACE_MAX_HEIGHT = 200;

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
		surfaceLength = DEFAULT_SURFACE_LENGTH;
		surfaceMinHeight = DEFAULT_SURFACE_MIN_HEIGHT;
		surfaceMaxHeight = DEFAULT_SURFACE_MAX_HEIGHT;
		calculationMethod = CalculationMethod.VESSEL;
		surface = Surface.random(surfaceLength, 0, 0);
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

		stackedVerticalModel.setBarMargin(BAR_MARGIN);
		stackedVerticalModel.setBarPadding(BAR_PADDING);

		stackedVerticalModel.setTitle(BAR_MODEL_TITLE);
		stackedVerticalModel.setLegendPosition(BAR_MODEL_LEGEND_POSITION);

		Axis xAxis = stackedVerticalModel.getAxis(AxisType.X);
		xAxis.setLabel(BAR_MODEL_X_LABEL);

		Axis yAxis = stackedVerticalModel.getAxis(AxisType.Y);
		yAxis.setLabel(BAR_MODEL_Y_LABEL);
		yAxis.setMin(0);
		yAxis.setMax(surfaceMaxHeight);
		yAxis.setTickFormat(TICK_FORMAT);
	}

	public void generate() {
		surface = Surface.random(surfaceLength, surfaceMinHeight, surfaceMaxHeight);
		surfaceLength = surface.getSurface().length;
		surfaceMaxHeight = Utils.max(surface.getSurface());
		textArea = Utils.printAsText(surface.getSurface());
		updateBarModel();
	}

	public void calculate() {
		long startTime = System.nanoTime();
		surface.fillWater(getWaterFillMethod(), null);
		calculationTime = System.nanoTime() - startTime;

		surfaceLength = surface.getSurface().length;
		surfaceMaxHeight = Utils.max(surface.getSurface());
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
			return new WFMFullTowerOptimized();
		case TOWER_OPTIMIZED:
			return new WFMFullTowerOptimized();
		default:
			return new WFMFullVessel();
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

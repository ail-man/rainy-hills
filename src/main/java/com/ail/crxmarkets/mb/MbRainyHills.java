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
	private static final int DEFAULT_BAR_CHART_LENGTH = 20;
	private static final int DEFAULT_BAR_CHART_HEIGHT = 10;
	private static final int BAR_MARGIN = 0;
	private static final int BAR_PADDING = 0;
	private static final int DEFAULT_LENGTH_SLIDER = 30;
	private static final int DEFAULT_SURFACE_MIN_HEIGHT_SLIDER = -10;
	private static final int DEFAULT_SURFACE_MAX_HEIGHT_SLIDER = 10;

	private String textArea;
	private int surfaceLengthSlider;
	private int surfaceMinHeightSlider;
	private int surfaceMaxHeightSlider;
	private CalculationMethod calculationMethod;
	private Surface surface;
	private BarChartModel stackedVerticalModel;
	private long calculationTime;

	// TODO negative values in graph
	// TODO Global ExceptionHandler
	// TODO ResourceBundles for i18n in java code
	// TODO translate Javadoc
	// TODO check thread safety of Surface class
	// TODO update README.md by adding task description with screenshots
	@PostConstruct
	public void init() {
		log.debug("Init MBean {} success", this.getClass().getName());
		surfaceLengthSlider = DEFAULT_LENGTH_SLIDER;
		surfaceMinHeightSlider = DEFAULT_SURFACE_MIN_HEIGHT_SLIDER;
		surfaceMaxHeightSlider = DEFAULT_SURFACE_MAX_HEIGHT_SLIDER;
		calculationMethod = CalculationMethod.VESSEL;
		surface = Surface.random(DEFAULT_BAR_CHART_LENGTH, 0, 0);
		updateBarModel(true);
	}

	public void draw() {
		try {
			int[] surfArr = Utils.parseIntArray(textArea);
			surface = new Surface(surfArr);
			textArea = Utils.printAsText(surfArr);
			updateBarModel(false);
		} catch (ApplicationException e) {
			FacesUtils.error(e.getMessage());
		}
	}

	public void generate() {
		try {
			surface = Surface.random(surfaceLengthSlider, surfaceMinHeightSlider, surfaceMaxHeightSlider);
			textArea = Utils.printAsText(surface.getSurface());
			updateBarModel(false);
		} catch (ApplicationException e) {
			FacesUtils.error(e.getMessage());
		}
	}

	public void calculate() {
		try {
			long startTime = System.nanoTime();
			surface.fillWater(getWaterFillMethod(), null);
			calculationTime = System.nanoTime() - startTime;
			updateBarModel(false);
		} catch (ApplicationException e) {
			FacesUtils.error(e.getMessage());
		}
	}

	private void updateBarModel(boolean firstInit) {
		stackedVerticalModel = new BarChartModel();
		stackedVerticalModel.setSeriesColors(CHART_SERIES_SURFACE_COLOR + "," + CHART_SERIES_WATER_COLOR);
		stackedVerticalModel.setNegativeSeriesColors(CHART_SERIES_SURFACE_COLOR + "," + CHART_SERIES_WATER_COLOR);
		stackedVerticalModel.setStacked(true);
		stackedVerticalModel.setBarMargin(BAR_MARGIN);
		stackedVerticalModel.setBarPadding(BAR_PADDING);
		stackedVerticalModel.setTitle(BAR_MODEL_TITLE);
		stackedVerticalModel.setLegendPosition(BAR_MODEL_LEGEND_POSITION);
		stackedVerticalModel.setShadow(false);

		ChartSeries surfaceChartSeries = new ChartSeries();
		surfaceChartSeries.setLabel(CHART_SERIES_SURFACE_LABEL);

		int[] surArr = surface.getSurface();
		for (int i = 0; i < surArr.length; i++) {
			surfaceChartSeries.set(i + 1, surArr[i]);
		}

		ChartSeries waterChartSeries = new ChartSeries();
		waterChartSeries.setLabel(CHART_SERIES_WATER_LABEL);

		int[] watArr = surface.getWater();
		for (int i = 0; i < watArr.length; i++) {
			waterChartSeries.set(i, watArr[i]);
		}

		stackedVerticalModel.addSeries(surfaceChartSeries);
		stackedVerticalModel.addSeries(waterChartSeries);

		Axis xAxis = stackedVerticalModel.getAxis(AxisType.X);
		xAxis.setLabel(BAR_MODEL_X_LABEL);

		Axis yAxis = stackedVerticalModel.getAxis(AxisType.Y);
		yAxis.setLabel(BAR_MODEL_Y_LABEL);
		if (firstInit) {
			yAxis.setMax(DEFAULT_BAR_CHART_HEIGHT);
		}
	}

	private WaterFillMethod getWaterFillMethod() {
		switch (calculationMethod) {
		case VESSEL:
			return new WFMFullVessel();
		case TOWER:
			return new WFMFullTower();
		case TOWER_OPTIMIZED:
			return new WFMFullTowerOptimized();
		default:
			return new WFMFullVessel();
		}
	}

	public String getTextArea() {
		return textArea;
	}

	public void setTextArea(String textArea) {
		this.textArea = textArea;
	}

	public int getSurfaceLengthSlider() {
		return surfaceLengthSlider;
	}

	public void setSurfaceLengthSlider(int surfaceLengthSlider) {
		this.surfaceLengthSlider = surfaceLengthSlider;
	}

	public int getSurfaceMinHeightSlider() {
		return surfaceMinHeightSlider;
	}

	public void setSurfaceMinHeightSlider(int surfaceMinHeightSlider) {
		this.surfaceMinHeightSlider = surfaceMinHeightSlider;
	}

	public int getSurfaceMaxHeightSlider() {
		return surfaceMaxHeightSlider;
	}

	public void setSurfaceMaxHeightSlider(int surfaceMaxHeightSlider) {
		this.surfaceMaxHeightSlider = surfaceMaxHeightSlider;
	}

	public CalculationMethod getCalculationMethod() {
		return calculationMethod;
	}

	public void setCalculationMethod(CalculationMethod calculationMethod) {
		this.calculationMethod = calculationMethod;
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

	private enum CalculationMethod {
		VESSEL, TOWER, TOWER_OPTIMIZED
	}

}

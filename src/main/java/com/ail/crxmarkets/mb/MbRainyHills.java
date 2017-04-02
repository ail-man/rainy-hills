package com.ail.crxmarkets.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.exception.ApplicationException;
import com.ail.crxmarkets.jsf.FacesUtils;
import com.ail.crxmarkets.jsf.ResourceBundles;
import com.ail.crxmarkets.model.Surface;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullTower;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullTowerOptimized;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullVessel;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "mbRainyHills")
@ViewScoped
public class MbRainyHills {

	@SuppressWarnings("WeakerAccess")
	public static final String PAGE_NAME = "rainyHills";

	private static final Logger log = LoggerFactory.getLogger(MbMain.class);

	private static final String CHART_TITLE = "Rainy Hills";
	private static final String CHART_LEGEND_POSITION = "ne";
	private static final boolean CHART_SHOW_POINT_LABELS = true;
	private static final boolean CHART_SHOW_SHADOW = false;
	private static final boolean CHART_ZOOM = true;
	private static final String CHART_SERIES_SURFACE_LABEL = "Surface";
	private static final String CHART_SERIES_SURFACE_COLOR = "EAA228";
	private static final String CHART_SERIES_WATER_LABEL = "Water";
	private static final String CHART_SERIES_WATER_COLOR = "4BB2C5";
	private static final String X_AXIS_LABEL = "Point";
	private static final String Y_AXIS_LABEL = "Height";
	private static final int DEFAULT_CHART_LENGTH = 10;
	private static final int DEFAULT_CHART_HEIGHT = 10;
	private static final int DEFAULT_LENGTH_SLIDER = 30;
	private static final int DEFAULT_SURFACE_MIN_HEIGHT_SLIDER = -10;
	private static final int DEFAULT_SURFACE_MAX_HEIGHT_SLIDER = 10;

	private String textArea;
	private int surfaceLengthSlider;
	private int surfaceMinHeightSlider;
	private int surfaceMaxHeightSlider;
	private CalculationMethod calculationMethod;
	private Surface surface;
	private LineChartModel lineChartModel;
	private long calculationTime;

	// TODO check thread safety of Surface class
	// TODO translate Javadoc
	// TODO update README.md by adding task description with screenshots
	@PostConstruct
	public void init() {
		log.debug("Init MBean {} success", this.getClass().getName());
		surfaceLengthSlider = DEFAULT_LENGTH_SLIDER;
		surfaceMinHeightSlider = DEFAULT_SURFACE_MIN_HEIGHT_SLIDER;
		surfaceMaxHeightSlider = DEFAULT_SURFACE_MAX_HEIGHT_SLIDER;
		calculationMethod = CalculationMethod.VESSEL;
		surface = Surface.random(DEFAULT_CHART_LENGTH, 0, 0);
		drawSurfaceGraphic(true);
	}

	public void draw() {
		if (StringUtils.isBlank(textArea)) {
			FacesUtils.warn(FacesUtils.getlocalizedString(ResourceBundles.RAINY_HILLS, "enterIntegersByCommas"));
			return;
		}

		try {
			int[] surfArr = Utils.parseIntArray(textArea);
			surface = new Surface(surfArr);
			textArea = Utils.printAsText(surfArr);
			drawSurfaceGraphic(false);
		} catch (ApplicationException e) {
			FacesUtils.error(e.getMessage());
		}
	}

	public void generate() {
		try {
			surface = Surface.random(surfaceLengthSlider, surfaceMinHeightSlider, surfaceMaxHeightSlider);
			textArea = Utils.printAsText(surface.getSurface());
			drawSurfaceGraphic(false);
		} catch (ApplicationException e) {
			FacesUtils.error(e.getMessage());
		}
	}

	public void calculate() {
		try {
			long startTime = System.nanoTime();
			surface.fillWater(getWaterFillMethod(), null);
			calculationTime = System.nanoTime() - startTime;
			drawSurfaceWithWaterGraphic();
		} catch (ApplicationException e) {
			FacesUtils.error(e.getMessage());
		}
	}

	private void drawSurfaceGraphic(boolean firstInit) {
		initLineCharModel(firstInit);

		lineChartModel.setSeriesColors(CHART_SERIES_SURFACE_COLOR);

		LineChartSeries surfaceChartSeries = new LineChartSeries();
		surfaceChartSeries.setLabel(CHART_SERIES_SURFACE_LABEL);
		int[] surArr = this.surface.getSurface();
		for (int i = 0; i < surArr.length; i++) {
			surfaceChartSeries.set(i, surArr[i]);
		}

		lineChartModel.addSeries(surfaceChartSeries);
	}

	private void drawSurfaceWithWaterGraphic() {
		initLineCharModel(false);

		lineChartModel.setSeriesColors(CHART_SERIES_SURFACE_COLOR + "," + CHART_SERIES_WATER_COLOR);

		LineChartSeries surfaceChartSeries = new LineChartSeries();
		surfaceChartSeries.setLabel(CHART_SERIES_SURFACE_LABEL);

		int[] surArr = surface.getSurface();
		for (int i = 0; i < surArr.length; i++) {
			surfaceChartSeries.set(i, surArr[i]);
		}

		LineChartSeries waterChartSeries = new LineChartSeries();
		waterChartSeries.setLabel(CHART_SERIES_WATER_LABEL);

		int[] watArr = surface.getWater();
		for (int i = 0; i < watArr.length; i++) {
			waterChartSeries.set(i, surArr[i] + watArr[i]);
		}

		lineChartModel.addSeries(surfaceChartSeries);
		lineChartModel.addSeries(waterChartSeries);
	}

	private void initLineCharModel(boolean firstInit) {
		lineChartModel = new LineChartModel();
		lineChartModel.setTitle(CHART_TITLE);
		lineChartModel.setLegendPosition(CHART_LEGEND_POSITION);
		lineChartModel.setShowPointLabels(CHART_SHOW_POINT_LABELS);
		lineChartModel.setShadow(CHART_SHOW_SHADOW);
		lineChartModel.setZoom(CHART_ZOOM);

		Axis xAxis = lineChartModel.getAxis(AxisType.X);
		xAxis.setLabel(X_AXIS_LABEL);

		Axis yAxis = lineChartModel.getAxis(AxisType.Y);
		yAxis.setLabel(Y_AXIS_LABEL);

		if (firstInit) {
			yAxis.setMax(DEFAULT_CHART_HEIGHT);
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

	public LineChartModel getLineChartModel() {
		return lineChartModel;
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

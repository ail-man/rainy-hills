package com.ail.crxmarkets.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ail.crxmarkets.ejb.RainyHillsEjbLocal;
import com.ail.crxmarkets.jsf.FacesUtils;
import com.ail.crxmarkets.model.Surface;
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

	static final String PAGE_NAME = "rainyHills";

	private static final String CHART_SERIES_WATER = "Water";
	private static final String CHART_SERIES_SURFACE = "Surface";
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
	@EJB
	private RainyHillsEjbLocal rainyHillsEjbLocal;

	@PostConstruct
	public void init() {
		log.debug("Init MBean {} success", this.getClass().getName());
		createBarModel();
	}

	private void createBarModel() {
		stackedVerticalModel = new BarChartModel();

		surface = Surface.random(20, 0, 20);

		ChartSeries surfaceChartSeries = new ChartSeries();
		surfaceChartSeries.setLabel(CHART_SERIES_SURFACE);

		for (int i = 0; i < surface.getSurface().length; i++) {
			surfaceChartSeries.set(i + 1, surface.getSurface()[i]);
		}

		long startTime = System.nanoTime();
		surface.fillWater(new WFMFullVessel(), null);
		calculationTime = System.nanoTime() - startTime;

		ChartSeries waterChartSeries = new ChartSeries();
		waterChartSeries.setLabel(CHART_SERIES_WATER);
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
		yAxis.setMax(25);
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

	public void calculate() {
		createBarModel();
		FacesUtils.error("TEFGADGGAEGA");
	}

	public void generate() {
		createBarModel();
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
}

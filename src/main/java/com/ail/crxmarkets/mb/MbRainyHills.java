package com.ail.crxmarkets.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ail.crxmarkets.ejb.RainyHillsEjbLocal;
import com.ail.crxmarkets.model.Surface;
import com.ail.crxmarkets.waterfill.WFMFullVessel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "mbRainyHills")
@ViewScoped
public class MbRainyHills {

	public static final String PAGE_NAME = "rainyHills";

	private static final String BAR_MODEL_TITLE = "Rainy Hills";
	private static final String BAR_MODEL_LEGEND_POSITION = "ne";
	private static final String BAR_MODEL_X_LABEL = "Point";

	private static final String BAR_MODEL_Y_LABEL = "Height";
	private static final Logger log = LoggerFactory.getLogger(MbMain.class);

	private BarChartModel stackedVerticalModel;

	@EJB
	private RainyHillsEjbLocal rainyHillsEjbLocal;

	@PostConstruct
	public void init() {
		log.debug("Init MBean {} success", this.getClass().getName());
		createBarModel();
	}
	//
	//	public void calculate() {
	//		log.debug("{}.calculate() invoked", this.getClass().getName());
	//		rainyHillsEjbLocal.calcWaterOnSurface(new int[] { 1 }, 2);
	//	}

	private void createBarModel() {
		stackedVerticalModel = new BarChartModel();

		Surface surface = Surface.random(100, 0, 100);

		ChartSeries surfaceChartSeries = new ChartSeries();
		surfaceChartSeries.setLabel("Surface");

		for (int i = 0; i < surface.getSurface().length; i++) {
			surfaceChartSeries.set(i, surface.getSurface()[i]);
		}

		surface.fillWithWater(new WFMFullVessel(), null);

		ChartSeries waterChartSeries = new ChartSeries();
		waterChartSeries.setLabel("Water");
		for (int i = 0; i < surface.getWater().length; i++) {
			waterChartSeries.set(i, surface.getWater()[i]);
		}

		stackedVerticalModel.addSeries(surfaceChartSeries);
		stackedVerticalModel.addSeries(waterChartSeries);

		stackedVerticalModel.setStacked(true);

		stackedVerticalModel.setBarMargin(1);
		stackedVerticalModel.setBarPadding(1);

		stackedVerticalModel.setTitle("Rainy Hills");
		stackedVerticalModel.setLegendPosition("ne");

		Axis xAxis = stackedVerticalModel.getAxis(AxisType.X);
		xAxis.setLabel("Point");

		Axis yAxis = stackedVerticalModel.getAxis(AxisType.Y);
		yAxis.setLabel("Height");
		yAxis.setMin(0);
		yAxis.setMax(100);
	}

	public BarChartModel getStackedVerticalModel() {
		return stackedVerticalModel;
	}
}

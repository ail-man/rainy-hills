package com.ail.crxmarkets.histogram;

public interface Histogram {

	void drawHistogram(int[] surface);

	void drawDoubleLayerHistogram(int[] surface, int[] water);

}

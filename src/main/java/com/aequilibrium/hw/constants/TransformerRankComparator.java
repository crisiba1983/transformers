package com.aequilibrium.hw.constants;

import java.util.Comparator;

import com.aequilibrium.hw.model.TransformerTO;

public class TransformerRankComparator implements Comparator<TransformerTO>{

	@Override
	public int compare(TransformerTO o1, TransformerTO o2) {
		return (-1)*o1.getRank().compareTo(o2.getRank());
	}
}
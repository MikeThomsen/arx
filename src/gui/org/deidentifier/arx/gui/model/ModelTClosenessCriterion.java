package org.deidentifier.arx.gui.model;

import org.deidentifier.arx.criteria.EqualDistanceTCloseness;
import org.deidentifier.arx.criteria.HierarchicalDistanceTCloseness;
import org.deidentifier.arx.criteria.PrivacyCriterion;

public class ModelTClosenessCriterion extends ModelExplicitCriterion{

	private static final long serialVersionUID = 4901053938589514626L;
	public ModelTClosenessCriterion(String attribute) {
		super(attribute);
	}
	private int variant = 0;
	private double t = 0.001d;
	public int getVariant() {
		return variant;
	}
	public void setVariant(int variant) {
		this.variant = variant;
	}
	public double getT() {
		return t;
	}
	public void setT(double t) {
		this.t = t;
	}

	@Override
	public PrivacyCriterion getCriterion(Model model) {
		if (variant == 0) {
			return new EqualDistanceTCloseness(getAttribute(), t);
		} else if (variant == 1) {
			return new HierarchicalDistanceTCloseness(getAttribute(), t,
					model.getInputConfig().getHierarchy(getAttribute()));
		} else {
			throw new RuntimeException(
					"Internal error: invalid variant of t-closeness");
		}
	}

}

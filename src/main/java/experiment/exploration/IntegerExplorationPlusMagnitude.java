package experiment.exploration;

import experiment.Runner;
import perturbation.perturbator.AddNPerturbatorImpl;

import java.util.ArrayList;

/**
 * Created by beyni on 01/05/16.
 */
public class IntegerExplorationPlusMagnitude extends ExplorationImpl {

    private int[] magnitudes;

    public IntegerExplorationPlusMagnitude(int ...magnitudes) {
        if (magnitudes.length > 0)
            this.magnitudes = magnitudes;
        else
            this.magnitudes = new int[]{1,2,5,10,20,50};

        super.names = new String[this.magnitudes.length];
        perturbators = new ArrayList<>();

        super.columnName = "Magnitude";

        super.name = "AddM";
        super.header = "Exploration Plus Magnitude\n";
        super.header += "magnitude value : ";

        for (int indexMagnitude = 0 ; indexMagnitude < this.magnitudes.length ; indexMagnitude++) {
            perturbators.add(new AddNPerturbatorImpl(this.magnitudes[indexMagnitude]));
            super.names[indexMagnitude] = "" + this.magnitudes[indexMagnitude];
            super.header +=  this.magnitudes[indexMagnitude] + " ";
        }

        super.header += "\n";
        super.header += Runner.locations.size() + " perturbation point\n";
        super.header += "PM : Numerical Perturbator\n";
    }
}
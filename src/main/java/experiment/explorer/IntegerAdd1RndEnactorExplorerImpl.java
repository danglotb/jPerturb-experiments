package experiment.explorer;

import experiment.Logger;
import experiment.Runner;
import experiment.Tuple;
import experiment.Util;
import perturbation.PerturbationEngine;
import perturbation.enactor.NeverEnactorImpl;
import perturbation.enactor.RandomEnactorImpl;
import perturbation.location.PerturbationLocation;
import perturbation.log.LoggerImpl;
import perturbation.perturbator.NothingPerturbatorImpl;
import perturbation.perturbator.Perturbator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spirals on 05/04/16.
 */
@Deprecated
public class IntegerAdd1RndEnactorExplorerImpl implements Explorer {

    protected float[] randomRates = new float[]{0.001f, 0.005f, 0.01f, 0.05f, 0.1f, 0.5f ,0.9f};

    protected int seedOfRandomEnactor = 32;

    protected String header;

    protected String path;

    private Map<PerturbationLocation, RandomEnactorImpl>[] enactorsOfLocationPerRandomRates;

    protected String perturbatorName;

    private int[][][] nbOfCallsPerLocationPerTaskPerRates;

    protected int repeat;

    private Perturbator perturbator;

    public IntegerAdd1RndEnactorExplorerImpl(Perturbator perturbatorTobeUsed, float... randomRates) {
        this(perturbatorTobeUsed, 5, randomRates);
    }

    public IntegerAdd1RndEnactorExplorerImpl(Perturbator perturbatorTobeUsed, int repeat, float... randomRates) {

        if (randomRates.length > 0)
            this.randomRates = randomRates;

        this.perturbator = perturbatorTobeUsed;

        this.repeat = repeat;

        enactorsOfLocationPerRandomRates = new Map[this.randomRates.length];

        nbOfCallsPerLocationPerTaskPerRates = new int[Runner.locations.size()][Runner.numberOfTask][this.randomRates.length];

        Logger.init(Runner.locations.size(),Runner.numberOfTask, this.randomRates.length, 5);

        header = "Exploration Random\n";
        header += "random Rates : ";

        for (int indexOfRandomRate = 0; indexOfRandomRate < this.randomRates.length; indexOfRandomRate++) {
            header += this.randomRates[indexOfRandomRate] + " ";
            enactorsOfLocationPerRandomRates[indexOfRandomRate] = new HashMap<>();
            for (PerturbationLocation location : Runner.locations) {
                enactorsOfLocationPerRandomRates[indexOfRandomRate].put(location, new RandomEnactorImpl(seedOfRandomEnactor, this.randomRates[indexOfRandomRate]));
            }
        }
        perturbatorName = "PONE : Numerical Perturbator";

        header += "\n" + Runner.locations.size() + " perturbation point\n";
        header += repeat + " repetition of each point of each task\n";
        header +=  perturbatorName + ", Random Enactor, seed :" + seedOfRandomEnactor + "\n";

        path = "IntegerAdd1RndEnactorExplorer";

        PerturbationEngine.loggers.put(path, new LoggerImpl());
    }

    @Override
    public void run(int indexOfTask, PerturbationLocation location) {
        location.setPerturbator(this.perturbator);
        for (int indexOfRandomRate = 0; indexOfRandomRate < randomRates.length; indexOfRandomRate++) {
            PerturbationEngine.loggers.get(path).logOn(location);
            Tuple result = runRandomRate(indexOfTask, location, indexOfRandomRate);
            Tuple resultWithLog = new Tuple(5);
            resultWithLog = resultWithLog.add(result);
            resultWithLog.set(3, PerturbationEngine.loggers.get(path).getCalls(location));
            resultWithLog.set(4, PerturbationEngine.loggers.get(path).getEnactions(location));
            Logger.add(Runner.locations.indexOf(location), indexOfTask, 0, indexOfRandomRate, resultWithLog);
            PerturbationEngine.loggers.get(path).reset();
        }
        location.setPerturbator(new NothingPerturbatorImpl());
        location.setEnactor(new NeverEnactorImpl());
    }

    private Tuple runRandomRate(int indexOfTask, PerturbationLocation location, int indexOfrandomRate) {
        location.setEnactor(enactorsOfLocationPerRandomRates[indexOfrandomRate].get(location));
        Tuple result = new Tuple(3);
        for (int i = 0 ; i < repeat ; i++) {
            nbOfCallsPerLocationPerTaskPerRates[Runner.locations.indexOf(location)][indexOfTask][indexOfrandomRate]++;
            result = result.add(Runner.runPerturbation(indexOfTask));
        }
        return result;
    }

    @Override
    public void log() {

        List<PerturbationLocation> locationExceptionFragile = new ArrayList<>();
        List<PerturbationLocation> locationOracleFragile = new ArrayList<>();
        List<PerturbationLocation> locationAntiFragile = new ArrayList<>();
        List<PerturbationLocation> locationSuperAntiFragile = new ArrayList<>();

        Tuple [][][][] results = Logger.getResults();

        try {
            /* All Log */
            FileWriter writer = new FileWriter("results/" + Runner.manager.getPath() + "/" + path + "_detail.txt", false);
            String format = "%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-14s %-27s";
            writer.write( "detail per task and per random rate.\n" + header + Runner.manager.getHeader());
            writer.write(String.format(format, "Task", "RandomRate", "IndexLoc", "#Success", "#Failure",
                    "#Exception", "#Call", "#Perturbation","%Success") + "\n");
            for (int indexTask = 0; indexTask < Runner.numberOfTask; indexTask++) {
                for (PerturbationLocation location : Runner.locations) {
                    for (int indexRandomRates = 0; indexRandomRates < randomRates.length; indexRandomRates++) {
                        Tuple result = results[Runner.locations.indexOf(location)][indexTask][0][indexRandomRates];
                        double avg = (double) result.get(4) / (double)nbOfCallsPerLocationPerTaskPerRates[Runner.locations.indexOf(location)][indexTask][indexRandomRates];
                        writer.write(String.format(format, indexTask, randomRates[indexRandomRates], location.getLocationIndex(),
                                result.get(0), result.get(1), result.get(2), result.get(3), result.get(4),
                                result.get(4)==0?"NaN": Util.getStringPerc(result.get(0), result.total(3))) + "\n");
                    }
                }
            }
            writer.close();

            /* Sum Arrays */
            writer = new FileWriter("results/" + Runner.manager.getPath() + "/" + path + "_random_rates_analysis_graph_data.txt", false);
            writer.write("contains the data for the random rates analysis graph.\n"+ header + Runner.manager.getHeader());
            format = "%-10s %-10s %-10s %-10s %-10s %-10s %-14s %-27s";
            writer.write(String.format(format, "RandomRate", "IndexLoc",
                    "#Success", "#Failure", "#Exception",
                    "#Call", "#Perturbation",
                    "%Success") + "\n");
            for (PerturbationLocation location : Runner.locations) {
                Tuple resultForLocation = new Tuple(3);
                for (int indexRandomRates = 0; indexRandomRates < randomRates.length; indexRandomRates++) {
                    Tuple result = new Tuple(5);
                    for (int indexTask = 0; indexTask < Runner.numberOfTask ; indexTask++)
                        result = result.add(results[Runner.locations.indexOf(location)][indexTask][0][indexRandomRates]);

                    writer.write(String.format(format,
                            randomRates[indexRandomRates], location.getLocationIndex(),
                            result.get(0), result.get(1), result.get(2),
                            result.get(3), result.get(4),
                            result.get(4)==0?"NaN":Util.getStringPerc(result.get(0), result.total(3))) + "\n");

                    resultForLocation = resultForLocation.add(result);
                }
                Explorer.addToFragilityList(resultForLocation, resultForLocation.total(), location, locationExceptionFragile,locationSuperAntiFragile,
                        locationAntiFragile, locationOracleFragile);
            }
            writer.close();

            String title = "SERN\n";
            title += Runner.locations.size() + " perturbation point\n";
            title += "Random Enactor, seed :" + seedOfRandomEnactor + "\n";
            title += perturbatorName + "\n";
            title += this.repeat + " repetition for each location for each task\n";

            format = "%-10s %-10s %-10s %-10s %-18s %-18s %-14s %-24s %-10s %-10s %-27s";
            for (int indexRandomRates = 0; indexRandomRates < randomRates.length; indexRandomRates++) {
                /* Sum PerturbationPoint */
                writer = new FileWriter("results/" + Runner.manager.getPath() + "/" + path + "_per_location_" + randomRates[indexRandomRates] + ".txt", false);
                writer.write("aggregate data per location for random rate = " + randomRates[indexRandomRates] + "\n" + title + Runner.manager.getHeader());
                writer.write(String.format(format,
                        "IndexLoc", "#Success", "#Failure", "#Exception",
                        "#CallAllExecs", "AvgCallPerExec",
                        "#Perturbations", "AvgPerturbationPerExec",
                        "#Execs", "#Tasks", "%Success") + "\n");

                for (PerturbationLocation location : Runner.locations) {
                    Tuple result = new Tuple(5);
                    int accNbOfTasks = 0;
                    for (int indexTask = 0; indexTask < Runner.numberOfTask ; indexTask++) {
                        result = result.add(results[Runner.locations.indexOf(location)][indexTask][0][indexRandomRates]);
                        accNbOfTasks += nbOfCallsPerLocationPerTaskPerRates[Runner.locations.indexOf(location)][indexTask][indexRandomRates];
                    }
                    double avgCalls = ((double)result.get(3) / (double) accNbOfTasks);
                    double avgPerturbation = (double) result.get(4) / (double) accNbOfTasks;

                    writer.write(String.format(format,
                            location.getLocationIndex(), result.get(0), result.get(1), result.get(2), //results
                            result.get(3), String.format("%.2f", avgCalls), // Calls
                            result.get(4), String.format("%.2f", avgPerturbation), //Perturbations
                            accNbOfTasks, Runner.numberOfTask,//Execs Task
                            result.get(4)==0?"NaN":Util.getStringPerc(result.get(0), result.total(3))) + "\n");//Percentage success
                }
                writer.close();
            }

            Explorer.writeListOnGivenFile("results/" + Runner.manager.getPath() + "/" + path + "_anti_fragile.txt",
                    "List of ids antifragile points.", locationAntiFragile);
            Explorer.writeListOnGivenFile("results/" + Runner.manager.getPath() + "/" + path + "_super_anti_fragile.txt",
                    "List of ids antifragile points.", locationSuperAntiFragile);
            Explorer.writeListOnGivenFile("results/" + Runner.manager.getPath() + "/" + path + "_oracle_fragile.txt",
                    "list ids of oracle fragile code : >" + Explorer.TOLERANCE +"% of oracle failures", locationOracleFragile);
            Explorer.writeListOnGivenFile("results/" + Runner.manager.getPath() + "/" + path + "_exception_fragile.txt",
                    "list ids of exception fragile code : >" + Explorer.TOLERANCE +"% of exceptions.", locationExceptionFragile);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
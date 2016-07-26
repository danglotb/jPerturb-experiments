package zip;

import experiment.CallableImpl;
import experiment.ManagerImpl;
import experiment.Oracle;

import java.util.Arrays;
import java.util.List;

/**
 * Created by spirals on 19/04/16.
 */
public class ZipManager extends ManagerImpl<String,String> {

    public ZipManager(int numberOfTask, int size) {
        this(numberOfTask, size, 23);
    }

    public ZipManager(int numberOfTask, int size, int seed) {
        super(23);
        //LOCATIONS TODO
        super.initialize(numberOfTask, size);
    }


    @Override
    protected String generateOneTask() {
        String string = "";
        for (int i = 0; i < super.sizeOfTask ; i++) {
            string += ((char)randomForGenTask.nextInt(256));
        }
        return string;
    }

    @Override
    public CallableImpl<String, String> getCallable(String input) {
        return new ZipCallableImpl(input);
    }

    @Override
    public Oracle<String, String> getOracle() {
        return new ZipOracle();
    }

    @Override
    public String getName() {
        return "zip";
    }

    @Override
    public String getHeader() {
        return super.indexTasks.size()+ " string of " + super.sizeOfTask + " char\n" +
                "Random char generated with " + seedForGenTask + " as seed\n" +
                super.locations.size() + " perturbation points\n";
    }

    @Override
    public List<List<Integer>> getLists() {
        List<Integer> antifragileIndex = Arrays.asList(3, 5, 8, 20, 22);
        List<Integer> robustIndex = Arrays.asList(0, 1, 2, 7, 15, 17, 18, 19, 26, 29, 23);
        List<Integer> weakIndex = Arrays.asList(6, 10, 16, 23, 25, 4, 8, 19, 21);
        return Arrays.asList(antifragileIndex, robustIndex,weakIndex);
    }

    @Override
    public String getTask(int indexOfTask) {
        return new String(super.tasks.get(indexOfTask));
    }
}

package mersenne;

import experiment.CallableImpl;
import experiment.ManagerImpl;
import experiment.Oracle;

import java.util.Arrays;
import java.util.List;

/**
 * Created by spirals on 19/04/16.
 */
public class MersenneManager extends ManagerImpl<Long, List<Long>> {

    public MersenneManager(int numberOfTask, int size) {
        this(numberOfTask, size, 23);
    }

    public MersenneManager(int numberOfTask, int size, int seed) {
        super(seed);
        //LOCATIONS TODO
        super.initialize(numberOfTask, size);
    }

    @Override
    protected Long generateOneTask() {
        return super.randomForGenTask.nextLong();
    }

    @Override
    public Long getTask(int index) {
        return new Long(super.tasks.get(index));
    }

    @Override
    public Oracle<Long, List<Long>> getOracle() {
        return new MersenneOracle();
    }

    @Override
    public CallableImpl<Long, List<Long>> getCallable(Long input) {
        return new MersenneCallableImpl(input, super.sizeOfTask);
    }

    @Override
    public List<List<Integer>> getLists() {
        List<Integer> antifragileIndex = Arrays.asList(0, 1, 2, 6, 10, 11, 12, 22, 46, 48, 51, 52, 53, 54, 58, 59, 60, 61, 62, 63, 64, 66, 67, 68, 69, 70, 71, 72, 73, 76, 77, 79, 81, 82, 83, 84, 85, 86, 87, 88, 90, 92, 93, 94, 95, 96, 97, 98, 99, 100, 103, 104, 107, 112, 113, 117, 118, 120, 50);
        List<Integer> robustIndex = Arrays.asList(4, 5, 7, 9, 15, 16, 17, 18, 19, 47, 49, 55, 56, 57, 65, 78, 80, 89, 91, 101, 102, 105, 13);
        List<Integer> weakIndex = Arrays.asList(21, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 74, 75, 106, 108, 109, 110, 111, 114, 115, 116, 119, 121, 122, 123, 8, 20);
        return Arrays.asList(antifragileIndex, robustIndex,weakIndex);
    }

    @Override
    public String getName() {
        return "mersenne";
    }

    @Override
    public String getHeader() {
        return super.indexTasks.size() + " list of " + super.sizeOfTask + " number\n"
                +"Random numbers generated with " + seedForGenTask + " as seed\n" +
                super.locations.size() + " perturbation points\n";
    }
}

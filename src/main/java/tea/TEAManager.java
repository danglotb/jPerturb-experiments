package tea;

import experiment.CallableImpl;
import experiment.ManagerImpl;
import experiment.Oracle;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bdanglot on 24/05/16.
 */
public class TEAManager extends ManagerImpl<int[], int[]> {

    private final int [] key = new int[]{0xDEADBEE1, 0xDEADBEE2, 0xDEADBEE3, 0xDEADBEE4};

    public TEAManager(int numberOfTask, int size) {
        this(numberOfTask, size, 23);
    }

    public TEAManager(int numberOfTask, int size, int seed) {
        super(seed);
        //LOCATIONS TODO
        super.initialize(numberOfTask, size);
    }

    @Override
    protected int[] generateOneTask() {
        return new int[] {randomForGenTask.nextInt(0x7FFFFFFF), randomForGenTask.nextInt(0x7FFFFFFF)};
    }

    @Override
    public CallableImpl<int[], int[]> getCallable(int[] input) {
        return new CallableImpl<int[], int[]>(input) {
            @Override
            public int[] call() throws Exception {
                return TEAInstr.decrypt(TEAInstr.encrypt(input, key), key);
            }
        };
    }

    @Override
    public Oracle<int[], int[]> getOracle() {
        return (in, out) -> in[0] == out[0] && in[1] == out[1];
    }

    @Override
    public String getName() {
        return "tea";
    }

    @Override
    public String getHeader() {
        return super.indexTasks.size() + " string of " + super.sizeOfTask + " char\nRandom char generated with " + seedForGenTask + " as seed\n" +
                super.locations.size() + " perturbation points\n";
    }

    @Override
    public List<List<Integer>> getLists() {
        List<Integer> antifragileIndex = Arrays.asList();
        List<Integer> robustIndex = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 27, 42, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 81, 96, 102, 103, 104, 105);
        List<Integer> weakIndex = Arrays.asList(18, 19, 20, 21, 22, 23, 24, 25, 26, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 43, 44, 45, 46, 47, 48, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 97, 98, 99, 100, 101, 17, 70);
        return Arrays.asList(antifragileIndex, robustIndex,weakIndex);
    }

    @Override
    public int[] getTask(int indexOfTask) {
        return new int[]{super.tasks.get(indexOfTask)[0],super.tasks.get(indexOfTask)[1]};
    }
}

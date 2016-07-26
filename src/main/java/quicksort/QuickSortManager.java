package quicksort;

import experiment.CallableImpl;
import experiment.ManagerImpl;
import experiment.Oracle;

import java.util.Arrays;
import java.util.List;

/**
 * Created by spirals on 21/05/16.
 */
public class QuickSortManager extends ManagerImpl<int[], int[]> {

    public QuickSortManager(int nbTask, int sizeTask) {
        this(nbTask, sizeTask, 23);
    }

    public QuickSortManager(int nbTask, int sizeTask, int seed){
        super(seed);
        this.locations = QuickSortInstr.getLocations();
        super.initialize(nbTask, sizeTask);
    }

    @Override
    protected int[] generateOneTask() {
        int [] task = new int[super.sizeOfTask];
        for (int i = 0; i < super.sizeOfTask ; i++)
            task[i] = randomForGenTask.nextInt();
        return task;
    }

    @Override
    public int[] getTask(int indexTask) {
        int [] clone = new int[super.sizeOfTask];
        System.arraycopy(super.tasks.get(indexTask), 0, clone, 0, super.sizeOfTask);
        return clone;
    }

    @Override
    public List<List<Integer>> getLists() {
        List<Integer> antifragileIndex = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 10, 11, 14, 17, 20, 22, 23, 28, 29, 31, 34, 35, 12);
        List<Integer> robustIndex = Arrays.asList(9, 15, 19);
        List<Integer> weakIndex = Arrays.asList(0, 1, 13, 18, 24, 26, 27, 30, 33, 36, 38, 39, 40, 41, 42, 43, 44, 45, 46, 16, 21, 25, 32, 37);
        return Arrays.asList(antifragileIndex, robustIndex,weakIndex);
    }

    @Override
    public CallableImpl<int[], int[]> getCallable(int[] input) {
        return new CallableImpl<int[], int[]>(input) {
            @Override
            public int[] call() throws Exception {
                QuickSortInstr.sort(this.input, 0, this.input.length-1);
                return this.input;
            }
        };
    }

    @Override
    public Oracle<int[], int[]> getOracle() {
        return new QuickSortOracle();
    }

    @Override
    public String getName() {
        return "quicksort";
    }

    @Override
    public String getHeader() {
        return super.indexTasks.size() + " arrays of " + super.sizeOfTask + " integers\n" +
                "Random integer generated with " + super.seedForGenTask + " as seed\n" +
                super.locations.size() + " perturbations points\n";
    }

}
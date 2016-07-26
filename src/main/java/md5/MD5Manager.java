package md5;

import experiment.CallableImpl;
import experiment.ManagerImpl;
import experiment.Oracle;

import java.util.Arrays;
import java.util.List;

/**
 * Created by spirals on 19/04/16.
 */
public class MD5Manager extends ManagerImpl<String, byte[]> {

    public MD5Manager(int numberOfTask, int size) {
        this(numberOfTask, size, 23);

    }

    public MD5Manager(int numberOfTask, int size, int seed) {
        super(seed);
        super.initialize(numberOfTask, size);
    }

    @Override
    protected String generateOneTask() {
        String string = "";
        for (int i = 0 ; i < super.sizeOfTask ; i++) {
            string += ((char)randomForGenTask.nextInt(256));
        }
        return string;
    }

    @Override
    public CallableImpl<String, byte[]> getCallable(String input) {
        return new MD5CallableImpl(input);
    }

    @Override
    public String getName() {
        return "md5";
    }

    @Override
    public String getHeader() {
        return super.indexTasks.size() + " string of " + super.sizeOfTask + " char\nRandom char generated with " + seedForGenTask + " as seed\n" +
                super.locations.size() + " perturbation points\n";
    }

    @Override
    public String getTask(int index) {
        return new String(super.tasks.get(index));
    }

    @Override
    public List<List<Integer>> getLists() {
        List<Integer> antifragileIndex = Arrays.asList(1, 2, 3, 20, 34, 64, 79);
        List<Integer> robustIndex = Arrays.asList(0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 29, 30, 31, 32, 33, 35, 36, 37, 49, 50, 146, 147, 148, 149, 150, 152, 153, 155, 156, 157, 159, 160, 163, 164);
        List<Integer> weakIndex = Arrays.asList(23, 24, 25, 26, 27, 28, 39, 40, 41, 42, 43, 44, 46, 47, 48, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 76, 77, 78, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 161, 165, 166, 167, 168, 169, 170, 172, 173, 22, 38, 45, 51, 75, 151, 154, 158, 162, 171);
        return Arrays.asList(antifragileIndex, robustIndex, weakIndex);
    }

    @Override
    public Oracle<String, byte[]> getOracle() {
        return new MD5Oracle();
    }
}

package oracle;

import experiment.Manager;
import experiment.Oracle;
import md5.MD5;
import md5.MD5Oracle;
import mersenne.MersenneOracle;
import org.junit.Test;
import quicksort.QuickSortOracle;
import sudoku.Sudoku;
import sudoku.SudokuManager;
import sudoku.SudokuOracle;
import zip.LZW;
import zip.ZipOracle;

import java.util.*;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by bdanglot on 09/05/16.
 */
public class TestOracle {

    private int[] clone(int [] array) {
        int [] clone = new int [array.length];
        System.arraycopy(array, 0, clone, 0, array.length);
        return clone;
    }

    private List<Integer> clone(List<Integer> lst) {
        List<Integer> clone = new ArrayList<>();
        clone.addAll(lst);
        return clone;
    }

    @Test
    public void testQuickSort() throws Exception {

        //Test the quicksort oracle by sorting, shuffling, adding and removing elements.
        //The input is cloned as the OracleManager does when it use get(int index) method.

        //TODO
        Oracle<int[], int[]> oracle = new QuickSortOracle();

        Random rnd = new Random();//w/e the seed used, it has to work
        final int[] input = new int[100];//100 elements
        for (int i = 0; i < input.length; i++) {
            input[i] = rnd.nextInt();
        }

        int [] test = clone(input);

        assertFalse(oracle.assertPerturbation(clone(input), test));
        Arrays.sort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
        assertTrue(oracle.assertPerturbation(clone(input), test));

//        Oracle<List<Integer>, List<Integer>> oracle = new QuickSortOracle();
//
//        Random rnd = new Random();
//        final List<Integer> input =  new ArrayList<>();
//        for (int i = 0 ; i < 100 ; i ++) {
//            input.add(rnd.nextInt());
//        }
//
//        List<Integer> listTest = clone(input);
//
//        //list is not sorted
//        assertFalse(oracle.assertPerturbation(clone(input), listTest));
//        //sorting
//        Collections.sort(listTest);
//        assertTrue(oracle.assertPerturbation(clone(input), listTest));
//        //stateless
//        Collections.shuffle(listTest);
//        assertFalse(oracle.assertPerturbation(clone(input), listTest));
//        //the list is no longer as the input, it has one more element
//        listTest.add(1);// add an element
//        Collections.sort(listTest);
//        assertFalse(oracle.assertPerturbation(clone(input), listTest));
//        listTest.clear();
//        listTest.addAll(input);
//        //as before, but one less element
//        listTest.remove(1);//removing one element
//        Collections.sort(listTest);
//        assertFalse(oracle.assertPerturbation(clone(input), listTest));
    }

    @Test
    public void testMersenne() throws Exception {

        //test the mersenne oracle with a given seed

        long seed = 23l;

        mersenne.MersenneTwister mt = new mersenne.MersenneTwister(seed);

        Oracle<Long, List<Long>> oracle = new MersenneOracle();

        final List<Long> output = new ArrayList<>();
        for (int i = 0 ; i < 100 ; i++)
            output.add(mt.genrand());

        List<Long> outputTest = new ArrayList<>();
        outputTest.addAll(output);
        assertTrue(oracle.assertPerturbation(seed, outputTest));
        outputTest.add(1L);
        assertFalse(oracle.assertPerturbation(seed, outputTest));
        outputTest.clear();
        outputTest.addAll(output);
        outputTest.remove(0);
        assertFalse(oracle.assertPerturbation(seed, outputTest));

        mt = new mersenne.MersenneTwister(32l);

        for (int i = 0 ; i < 100 ; i++)
            output.add(mt.genrand());

        outputTest = new ArrayList<>();
        outputTest.addAll(output);

        //wrong seed
        assertFalse(oracle.assertPerturbation(seed, outputTest));
    }

    @Test
    public void testMd5() throws Exception {

        Oracle<String,byte[]> oracle = new MD5Oracle();

        final String input = "this sentence will be hash";
        String inputTest = new String(input);
        byte [] output = MD5.computeMD5(inputTest.getBytes());
        assertTrue(oracle.assertPerturbation(input, output));
        output[0] = 0;
        assertFalse(oracle.assertPerturbation(input, output));
        inputTest = new String(input + " ");
        output = MD5.computeMD5(inputTest.getBytes());
        assertFalse(oracle.assertPerturbation(input, output));
        inputTest = new String(input.substring(1));
        output = MD5.computeMD5(inputTest.getBytes());
        assertFalse(oracle.assertPerturbation(input, output));
    }

    @Test
    public void testZip() throws Exception {

        Oracle<String,String> oracle = new ZipOracle();

        final String input = "this sentence will be zip and unzip";
        String output = LZW.decompress(LZW.compress(new String(input)));
        assertTrue(oracle.assertPerturbation(input, output));
        output = LZW.decompress(LZW.compress(new String(input + " ")));
        assertFalse(oracle.assertPerturbation(input, output));
        output = LZW.decompress(LZW.compress(new String(input.substring(1))));
        assertFalse(oracle.assertPerturbation(input, output));
        output = LZW.decompress(LZW.compress(new String(input)));
        assertFalse(oracle.assertPerturbation(input, output + " "));
        assertFalse(oracle.assertPerturbation(input, output.substring(1)));

    }

    @Test
    public void testSudoku() throws Exception {

        Oracle<int[][],int[][]> oracle = new SudokuOracle();
        Manager<int[][], int[][]> manager = new SudokuManager(2,40);

        int[][] input = manager.getTask(0);
        Sudoku sdk = new Sudoku(input);
        sdk.initSubsets();
        sdk.solve();
        int[][] output = sdk.getGrid();
        assertTrue(oracle.assertPerturbation(input, output));

        assertFalse(oracle.assertPerturbation(manager.getTask(1), output));

        output[0][0] = 0;
        assertFalse(oracle.assertPerturbation(input, output));

    }

    //    @Test
//    public void testClassifier() throws Exception {
//
//        OracleManager<Experiment> manager = new BayesManager();
//        Oracle<Experiment, InstancesResultListener> oracle = (BayesOracle)manager.getOracle();
//        Experiment input = manager.get(0);
//        input.initialize();
//        input.runExperiment();
//        input.postProcess();
//        assertTrue(oracle.assertPerturbation(manager.get(0), (InstancesResultListener) input.getResultListener()));
//
//
//    }
}

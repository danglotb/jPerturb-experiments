package sudoku;

import experiment.CallableImpl;
import experiment.ManagerImpl;
import experiment.Oracle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by spirals on 19/04/16.
 */
public class SudokuManager extends ManagerImpl<int[][],int[][]> {

    private List<int[][]> grids;

    private BufferedReader br;

    private static final String PATH_TO_GRID_FILE = "resources/sudoku/grid/grid.txt";

    public SudokuManager(int numberOfTask, int size) {
        this(numberOfTask, size, 23);
    }

    public SudokuManager(int numberOfTask, int size, int seed) {
        super(seed);
        //LOCATIONS TODO
        super.initialize(numberOfTask, size);
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        grids = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(PATH_TO_GRID_FILE));
            while (br.readLine() != null) {//Trash Header
                int[][] grid = new int[9][9];
                for (int row = 0; row < 9; row++) {
                    String rowAsStr = br.readLine();
                    for (int col = 0; col < rowAsStr.length(); col++) {
                        grid[row][col] = Integer.parseInt(rowAsStr.charAt(col) + "");
                    }
                }
                grids.add(grid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int[][] generateOneTask() {
        if (br == null)
            readFile();

        int[][] grid = grids.get(super.indexTasks.size() % grids.size());
        int nbCellToErase = 10 + (int) (super.sizeOfTask * 0.1f);
        for (int i = 0; i < nbCellToErase; i++) {
            int indexToErase = 0;
            do {
                indexToErase = randomForGenTask.nextInt(81);
            } while (grid[indexToErase % 9][(indexToErase) / 9] == 0);
            grid[indexToErase % 9][indexToErase / 9] = 0;
        }
        return grid;
    }


    @Override
    public int[][] getTask(int index) {
        int[][] clone = new int[9][9];
        int[][] originalValue = super.tasks.get(index);
        for (int row = 0; row < originalValue.length; row++) {
            for (int col = 0; col < originalValue[row].length; col++) {
                clone[row][col] = originalValue[row][col];
            }
        }
        return clone;
    }

    @Override
    public CallableImpl<int[][], int[][]> getCallable(int[][] input) {
        return new SudokuCallableImpl(input);
    }

    @Override
    public String getName() {
        return "sudoku";
    }

    @Override
    public String getHeader() {
        return super.indexTasks.size() + " sudoku grid \n"
                + "Those grid are read from file in resources/sudoku/grid.txt\n"+
                super.locations.size() + " perturbations points\n";
    }

    @Override
    public List<List<Integer>> getLists() {
		List<Integer> antifragileIndex = Arrays.asList(74, 75, 76, 77, 78, 79, 81, 85, 16);
		List<Integer> robustIndex = Arrays.asList(14, 15, 20, 21, 35, 36, 55, 56, 57, 59, 60, 61, 80, 82, 84, 23, 48, 58, 62, 102, 107, 114);
		List<Integer> weakIndex = Arrays.asList(0, 1, 3, 4, 7, 8, 9, 10, 17, 18, 24, 25, 26, 27, 29, 30, 31, 32, 34, 38, 39, 40, 42, 43, 44, 45, 46, 47, 49, 50, 51, 52, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 86, 88, 89, 90, 91, 92, 94, 95, 96, 98, 99, 100, 101, 103, 104, 105, 106, 108, 109, 110, 111, 112, 113, 2, 5, 6, 11, 12, 13, 19, 22, 28, 33, 37, 41, 53, 54, 83, 87, 93, 97);
        return Arrays.asList(antifragileIndex, robustIndex,weakIndex);
    }

    @Override
    public Oracle<int[][], int[][]> getOracle() {
        return new SudokuOracle();
    }

}

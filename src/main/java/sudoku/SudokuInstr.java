

package sudoku;

import perturbation.PerturbationEngine;
import perturbation.location.PerturbationLocation;
import perturbation.location.PerturbationLocationImpl;

public class SudokuInstr {
    static {
        SudokuInstr.initPerturbationLocation0();
    }

    public static PerturbationLocation __L0;

    public static PerturbationLocation __L1;

    public static PerturbationLocation __L2;

    public static PerturbationLocation __L3;

    public static PerturbationLocation __L4;

    public static PerturbationLocation __L5;

    public static PerturbationLocation __L6;

    public static PerturbationLocation __L7;

    public static PerturbationLocation __L8;

    public static PerturbationLocation __L9;

    public static PerturbationLocation __L10;

    public static PerturbationLocation __L11;

    public static PerturbationLocation __L12;

    public static PerturbationLocation __L13;

    public static PerturbationLocation __L14;

    public static PerturbationLocation __L15;

    public static PerturbationLocation __L16;

    public static PerturbationLocation __L17;

    public static PerturbationLocation __L18;

    public static PerturbationLocation __L19;

    public static PerturbationLocation __L20;

    public static PerturbationLocation __L21;

    public static PerturbationLocation __L22;

    public static PerturbationLocation __L23;

    public static PerturbationLocation __L24;

    public static PerturbationLocation __L25;

    public static PerturbationLocation __L26;

    public static PerturbationLocation __L27;

    public static PerturbationLocation __L28;

    public static PerturbationLocation __L29;

    public static PerturbationLocation __L30;

    public static PerturbationLocation __L31;

    public static PerturbationLocation __L32;

    public static PerturbationLocation __L33;

    public static PerturbationLocation __L34;

    public static PerturbationLocation __L35;

    public static PerturbationLocation __L36;

    public static PerturbationLocation __L37;

    public static PerturbationLocation __L38;

    public static PerturbationLocation __L39;

    public static PerturbationLocation __L40;

    public static PerturbationLocation __L41;

    public static PerturbationLocation __L42;

    public static PerturbationLocation __L43;

    public static PerturbationLocation __L44;

    public static PerturbationLocation __L45;

    public static PerturbationLocation __L46;

    public static PerturbationLocation __L47;

    public static PerturbationLocation __L48;

    public static PerturbationLocation __L49;

    public static PerturbationLocation __L50;

    public static PerturbationLocation __L51;

    public static PerturbationLocation __L52;

    public static PerturbationLocation __L53;

    public static PerturbationLocation __L54;

    public static PerturbationLocation __L55;

    public static PerturbationLocation __L56;

    public static PerturbationLocation __L57;

    public static PerturbationLocation __L58;

    public static PerturbationLocation __L59;

    public static PerturbationLocation __L60;

    public static PerturbationLocation __L61;

    public static PerturbationLocation __L62;

    public static PerturbationLocation __L63;

    public static PerturbationLocation __L64;

    public static PerturbationLocation __L65;

    public static PerturbationLocation __L66;

    public static PerturbationLocation __L67;

    public static PerturbationLocation __L68;

    public static PerturbationLocation __L69;

    public static PerturbationLocation __L70;

    public static PerturbationLocation __L71;

    public static PerturbationLocation __L72;

    public static PerturbationLocation __L73;

    public static PerturbationLocation __L74;

    public static PerturbationLocation __L75;

    public static PerturbationLocation __L76;

    public static PerturbationLocation __L77;

    public static PerturbationLocation __L78;

    public static PerturbationLocation __L79;

    public static PerturbationLocation __L80;

    public static PerturbationLocation __L81;

    public static PerturbationLocation __L82;

    public static PerturbationLocation __L83;

    public static PerturbationLocation __L84;

    public static PerturbationLocation __L85;

    public static PerturbationLocation __L86;

    public static PerturbationLocation __L87;

    public static PerturbationLocation __L88;

    public static PerturbationLocation __L89;

    public static PerturbationLocation __L90;

    public static PerturbationLocation __L91;

    public static PerturbationLocation __L92;

    public static PerturbationLocation __L93;

    public static PerturbationLocation __L94;

    public static PerturbationLocation __L95;

    public static PerturbationLocation __L96;

    public static PerturbationLocation __L97;

    public static PerturbationLocation __L98;

    public static PerturbationLocation __L99;

    public static PerturbationLocation __L100;

    public static PerturbationLocation __L101;

    public static PerturbationLocation __L102;

    public static PerturbationLocation __L103;

    public static PerturbationLocation __L104;

    public static PerturbationLocation __L105;

    public static PerturbationLocation __L106;

    public static PerturbationLocation __L107;

    public static PerturbationLocation __L108;

    public static PerturbationLocation __L109;

    public static PerturbationLocation __L110;

    public static PerturbationLocation __L111;

    public static PerturbationLocation __L112;

    public static PerturbationLocation __L113;

    public static PerturbationLocation __L114;

    public static PerturbationLocation __L115;

    public static PerturbationLocation __L116;

    public static PerturbationLocation __L117;

    public static PerturbationLocation __L118;

    public static PerturbationLocation __L119;

    public static PerturbationLocation __L120;

    private int[][] mBoard;

    private int mBoardSize;

    private int mBoxSize;

    private boolean[][] mRowSubset;

    private boolean[][] mColSubset;

    private boolean[][] mBoxSubset;

    public SudokuInstr(int[][] board) {
        mBoard = board;
        mBoardSize = mBoard.length;
        mBoxSize = ((int) (Math.sqrt(mBoardSize)));
    }

    public int[][] getGrid() {
        return SudokuInstr.this.mBoard;
    }

    public void initSubsets() {
        mRowSubset = new boolean[PerturbationEngine.pint(__L78, mBoardSize)][PerturbationEngine.pint(__L79, mBoardSize)];
        mColSubset = new boolean[PerturbationEngine.pint(__L80, mBoardSize)][PerturbationEngine.pint(__L81, mBoardSize)];
        mBoxSubset = new boolean[PerturbationEngine.pint(__L82, mBoardSize)][PerturbationEngine.pint(__L83, mBoardSize)];
        for (int i = PerturbationEngine.pint(__L84, 0); PerturbationEngine.pboolean(__L87, ((PerturbationEngine.pint(__L85, i)) < (PerturbationEngine.pint(__L86, mBoard.length)))); PerturbationEngine.pint(__L88, (i++))) {
            for (int j = PerturbationEngine.pint(__L89, 0); PerturbationEngine.pboolean(__L92, ((PerturbationEngine.pint(__L90, j)) < (PerturbationEngine.pint(__L91, mBoard.length)))); PerturbationEngine.pint(__L93, (j++))) {
                int value = PerturbationEngine.pint(__L96, mBoard[PerturbationEngine.pint(__L94, i)][PerturbationEngine.pint(__L95, j)]);
                if (PerturbationEngine.pboolean(__L99, ((PerturbationEngine.pint(__L97, value)) != (PerturbationEngine.pint(__L98, 0))))) {
                    setSubsetValue(PerturbationEngine.pint(__L100, i), PerturbationEngine.pint(__L101, j), PerturbationEngine.pint(__L102, value), PerturbationEngine.pboolean(__L103, true));
                } 
            }
        }
    }

    private void setSubsetValue(int i, int j, int value, boolean present) {
        mRowSubset[PerturbationEngine.pint(__L104, i)][PerturbationEngine.pint(__L107, ((PerturbationEngine.pint(__L105, value)) - (PerturbationEngine.pint(__L106, 1))))] = PerturbationEngine.pboolean(__L108, present);
        mColSubset[PerturbationEngine.pint(__L109, j)][PerturbationEngine.pint(__L112, ((PerturbationEngine.pint(__L110, value)) - (PerturbationEngine.pint(__L111, 1))))] = PerturbationEngine.pboolean(__L113, present);
        mBoxSubset[PerturbationEngine.pint(__L116, computeBoxNo(PerturbationEngine.pint(__L114, i), PerturbationEngine.pint(__L115, j)))][PerturbationEngine.pint(__L119, ((PerturbationEngine.pint(__L117, value)) - (PerturbationEngine.pint(__L118, 1))))] = PerturbationEngine.pboolean(__L120, present);
    }

    public boolean solve() {
        return PerturbationEngine.pboolean(__L18, solve(PerturbationEngine.pint(__L16, 0), PerturbationEngine.pint(__L17, 0)));
    }

    public boolean solve(int i, int j) {
        if (PerturbationEngine.pboolean(__L21, ((PerturbationEngine.pint(__L19, i)) == (PerturbationEngine.pint(__L20, mBoardSize))))) {
            i = PerturbationEngine.pint(__L22, 0);
            if (PerturbationEngine.pboolean(__L25, ((PerturbationEngine.pint(__L23, (++j))) == (PerturbationEngine.pint(__L24, mBoardSize))))) {
                return PerturbationEngine.pboolean(__L26, true);
            } 
        } 
        if (PerturbationEngine.pboolean(__L31, ((PerturbationEngine.pint(__L29, mBoard[PerturbationEngine.pint(__L27, i)][PerturbationEngine.pint(__L28, j)])) != (PerturbationEngine.pint(__L30, 0))))) {
            return PerturbationEngine.pboolean(__L36, solve(PerturbationEngine.pint(__L34, ((PerturbationEngine.pint(__L32, i)) + (PerturbationEngine.pint(__L33, 1)))), PerturbationEngine.pint(__L35, j)));
        } 
        for (int value = PerturbationEngine.pint(__L37, 1); PerturbationEngine.pboolean(__L40, ((PerturbationEngine.pint(__L38, value)) <= (PerturbationEngine.pint(__L39, mBoardSize)))); PerturbationEngine.pint(__L41, (value++))) {
            if (PerturbationEngine.pboolean(__L45, isValid(PerturbationEngine.pint(__L42, i), PerturbationEngine.pint(__L43, j), PerturbationEngine.pint(__L44, value)))) {
                mBoard[PerturbationEngine.pint(__L46, i)][PerturbationEngine.pint(__L47, j)] = PerturbationEngine.pint(__L48, value);
                setSubsetValue(PerturbationEngine.pint(__L49, i), PerturbationEngine.pint(__L50, j), PerturbationEngine.pint(__L51, value), PerturbationEngine.pboolean(__L52, true));
                if (PerturbationEngine.pboolean(__L57, solve(PerturbationEngine.pint(__L55, ((PerturbationEngine.pint(__L53, i)) + (PerturbationEngine.pint(__L54, 1)))), PerturbationEngine.pint(__L56, j)))) {
                    return PerturbationEngine.pboolean(__L58, true);
                } 
                setSubsetValue(PerturbationEngine.pint(__L59, i), PerturbationEngine.pint(__L60, j), PerturbationEngine.pint(__L61, value), PerturbationEngine.pboolean(__L62, false));
            } 
        }
        mBoard[PerturbationEngine.pint(__L63, i)][PerturbationEngine.pint(__L64, j)] = PerturbationEngine.pint(__L65, 0);
        return PerturbationEngine.pboolean(__L66, false);
    }

    private boolean isValid(int i, int j, int val) {
        PerturbationEngine.pint(__L0, (val--));
        boolean isPresent = PerturbationEngine.pboolean(__L13, ((PerturbationEngine.pboolean(__L7, ((PerturbationEngine.pboolean(__L3, mRowSubset[PerturbationEngine.pint(__L1, i)][PerturbationEngine.pint(__L2, val)])) || (PerturbationEngine.pboolean(__L6, mColSubset[PerturbationEngine.pint(__L4, j)][PerturbationEngine.pint(__L5, val)]))))) || (PerturbationEngine.pboolean(__L12, mBoxSubset[PerturbationEngine.pint(__L10, computeBoxNo(PerturbationEngine.pint(__L8, i), PerturbationEngine.pint(__L9, j)))][PerturbationEngine.pint(__L11, val)]))));
        return PerturbationEngine.pboolean(__L15, (!(PerturbationEngine.pboolean(__L14, isPresent))));
    }

    private int computeBoxNo(int i, int j) {
        int boxRow = PerturbationEngine.pint(__L69, ((PerturbationEngine.pint(__L67, i)) / (PerturbationEngine.pint(__L68, mBoxSize))));
        int boxCol = PerturbationEngine.pint(__L72, ((PerturbationEngine.pint(__L70, j)) / (PerturbationEngine.pint(__L71, mBoxSize))));
        return PerturbationEngine.pint(__L77, ((PerturbationEngine.pint(__L75, ((PerturbationEngine.pint(__L73, boxRow)) * (PerturbationEngine.pint(__L74, mBoxSize))))) + (PerturbationEngine.pint(__L76, boxCol))));
    }

    static private void initPerturbationLocation0() {
        __L0 = new PerturbationLocationImpl("Sudoku.java:78", 0, "Numerical");
        __L1 = new PerturbationLocationImpl("Sudoku.java:79", 1, "Numerical");
        __L2 = new PerturbationLocationImpl("Sudoku.java:79", 2, "Numerical");
        __L3 = new PerturbationLocationImpl("Sudoku.java:79", 3, "Boolean");
        __L4 = new PerturbationLocationImpl("Sudoku.java:79", 4, "Numerical");
        __L5 = new PerturbationLocationImpl("Sudoku.java:79", 5, "Numerical");
        __L6 = new PerturbationLocationImpl("Sudoku.java:79", 6, "Boolean");
        __L7 = new PerturbationLocationImpl("Sudoku.java:79", 7, "Boolean");
        __L8 = new PerturbationLocationImpl("Sudoku.java:79", 8, "Numerical");
        __L9 = new PerturbationLocationImpl("Sudoku.java:79", 9, "Numerical");
        __L10 = new PerturbationLocationImpl("Sudoku.java:79", 10, "Numerical");
        __L11 = new PerturbationLocationImpl("Sudoku.java:79", 11, "Numerical");
        __L12 = new PerturbationLocationImpl("Sudoku.java:79", 12, "Boolean");
        __L13 = new PerturbationLocationImpl("Sudoku.java:79", 13, "Boolean");
        __L14 = new PerturbationLocationImpl("Sudoku.java:80", 14, "Boolean");
        __L15 = new PerturbationLocationImpl("Sudoku.java:80", 15, "Boolean");
        __L16 = new PerturbationLocationImpl("Sudoku.java:50", 16, "Numerical");
        __L17 = new PerturbationLocationImpl("Sudoku.java:50", 17, "Numerical");
        __L18 = new PerturbationLocationImpl("Sudoku.java:50", 18, "Boolean");
        __L19 = new PerturbationLocationImpl("Sudoku.java:54", 19, "Numerical");
        __L20 = new PerturbationLocationImpl("Sudoku.java:54", 20, "Numerical");
        __L21 = new PerturbationLocationImpl("Sudoku.java:54", 21, "Boolean");
        __L22 = new PerturbationLocationImpl("Sudoku.java:55", 22, "Numerical");
        __L23 = new PerturbationLocationImpl("Sudoku.java:56", 23, "Numerical");
        __L24 = new PerturbationLocationImpl("Sudoku.java:56", 24, "Numerical");
        __L25 = new PerturbationLocationImpl("Sudoku.java:56", 25, "Boolean");
        __L26 = new PerturbationLocationImpl("Sudoku.java:57", 26, "Boolean");
        __L27 = new PerturbationLocationImpl("Sudoku.java:60", 27, "Numerical");
        __L28 = new PerturbationLocationImpl("Sudoku.java:60", 28, "Numerical");
        __L29 = new PerturbationLocationImpl("Sudoku.java:60", 29, "Numerical");
        __L30 = new PerturbationLocationImpl("Sudoku.java:60", 30, "Numerical");
        __L31 = new PerturbationLocationImpl("Sudoku.java:60", 31, "Boolean");
        __L32 = new PerturbationLocationImpl("Sudoku.java:61", 32, "Numerical");
        __L33 = new PerturbationLocationImpl("Sudoku.java:61", 33, "Numerical");
        __L34 = new PerturbationLocationImpl("Sudoku.java:61", 34, "Numerical");
        __L35 = new PerturbationLocationImpl("Sudoku.java:61", 35, "Numerical");
        __L36 = new PerturbationLocationImpl("Sudoku.java:61", 36, "Boolean");
        __L37 = new PerturbationLocationImpl("Sudoku.java:63", 37, "Numerical");
        __L38 = new PerturbationLocationImpl("Sudoku.java:63", 38, "Numerical");
        __L39 = new PerturbationLocationImpl("Sudoku.java:63", 39, "Numerical");
        __L40 = new PerturbationLocationImpl("Sudoku.java:63", 40, "Boolean");
        __L41 = new PerturbationLocationImpl("Sudoku.java:63", 41, "Numerical");
        __L42 = new PerturbationLocationImpl("Sudoku.java:64", 42, "Numerical");
        __L43 = new PerturbationLocationImpl("Sudoku.java:64", 43, "Numerical");
        __L44 = new PerturbationLocationImpl("Sudoku.java:64", 44, "Numerical");
        __L45 = new PerturbationLocationImpl("Sudoku.java:64", 45, "Boolean");
        __L46 = new PerturbationLocationImpl("Sudoku.java:65", 46, "Numerical");
        __L47 = new PerturbationLocationImpl("Sudoku.java:65", 47, "Numerical");
        __L48 = new PerturbationLocationImpl("Sudoku.java:65", 48, "Numerical");
        __L49 = new PerturbationLocationImpl("Sudoku.java:66", 49, "Numerical");
        __L50 = new PerturbationLocationImpl("Sudoku.java:66", 50, "Numerical");
        __L51 = new PerturbationLocationImpl("Sudoku.java:66", 51, "Numerical");
        __L52 = new PerturbationLocationImpl("Sudoku.java:66", 52, "Boolean");
        __L53 = new PerturbationLocationImpl("Sudoku.java:67", 53, "Numerical");
        __L54 = new PerturbationLocationImpl("Sudoku.java:67", 54, "Numerical");
        __L55 = new PerturbationLocationImpl("Sudoku.java:67", 55, "Numerical");
        __L56 = new PerturbationLocationImpl("Sudoku.java:67", 56, "Numerical");
        __L57 = new PerturbationLocationImpl("Sudoku.java:67", 57, "Boolean");
        __L58 = new PerturbationLocationImpl("Sudoku.java:68", 58, "Boolean");
        __L59 = new PerturbationLocationImpl("Sudoku.java:70", 59, "Numerical");
        __L60 = new PerturbationLocationImpl("Sudoku.java:70", 60, "Numerical");
        __L61 = new PerturbationLocationImpl("Sudoku.java:70", 61, "Numerical");
        __L62 = new PerturbationLocationImpl("Sudoku.java:70", 62, "Boolean");
        __L63 = new PerturbationLocationImpl("Sudoku.java:73", 63, "Numerical");
        __L64 = new PerturbationLocationImpl("Sudoku.java:73", 64, "Numerical");
        __L65 = new PerturbationLocationImpl("Sudoku.java:73", 65, "Numerical");
        __L66 = new PerturbationLocationImpl("Sudoku.java:74", 66, "Boolean");
        __L67 = new PerturbationLocationImpl("Sudoku.java:84", 67, "Numerical");
        __L68 = new PerturbationLocationImpl("Sudoku.java:84", 68, "Numerical");
        __L69 = new PerturbationLocationImpl("Sudoku.java:84", 69, "Numerical");
        __L70 = new PerturbationLocationImpl("Sudoku.java:85", 70, "Numerical");
        __L71 = new PerturbationLocationImpl("Sudoku.java:85", 71, "Numerical");
        __L72 = new PerturbationLocationImpl("Sudoku.java:85", 72, "Numerical");
        __L73 = new PerturbationLocationImpl("Sudoku.java:86", 73, "Numerical");
        __L74 = new PerturbationLocationImpl("Sudoku.java:86", 74, "Numerical");
        __L75 = new PerturbationLocationImpl("Sudoku.java:86", 75, "Numerical");
        __L76 = new PerturbationLocationImpl("Sudoku.java:86", 76, "Numerical");
        __L77 = new PerturbationLocationImpl("Sudoku.java:86", 77, "Numerical");
        __L78 = new PerturbationLocationImpl("Sudoku.java:30", 78, "Numerical");
        __L79 = new PerturbationLocationImpl("Sudoku.java:30", 79, "Numerical");
        __L80 = new PerturbationLocationImpl("Sudoku.java:31", 80, "Numerical");
        __L81 = new PerturbationLocationImpl("Sudoku.java:31", 81, "Numerical");
        __L82 = new PerturbationLocationImpl("Sudoku.java:32", 82, "Numerical");
        __L83 = new PerturbationLocationImpl("Sudoku.java:32", 83, "Numerical");
        __L84 = new PerturbationLocationImpl("Sudoku.java:33", 84, "Numerical");
        __L85 = new PerturbationLocationImpl("Sudoku.java:33", 85, "Numerical");
        __L86 = new PerturbationLocationImpl("Sudoku.java:33", 86, "Numerical");
        __L87 = new PerturbationLocationImpl("Sudoku.java:33", 87, "Boolean");
        __L88 = new PerturbationLocationImpl("Sudoku.java:33", 88, "Numerical");
        __L89 = new PerturbationLocationImpl("Sudoku.java:34", 89, "Numerical");
        __L90 = new PerturbationLocationImpl("Sudoku.java:34", 90, "Numerical");
        __L91 = new PerturbationLocationImpl("Sudoku.java:34", 91, "Numerical");
        __L92 = new PerturbationLocationImpl("Sudoku.java:34", 92, "Boolean");
        __L93 = new PerturbationLocationImpl("Sudoku.java:34", 93, "Numerical");
        __L94 = new PerturbationLocationImpl("Sudoku.java:35", 94, "Numerical");
        __L95 = new PerturbationLocationImpl("Sudoku.java:35", 95, "Numerical");
        __L96 = new PerturbationLocationImpl("Sudoku.java:35", 96, "Numerical");
        __L97 = new PerturbationLocationImpl("Sudoku.java:36", 97, "Numerical");
        __L98 = new PerturbationLocationImpl("Sudoku.java:36", 98, "Numerical");
        __L99 = new PerturbationLocationImpl("Sudoku.java:36", 99, "Boolean");
        __L100 = new PerturbationLocationImpl("Sudoku.java:37", 100, "Numerical");
        __L101 = new PerturbationLocationImpl("Sudoku.java:37", 101, "Numerical");
        __L102 = new PerturbationLocationImpl("Sudoku.java:37", 102, "Numerical");
        __L103 = new PerturbationLocationImpl("Sudoku.java:37", 103, "Boolean");
        __L104 = new PerturbationLocationImpl("Sudoku.java:44", 104, "Numerical");
        __L105 = new PerturbationLocationImpl("Sudoku.java:44", 105, "Numerical");
        __L106 = new PerturbationLocationImpl("Sudoku.java:44", 106, "Numerical");
        __L107 = new PerturbationLocationImpl("Sudoku.java:44", 107, "Numerical");
        __L108 = new PerturbationLocationImpl("Sudoku.java:44", 108, "Boolean");
        __L109 = new PerturbationLocationImpl("Sudoku.java:45", 109, "Numerical");
        __L110 = new PerturbationLocationImpl("Sudoku.java:45", 110, "Numerical");
        __L111 = new PerturbationLocationImpl("Sudoku.java:45", 111, "Numerical");
        __L112 = new PerturbationLocationImpl("Sudoku.java:45", 112, "Numerical");
        __L113 = new PerturbationLocationImpl("Sudoku.java:45", 113, "Boolean");
        __L114 = new PerturbationLocationImpl("Sudoku.java:46", 114, "Numerical");
        __L115 = new PerturbationLocationImpl("Sudoku.java:46", 115, "Numerical");
        __L116 = new PerturbationLocationImpl("Sudoku.java:46", 116, "Numerical");
        __L117 = new PerturbationLocationImpl("Sudoku.java:46", 117, "Numerical");
        __L118 = new PerturbationLocationImpl("Sudoku.java:46", 118, "Numerical");
        __L119 = new PerturbationLocationImpl("Sudoku.java:46", 119, "Numerical");
        __L120 = new PerturbationLocationImpl("Sudoku.java:46", 120, "Boolean");
    }
}


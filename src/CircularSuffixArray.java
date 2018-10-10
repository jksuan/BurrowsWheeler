import edu.princeton.cs.algs4.BinaryStdIn;

/**
 * The {@code CircularSuffixArray} class provides the key component for 
 * the Burrows–Wheeler transform. 
 * <p>
 * Circular suffix array is a sorted array of each character of 
 * a string moving one position to the left.
 * As an example, consider the string "ABRACADABRA!" of length 12 :
 * <p>
 *   i       Original Suffixes            Sorted Suffixes         index[i]
 *  --    -----------------------     -----------------------    --------
 *   0    A B R A C A D A B R A !     ! A B R A C A D A B R A       11
 *   1    B R A C A D A B R A ! A     A ! A B R A C A D A B R       10
 *   2    R A C A D A B R A ! A B     A B R A ! A B R A C A D        7
 *   3    A C A D A B R A ! A B R     A B R A C A D A B R A !        0
 *   4    C A D A B R A ! A B R A     A C A D A B R A ! A B R        3
 *   5    A D A B R A ! A B R A C     A D A B R A ! A B R A C        5
 *   6    D A B R A ! A B R A C A     B R A ! A B R A C A D A        8
 *   7    A B R A ! A B R A C A D     B R A C A D A B R A ! A        1
 *   8    B R A ! A B R A C A D A     C A D A B R A ! A B R A        4
 *   9    R A ! A B R A C A D A B     D A B R A ! A B R A C A        6
 *  10    A ! A B R A C A D A B R     R A ! A B R A C A D A B        9
 *  11    ! A B R A C A D A B R A     R A C A D A B R A ! A B        2
 * <p>
 * The index[i] to be the index of the original suffix that appears ith in the sorted array. 
 * For example, index[11] = 2 means that the 2nd original suffix appears 11th in the sorted order.
 * <p>
 * More info in directory 
 * ../assignment5_burrows/specifications/Burrows–Wheeler Data Compression.html
 * 
 * @author Chih kai 
 */

public class CircularSuffixArray {
    
  private int n;
  private int[] index;
  private Manber manber;
  
  /**
   * Circular suffix array of s
   * 
   * @param <code>s</code> - input string 
   */
  public CircularSuffixArray(String s) {
    if (s == null) throw new IllegalArgumentException("Input string must be not null");  
    manber = new Manber(s);  // base on ManberMyers sort       
  }     
    
  //get a char from input string
  private int charAt(String s, int d, int shift) {
    if (d == n)  
      return -1;
    int i = (d + shift) % n;
    return s.charAt(i);
  }
   
  // insertion sort a[lo..hi], starting at character d
  // do not sort a[lo..hi] explicitly
  private void insertionSort(String s, int lo, int hi, int d) {
    for (int i = lo; i <= hi; i++)
      for (int j = i; j > lo && less(s, j, j-1, d); j--)
        exch(j, j-1);
  }

  // swap pointer sort indices
  private void exch(int i, int j) {
    int temp = index[i];
    index[i] = index[j];
    index[j] = temp;
  }

  // is c1 less than c2, starting at character d
  private boolean less(String s, int v, int w, int d) { 
    for (int i = d; i < length(); i++) {
      int c1 = charAt(s, i, index(v));
      int c2 = charAt(s, i, index(w));
      if (c1 < c2) return true; 
      if (c1 > c2) return false;
    }
    return false;
  }  
  
  /**
   * Return the length of input string
   * 
   * @return <code>n</code> - length of input string
   */
  public int length() {
    return manber.length();
  }
  
  
  /**
   * Returns index of the original suffix that appears <em>ith</em> in the sorted array
   * 
   * @param <code>i</code> the <em>ith</em> sorted array
   * @return index of the original suffix that appears <em>ith</em> in the sorted array
   * @throws IllegalArgumentException if <code>i</code> is not 
   *         between <code>0</code> and <code>length()</code> − <code>1</code>
   */
  public int index(int i) {
    if (i < 0 || i > length() - 1) 
      throw new IllegalArgumentException("Argument i must be between 0 and length() - 1");
    return manber.index(i);
  }
   
  // Unit testing (required)
  public static void main(String[] args) {
    String s = BinaryStdIn.readString();
    CircularSuffixArray suffix = new CircularSuffixArray(s);
    for (int i = 0; i < s.length(); i++) {
      System.out.println(suffix.index(i));
    }
    System.out.println("END");
  }
}

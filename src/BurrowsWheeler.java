import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/** 
 * The Burrows–Wheeler compression algorithm consists of three algorithmic components, 
 * and Burrows–Wheeler transform(BWT) is one of them, and the {@code BurrowsWheeler} class
 * is to implement BWT.
 * <p>
 * The goal of BWT is not to compress a message, 
 * but rather to transform it into a form that is more amenable to be compressed 
 * by Huffman or other data compression algorithm. 
 * The transform rearranges the characters in the input so that 
 * there are lots of clusters with repeated characters, 
 * but in such a way that it is still possible to recover the original input.
 * <p>
 * These three algorithmic components are applied in succession:
 * <p>
 * 1.Burrows–Wheeler transform.
 * <p>
 * 2.Move-to-front encoding
 * <p>
 * 3.Huffman compression or Run-length encoding compression
 * <p>
 * More info in directory 
 * ../assignment5_burrows/specifications/Burrows–Wheeler Data Compression.html
 * 
 * @author chih kai
 */

public class BurrowsWheeler {
  
  private static final int R = 256;
  
  /**
   * Apply Burrows-Wheeler transform,
   * reading from standard input and writing to standard output
   */
  public static void transform() {
    String input = BinaryStdIn.readString();     
       
    CircularSuffixArray suffix = new CircularSuffixArray(input);
    int first = -1;
    int[] t = new int[input.length()];
    for (int i = 0; i < input.length(); i++) {
      int shift = suffix.index(i);
      t[i] = getChar(input, input.length() - 1, shift); // get the last column of circular suffix array
      if (shift == 0) first = i;
    }
    
    BinaryStdOut.write(first);
    for (int i = 0; i < input.length(); i++) {
      BinaryStdOut.write(t[i], 8);  // read a byte from integer
    }
    BinaryStdOut.close();
  }
  
  // get an integer value of a char from input string 
  private static int getChar(String s, int d, int shift) {
    assert d >= 0 && d <= s.length() - 1;
    int i = (d + shift) % s.length();
    return s.charAt(i);
  }
  

  /**
   * Apply Burrows-Wheeler inverse transform, 
   * reading from standard input and writing to standard output
   */
  public static void inverseTransform() {
    int first = BinaryStdIn.readInt();
    String input = BinaryStdIn.readString();
    int n = input.length();
    int[] next = new int[n];
    char[] a = input.toCharArray();
    char[] aux = new char[n];          
  
    int[] count = new int[R + 1];
    for (int i = 0; i < n; i++) {
      count[a[i] + 1]++;
    }
    
    for (int r = 0; r < R; r++) {
      count[r + 1] += count[r];
    }
    
    for (int i = 0; i < n; i++) {
      aux[count[a[i]]] = a[i];
      next[count[a[i]]] = i;
      count[a[i]]++;
    }
    
    int k = first;
    BinaryStdOut.write(aux[k]);    
    for (int i = 0; i < n - 1; i++) {
      k = next[k];
      BinaryStdOut.write(aux[k]);
    }
    BinaryStdOut.close();
  }

  // if args[0] is '-', apply Burrows-Wheeler transform
  // if args[0] is '+', apply Burrows-Wheeler inverse transform
  public static void main(String[] args) {
    if (args[0].equals("-")) transform();
    else if (args[0].equals("+")) inverseTransform();
    else throw new IllegalArgumentException("Illegal command line argument");
  }
}

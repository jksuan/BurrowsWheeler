import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * The {@code MoveToFront} is one of the three Burrows–Wheeler compression algorithm components.
 * <p> 
 * Three Burrows–Wheeler compression algorithm components, which are applied in succession:
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
 *
 */

public class MoveToFront {
  
  //alphabet size of extended ASCII
  private static final int R = 256;
  private static final int W = 8;         // codeword width
  
  public MoveToFront() { }
  
  /**
   * Apply move-to-front encoding, 
   * reading from standard input and writing to standard output   * 
   */  
  public static void encode() {
    String s = BinaryStdIn.readString();
    char[] seq = new char[R];
    for (int i = 0; i < R; i++) 
      seq[i] = (char) i;
    
    int codeword = -1;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      for (int j = 0; j < R; j++) {
        if (seq[j] == c) {
          codeword = j;
          break;
        }
      }
      BinaryStdOut.write(codeword, W);
      System.arraycopy(seq, 0, seq, 1, codeword);
      seq[0] = c;      
    }
    BinaryStdOut.close();
  }

  /**
   * Apply move-to-front decoding, 
   * reading from standard input and writing to standard output   * 
   */
  public static void decode() {
    char[] seq = new char[R];
    for (int i = 0; i < R; i++) {
      seq[i] = (char) i;
    }
    while (!BinaryStdIn.isEmpty()) {
      int codeword = BinaryStdIn.readInt(W);
      char c = seq[codeword];
      BinaryStdOut.write(c);
      System.arraycopy(seq, 0, seq, 1, codeword);
      seq[0] = c;      
    }
    BinaryStdOut.close();
  }

  // if args[0] is '-', apply move-to-front encoding
  // if args[0] is '+', apply move-to-front decoding
  public static void main(String[] args) {
    if (args[0].equals("-")) encode();
    if (args[0].equals("+")) decode();
  }
}
import java.util.Random ;

/*
 *  Generate instances of HRM
 */
public class generator {

  public static void main (String[] args) {
    if (args.length != 3) {
      System.out.println("usage: java generator in_length out_length seed") ;
      return ;
    }

    int in_length = Integer.parseInt(args[0]);
    int out_length = Integer.parseInt(args[1]);
    int seed = Integer.parseInt(args[2]);

    int[] in = new int[in_length];
    int[] out  = new int[out_length];

    // Choose the sequences uniformly at random
    // NB random.nextInt(k) gives a value in range 0..k-1
    Random random = new Random(seed) ;
    for (int i = 0; i < in_length; i++) {
      in[i] = 1+random.nextInt(in_length*2);
    }
    for (int i = 0; i < out_length; i++) {
      out[i] = 1+random.nextInt(out_length*2);
    }

    // Upper bound on the number of actions:
    // minimum of the sum between the differences of each input element and the output elements
    // i.e., represents a plan that gets the element, bumps until you get the number and then output
    int max_ops = 999;
    int diff_outputs = 0;
    for (int j = 0; j < out_length-1; j++) {
        diff_outputs += Math.abs(out[j] - out[j+1]); // counts the number of bumps needed for each one
    }
    for (int i = 0; i < in_length-1; i++) {
        // count the skipping to the value + storing step
        int diff = i+1 + 1;
        diff += Math.abs(in[i] - out[0]) + diff_outputs; // count all the bumping
        diff += out_length; // outputting steps
        if (diff < max_ops) {
            max_ops = diff;
        }
    }

    // 2 registers should be more than enough. 0 registers is a possibility,
    // and most probably generate an unsat instance
    int max_registers=random.nextInt(2);

    // output
    System.out.println("$ HRM instance with in/out lengths = "+in_length+"/"+out_length+", seed = "+seed) ;
    System.out.println("language ESSENCE' 1.0");
    System.out.print("letting input=[") ;
    for (int i = 0; i < in_length; i++) {
      System.out.print(in[i]);
      if (i < in_length-1)
        System.out.print(",") ;
    }
    System.out.println("]") ;

    System.out.print("letting output=[") ;
    for (int i = 0; i < out_length; i++) {
      System.out.print(out[i]) ;
      if (i < out_length-1)
        System.out.print(",") ;
    }
    System.out.println("]") ;
    System.out.println("letting MAX_OPS="+max_ops);
    System.out.println("letting num_registers="+max_registers);
  }
}
package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Double> times = new AList<>();
        times.addLast(timecalulation(1000));
        times.addLast(timecalulation(2000));
        times.addLast(timecalulation(4000));
        times.addLast(timecalulation(8000));
        times.addLast(timecalulation(16000));
        times.addLast(timecalulation(32000));
        times.addLast(timecalulation(64000));
        times.addLast(timecalulation(128000));
        AList<Integer> nums = new AList<>();
        nums.addLast(1000);
        nums.addLast(2000);
        nums.addLast(4000);
        nums.addLast(8000);
        nums.addLast(16000);
        nums.addLast(32000);
        nums.addLast(64000);
        nums.addLast(128000);
        AList<Integer> nums2 = new AList<>();
        nums2.addLast(10000);
        nums2.addLast(10000);
        nums2.addLast(10000);
        nums2.addLast(10000);
        nums2.addLast(10000);
        nums2.addLast(10000);
        nums2.addLast(10000);
        nums2.addLast(10000);
        printTimingTable(nums, times, nums2);
    }
    public static double timecalulation(int N){
        int i = 0;
        SLList<Integer> L = new SLList<>();
        while ( i < N) {
            L.addLast(i);
            i = i + 1;
        }
        int j = 0 ;
        Stopwatch sw = new Stopwatch();
        while ( j < 10000 ){
            L.getLast();
            j = j + 1;
        }
        double timeInSeconds = sw.elapsedTime();
        //System.out.println("Time taken to compute 41st fibonacci number: " + timeInSeconds + " seconds.");
        return timeInSeconds;
    }
}

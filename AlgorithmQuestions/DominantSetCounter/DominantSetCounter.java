package second;

import java.util.*;

/**
 2
 1 5
 3 3

 3
 1 3
 2 5
 4 6

 5
 1 3
 2 5
 3 6
 8 9
 7 8

 4
 1 3
 2 4
 2 3
 3 5

 0

 1
 1 3
 **/

public class DominantSetCounter {

    public static List<Integer> getMaxNonOverlappingLeft(List<Interval> intervals)
    {
        List<Integer> a = new ArrayList<>();

        for(int i=0;i<intervals.size();i++)
        {
            a.add(0);
            for(int j=0;j<=i;j++)
                if(intervals.get(i).overlap(intervals.get(j)))
                {
                    a.set(i, j-1);
                    break;
                }
        }
        return a;
    }

    public static List<Integer> getMinNonOverlappingRight(List<Interval> intervals)
    {
        List<Integer> b = new ArrayList<>();

        for(int i=0;i<intervals.size();i++)
        {
            b.add(0);
            for(int j=i+1;j<intervals.size();j++)
                if(!intervals.get(i).overlap(intervals.get(j)))
                {
                    b.set(i, j);
                    break;
                }
        }
        return b;
    }

    public static int getCountOfDominantSet(List<Interval> intv, List<Integer> maxNonOverlappingLeft,
                                        List<Integer> minNonOverlappingRight, int n)
    {
        int sz = intv.size();

        int[][] dp = new int[sz][sz];
        int[][] topToBottomSum = new int[sz][sz];

        for(int i=0;i<intv.size(); i++)
        {
            for(int j=0;j<intv.size(); j++) {
                int ans = 0;
                if (minNonOverlappingRight.get(j) == i && j == 0)
                    ans = 1;

                else if (j == 0)
                    ans = 0;

                else if (i <= maxNonOverlappingLeft.get(j))
                    ans = 2 * dp[i][j - 1];

                else if (i > maxNonOverlappingLeft.get(j) && i < minNonOverlappingRight.get(j))
                    ans = dp[i][j - 1];

                else if (i > minNonOverlappingRight.get(j))
                    ans = dp[i][j - 1];

                else {
                    ans = dp[i][j - 1];
                    int overlappingFrom = maxNonOverlappingLeft.get(j);
                    int sumFromOverlappingIntervals = topToBottomSum[i][j - 1]
                            - (overlappingFrom >= 0 ? topToBottomSum[overlappingFrom][j - 1] : 0);
                    ans += sumFromOverlappingIntervals;
                }

                dp[i][j] = ans;

                if (i > 0)
                    topToBottomSum[i][j] = dp[i][j] + topToBottomSum[i - 1][j];
                else topToBottomSum[i][j] = dp[i][j];
            }
        }
        return dp[n+1][n];
    }

//    public static int getCountTillIndex(int i, int j, List<Interval> intv, List<Integer> maxNonOverlappingLeft,
//                                        List<Integer> minNonOverlappingRight)
//    {
//        int ans=0;
//        if(minNonOverlappingRight.get(j)==i && j==0)
//            ans = 1;
//        else if(j == 0)
//            ans = 0;
//        else if(i <= maxNonOverlappingLeft.get(j))
//            ans = 2*getCountTillIndex(i,j-1,intv,maxNonOverlappingLeft,minNonOverlappingRight);
//
//        else if(i > maxNonOverlappingLeft.get(j) && i<minNonOverlappingRight.get(j))
//            ans = getCountTillIndex(i,j-1,intv,maxNonOverlappingLeft,minNonOverlappingRight);
//
//        else if(i > minNonOverlappingRight.get(j))
//            ans = getCountTillIndex(i,j-1,intv,maxNonOverlappingLeft,minNonOverlappingRight);
//
//        else {
//            ans = getCountTillIndex(i,j-1,intv,maxNonOverlappingLeft,minNonOverlappingRight);
//            for(int in = maxNonOverlappingLeft.get(j)+1; in<=i; in++)
//            {
//                int val = getCountTillIndex(in,j-1,intv,maxNonOverlappingLeft,minNonOverlappingRight);
//                ans += val;
//
//            }
//        }
//        //  cout<<ans<<endl;
//        return ans;
//    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        // Input
        int n = scanner.nextInt();

        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(-1,-1));
        for(int i=0; i<n; i++){
            int s = scanner.nextInt(), e = scanner.nextInt();
            Interval interval = new Interval(s,e);
            intervals.add(interval);
        }
        intervals.add(new Interval(50000,50000));

        // Sort Interval List
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval one, Interval two) {
                if((one.getEnd()==two.getEnd() && one.getStart()<two.getStart()) ||
                        (one.getEnd()!=two.getEnd() && one.getEnd()<two.getEnd()))
                    return -1;
                return 1;
            }
        });

        List<Integer> maxNonOverlappingLeft = getMaxNonOverlappingLeft(intervals);
        List<Integer> minNonOverlappingRight = getMinNonOverlappingRight(intervals);

       // System.out.println(getCountTillIndex(n+1, n, intervals,maxNonOverlappingLeft, minNonOverlappingRight ));
        System.out.println(getCountOfDominantSet(intervals, maxNonOverlappingLeft, minNonOverlappingRight, n));
    }
}

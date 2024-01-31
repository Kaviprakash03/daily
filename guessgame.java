import java.util.*;
public class GuessGame{
    public static void main(String args[]){
        Scanner me=new Scanner(System.in);
        int n=me.nextInt();
        int dp[][]=new int[n+1][n+1];
        for(int i=2;i<=n;i++){
            for(int j=i-1;j>0;j--){
                int g=Integer.MAX_VALUE;
                for(int k=j+1;k<i;k++){
                    int loc=k+Math.max(dp[j][k-1],dp[k+1][i]);
                    g=Math.min(g,loc);
                }
                dp[j][i]=(j+1==i?j:g);
            }
        }
        System.out.println(dp[1][n]);
    }
}
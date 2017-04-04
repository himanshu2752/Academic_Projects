
public class StoI{
public static int convert(String str) {
    int ans = 0, f = 1;
    for (int i = str.length()-1; i >= 0; i--) {
        ans += (str.charAt(i) - '0') * f;
        f *= 10;
    }
    return ans;
}

public static void main(String args[])
{
	
	StoI obj=new StoI();
	String str=args[0];
	System.out.println(str);
	System.out.println(convert(str));
	
}
}
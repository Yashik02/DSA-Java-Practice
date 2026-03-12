import java.util.*;

public class JavaBasics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // System.out.println("Enter Binary number : ");
        // int h = sc.nextInt();
        // System.out.println("Enter Decimal number : ");
        // int l = sc.nextInt();
        //System.out.println((isPrime(n)) ? "Prime" : "Not Prime");
        System.out.println("Enter number : ");
        //int n = sc.nextInt();
        //System.out.println((char)('a' & ~' '));
        String line = "my, name is@khan";
        String[] arr = line.split("[,@]");
        for(String word : arr){
            System.out.println(word);
        }
    }

    public static boolean isPower2 (int num) {
        return (num & (num - 1)) == 0;
    }
    
    public static int setBit (int num) {
        int count = 0;
        while(num != 0) {
            if((num & 1) == 1) {
                count++;
            }
            num >>= 1;
        }
        return count;
    }

    public static int getIthBit(int num, int ith) {
        if ((num & (1 << ith)) == 0) {
            return 0;
        }
        else{
            return 1;
        }
    }

    public static int power (int num, int power) {
        int ans = 1;
        while(power != 0) {
            if((power & 1) == 1) {
                ans *= num;
            }
            num *= num;
            power >>= 1;
        }
        return ans;
    }

    public static int modulo(int num, int power, int mod) {
        int ans = 1;
        num %= mod;
        while(power > 0) {
            if((power & 1) != 0) {
                ans = (int)(1L * ans * num) % mod;
            }
            num = (int)(1L * num * num) % mod;
            power >>= 1;
        }
        return ans;
    }

    public static int setIthBit(int num, int ith) {
        return num | (1 << ith); 
}

    public static int clearIthBit(int num, int ith) {
        return num & (~(1 << ith)); 
    }

    public static boolean isEvenBit (int num) {
        if((num & 1) == 1) {
            return false;
        }
        return true;
    }

    public static int clearTillIthBitLoop( int num, int ith) {
        for(int i = 0; i <= ith; i++) {
            num = (num & ~(1 << i));
        }
        return num;
    }

    public static int clearTillIthBit(int num, int ith) {
        return (num & ~((1 << ith + 1) - 1));
    }

    public static int clearIBit(int num, int ith) {
        return num & ((~0) << ith);
    }

    public static int clearItoJBit(int num, int ith, int jth) {
        int right = (~0) << ith;
        int left = (1 << jth) - 1;
        return num & (right | left);
    }

    public static int upsateIthBit(int num, int ith, int newBit) {
        if(newBit == 1){
            return setIthBit(num, ith);
        } else {
            return clearIthBit(num, ith);
        }
    }

    public static void test(String b) {
        System.out.println("test method called with argument: " + b);
    }

    public static void BinToDec(int num) {
        int power = 0;
        int dec = 0;
        while(num > 0) {
        int lastDig = num % 10;
        dec += (lastDig * Math.pow(2, power));
        num /= 10;
        power++;
        }
        System.out.println("Decimal value is : " + dec);
    }
    
    public static void DecToBin(int dnum) {
        int binary = 0;
        int power = 0;
        while(dnum > 0) {
            int remainder = dnum % 2;
            binary += remainder * (int)Math.pow(10, power);
            dnum /= 2;
            power++;
        }
        System.out.println("Binary value is : " + binary);
    }

    public static boolean isPrime (int n) {
        if(n == 2) {
            return true;
        }
        for(int i = 2; i<= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void primeInRange(int l, int h) {
        if(l < 2 ) {
            System.out.println("invalid input, please enter a number greater than 1");
            return;
        }
        else {
            for(int i = l; i <= h; i++) {
                if(isPrime(i)) {
                    System.out.print(i + " ");
                }
            }
        }
    }

    public static void Average(int n, int m, int o) {
        int sum = n + m + o;
        int avg = sum / 3;
        System.out.println("Average is : " + avg);
        return;
    }

    public static boolean isEven(int n) {
        return(n % 2 == 0);
    }

    public static boolean isPalindrome(int n) {
        int rev = 0;
        int original = n;
        for(int i = 1; i > 0; i /= 10) {
            int lastDig = n % 10;
            rev = lastDig + (rev * 10);
            
        }
        return(original == rev);
    }

    public static void digitSum(int n) {
        int sum = 0;
        while( n > 0){
            int lastDig = n % 10;
            sum += lastDig;
            n /= 10;
        }
        System.out.println("sum of digits is : " + sum);
        return;
    }

    public static void rectangle(int r, int c) {
        for(int i = 1; i <= r; i++){
            for(int j = 1; j <= c; j++) {
                if(i == 1 || i == r || j == 1 || j == c) {
                    System.out.print("*");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void triangle(int r) {
        for(int i = 1; i <= r; i++) {
            for(int j = 1; j <= r; j++) {
                if(j > r - i) {
                    System.out.print("*");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void numTriangle(int r) {
        for(int i = 1; i <= r; i++) {
            for(int j = 1; j <= r - i + 1; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void floydTriangle(int r) {
        int count = 1;
        for(int i = 1; i <= r; i++) {
            for(int j = 1; j <= i; j++){
                System.out.print(count);
                count++;
            }
            System.out.println();
        }    
    }

    public static void binaryTriangle(int r) {
        for(int i = 1; i <= r; i++) {
            for(int j = 1; j <= i; j++){
                if(isEven(i + j)) {
                    System.out.print(1);
                }
                else {
                    System.out.print(0);
                }
            }
            System.out.println();
        } 
    }

    public static void PascalTriangle(int r) {
        for (int row = 0; row <= r; row++) {
            for (int j = 1; j <= r - row ; j++) {
                System.out.print("\t");
            }
            for(int col = 0; col <= row; col++) {
                System.out.print(binomialCoff(row, col) + "\t\t");
            }
            System.out.println();
        }
    }

    public static int binomialCoff(int n, int r) {
        if (r == 0 || r == n) {
            return 1;
        }
        else if (r == 1) {
            return n;
        }
        else {
            return factorial(n) / (factorial(r) * factorial(n - r));
        }
    }

    public static int factorial(int n) {
        int fact = 1;
        for(int i = 1; i <= n; i++) {
            fact *= i;
        }   
        return fact;
    }
    
    public static void factorial1(int n) {
        System.out.println("he");
    }
}
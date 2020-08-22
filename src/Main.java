public class Main {
    static int x = 10;
    static int y = 20;
    public static void main(String[] args) {
        Calculatable calculatorSum;
        calculatorSum = (x,y)-> x + y;
        Calculatable calculatorMul = (x,y) -> x * y;
        Calculatable calculatorDiv = (x,y) -> x / y;

        System.out.println(calculatorSum.calculate(10, 20)); //30
        System.out.println(calculatorMul.calculate(10, 20)); //200
        System.out.println(calculatorDiv.calculate(20, 10)); //0


        Operationable op = new Operationable(){

            public int calculate(int x, int y){

                return x + y;
            }
        };
        int z = op.calculate(20, 10);
        System.out.println(z); // 30

        Printable printer = System.out::println;
        printer.print("Hello World!"); // Hello World!

        System.out.println(x / 5); // 2

        Classable classTest = (y) -> {
            x = 100;
            return x / y;
        };
        System.out.println(classTest.calculate(5)); // 20

        TestComparable comparator = (String firstStr, String secondStr) ->
                Integer.compare(firstStr.length(),secondStr.length());
        System.out.println(comparator.compare("Hello", "World!")); // -1

        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        System.out.println(sum(numbers, number -> number < 5)); // 10
        System.out.println(sum(numbers, Helper::isEven)); // 20
        System.out.println(sum(numbers, Helper::isNegative)); // 0
        System.out.println(sum(numbers, Helper::isPositive)); // 45

        Creatable userBuilder = User::new;
        User user = userBuilder.create("userName");
        System.out.println(user.getName()); // userName

        Operation func = action(1);
        System.out.println(func.execute(6, 5));          // 11

        System.out.println(action(1).execute(6, 5));          // 11
        System.out.println(action(2).execute(6, 5));          // 1
        System.out.println(action(3).execute(6, 5));          // 30
        System.out.println(action(4).execute(6, 5));          // 0

    }
    private static Operation action(int number){
        switch(number){
            case 1: return (x, y) -> x + y;
            case 2: return (x, y) -> x - y;
            case 3: return (x, y) -> x * y;
            default: return (x,y) -> 0;
        }
    }
    private static int sum (int[] arr, Conditionable lambdaFunc)
    {
        int result = 0;
        for(int i : arr)
        {
            if (lambdaFunc.compare(i))
                result += i;
        }
        return result;
    }
}
class Helper{

    static boolean isEven(int n){

        return n%2 == 0;
    }

    static boolean isNegative(int n){

        return n < 0;
    }
    static boolean isPositive(int n){

        return n > 0;
    }
}

interface Calculatable {
    int calculate(int x, int y);
}

interface Operationable {
    int calculate(int x, int y);
}

interface Printable{
    void print(String s);
}

interface Classable {
    int calculate(int y);
}

interface TestComparable {
    int compare(String x, String y);
}

interface Conditionable {
    boolean compare(int n);
}

interface Creatable {
    User create(String name);
}

class User {

    private String name;
    String getName() {
        return name;
    }

    User(String n){
        this.name = n;
    }
}

interface Operation {
    int execute(int a, int b);
}
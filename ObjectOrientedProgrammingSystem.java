import java.util.*;

class RoughCodeRecord {
    //     Pen p1 = new Pen("blue", 5); //created a pen object named p1
        
    //     // p1.setColor("Blue");
    //     // System.out.println(p1.getColor());
    //     // p1.setTip(5);
    //     // System.out.println(p1.getTip());
    //     // p1.setColor("Yellow");
    //     // System.out.println(p1.getColor());
    //     //p1.setColor("Yellow");

    //     System.out.println(p1.getColor());


    //     // Student s1 = new Student();
        
    //     // Student s2 = new Student("yashik");

    //     // Student s3 = new Student(123);

    //     Student s1 = new Student();
    //     s1.name = "dhruv";
    //     s1.roll = 123;
    //     s1.Password = "abcd";
    //     s1.marks[0] = 100;
    //     s1.marks[1] = 90;
    //     s1.marks[2] = 80;

    //     Student s2 = new Student(s1);
    //     s2.Password = "xyz"; //created a new student object with same property as Student s1, but changed password (copy Constructor).
    //     s1.marks[2] = 100; //how?????
    //     s1.name  = "dhruv343";
    //     System.out.println(s2.name);
    //     for(int i = 0; i < s1.marks.length; i++) {
    //         System.out.println(s2.marks[i]);
    //     }
        

    //     //System.out.println(s1.name);

    //     //Student s4 = new Student("dhruv", 123); error because no Constructor with these data parameters

    //     BankAccount myAcc = new BankAccount();
    //     myAcc.Username = "Yashik Bandhu";
    //     myAcc.setPassword("YASH");

    // Dog dobby = new Dog();
    // dobby.eats();
    // dobby.legs = 4;
    // System.out.println(dobby.legs);
    // Calculator calc = new Calculator();
    // System.out.println(calc.sum(1.5F, 2.5F));

    // Deer d = new Deer();
    //     d.eats();

    //scanner sc = new scanner(System.in);

    // Horse h = new Horse();
    //     h.eat();
    //     h.walk();
    //     h.changeColor(null);
    //     System.out.println(h.color);
        
    //     Chicken c = new Chicken();
    //     c.eat();
    //     c.walk();

    //     Mustang m = new Mustang();
    
    //    Bear b = new Bear();
    //    b.eatsGrass();
    //    b.eatsMeat();

    //     Student1 s1 = new Student1();
    //     Student1 s2 = new Student1();
    //     Student1 s3 = new Student1();
    //     // s1.schoolName = "GNF";
    //     // System.out.println(s2.schoolName);
    //     // s3.schoolName = "abc";
    //     // System.out.println(s1.schoolName);

    //     System.out.println(s1.resultPercent(23, 43, 44));
    //     System.out.println(s2.resultPercent(73, 53, 64));
}



// Constructors : used for initializing.
// Destructors : Garbage collectors in java (for more info look in c++)

    //1.) Non-Paramiterized :-
        /*  Student() {
        System.out.println("constructor is called");
        }  */ 

    //2.) Paramiterized :-
        /*  Student(String name) {
        this.name = name;
        }  */

    //3.) copy Constructor :-
        //1.) shallow copy - same reference
        //2.) deep copy - a new meamory location
        /*3.) lazy copy - most Meamory effecient
        shallow copy for first time, if any changes are made it automatically makes it into a deep copy.*/

/*Encapsulation : defined as the 'Wrapping up' of data & meathods under a single unit (class).
It also impliments data hiding (by using acess specifiers on internal variables/meathods/blocks/nested classes/). */

//Inheritance : Inheritance is when properties & meathods of base class are passed on to a derived class 
    //types of Inheritance :-
        //1.) single level inheritance - base > derived. single base class to a single derived class
        //2.) multi level inheritance - base class > derived class > derived class
        //3.) Hierarchial inheritance - base > (derived class-1, derived class-2)
        //4.) Hybrid inheritance - anything goes! anything inheriting anywhere like a tree
        //5.) Multiple inheritance - (mainly in c++, only possible in java with interfaces) (base1, base2) > derived class
        //(while creating a new object, classes are called top to bottom. for ex: class a > class b > class c. class c in extended by b which is extended by a. so whille creating a new object, first A will be called then B then lastly C)

//Abstraction : hiding all the unnecessary details and showing only the important parts to the user.
    //(idea ✅, NOT implimentation ❌)
    //1.) Abstract classes
        //1.) cannot creat an instance of abstract class.(no object creation)(cannot use "new")
        //2.) can have abstract/non-abstract meathods
        //3.) can have constructors 
        //(while creating a new object, classes are called top to bottom. for ex: class a > class b > class c. class c in extended by b which is extended by a. so whille creating a new object, first A will be called then B then lastly C)
    //2.) Interfaces: (100% Total Abstraction, Multiple inheritance)
        // Interface is a Blueprint of class
            //1.) all meathods are public, abstract & withour implimentation
            //2.) used to achive total abstraction
            //3.) variables in the interface are final, public and static

//Polymorphism : (Overloading) 
    //1.) Compile time polymorphism (Static) - 
        //Meathod overlaoding : multiple functions with same name but different parameters (same name but-  (1)diff data types OR (2)diff number of data types)
    //2.) Run time polymorphism (Dynamic) - 
        //Meathod overriding : Parent and child classes both contain the same function with a different definition

//Packages : Packages is a group of types of classes, interfaces and sub-packages.
    //1.) in-built packages (import java.util.*; :- util(utility) is a package, many helpful classes and sub-packages and interfaces)
    //2.) User defined packages

//Getters and Setters : gets private values and sets them and keeps the value safe.

/*"this." is used to differentiate between instance variables and parameter names
stored in heap meamory*/

//Acess Modifiers public(any) > protected(in package + sub-class) > default(only package) > private(only class)

//static is used when creating a common: function/variable/ | /block(always runs when a class is called, even before Main class)/nested class(no need to create an outer class object when creating an inner class object)

//super!!!! is used to reffer to immidiate parent class object (purely optional to use)
    // to acess parent's properties (instance variables)
    //to acess parent's functions
    //to acess parents's constructors

//final: final keyword is used to set constants or values that cannot be altered

//Constructor chaining : calling another constructor while using/calling a constructor (search web for more)

//aggrigation : nested classes are called aggregation


class BankAccount {
    public String Username;
    private String Password;
    public void setPassword (String newPassword) {
        Password = newPassword;
    }
}

class Pen {
    private String color; //instance variables
    private int tip;

    Pen (String color, int tip) { /*constructor (instance variables are required to store variables)
        if not defined, java creates a constructor by default with no intitialization*/
        this.color = color;
        this.tip = tip;
    }

    void setColor(String newColor) { //Setters
        color = newColor;
    }

    void setTip(int newTip) {//Setters
        tip = newTip;
    }

    String getColor () { //Getters
        return this.color;
    }

    int getTip () { //Getters
        return tip;
    }
}

class Student {
    String name;
    int age;
    int roll;
    float marks[]; //only refference is copied (not "deep copied")
    String Password; //cgps
    float percent;

    
    class Address {
        String city;
    }

    //deep copy constructor
    Student (Student s1) {
        marks = new float[3];
        this.name = s1.name;
        this.roll = s1.roll;
        for(int i = 0; i < marks.length; i++) {
            this.marks[i] = s1.marks[i];
        }
    }
    

    //constructor overloading
    //Shallow copy Constructor
    // Student (Student s1) {
    //     marks = new float[3];
    //     this.name = s1.name;
    //     this.roll = s1.roll;
    //     this.marks = s1.marks;
    // }
    Student() {
        marks = new float[3];
        //this.name = name;
        System.out.println("constructor is called");
    }

    Student(String name) {
        marks = new float[3];
        this.name = name;
    }

    Student(int roll) {
        marks = new float[3];
        this.roll = roll;
    }

    void calcPercent (int phy, int chem, int math) {
        percent = (phy + chem + math) / 3;
    }
}

//inheritance

//Base class
class Animal {
    String color;

    void eats() {
        System.out.println("eats anything");
    }

    void breathes() {
        System.out.println("breathes");
    }
}

//Derived class
class Mammels extends Animal {
    int legs;
    void walks () {
        System.out.println("walks");
    }
}

class Fish extends Animal {
    int fins;

    void swim() {
        System.out.println("swims in water");
    }
}

class Bird extends Animal {
    void fly() {
        System.out.println("fly");
    }
}

//derived class level-2
//Mammels
class Dog extends Mammels {
    String breed;
}

class Cat extends Mammels {
    String color;
}

class Human extends Mammels {
    String name;
}

//Bird
class Peacock extends Bird {
    int feathers;
}

//Fish
class Tuna extends Fish {
    int eggs;
}

class Shark extends Fish {
    int teeths;
}

//Polymorphism

//Meathod overloading
//compile time overloading

class Calculator {
    int sum (int a, int b, int c) {
        return a + b + c;
    }

    int sum (int a, int b) {
        return a + b;
    }

    float sum (float a, float b) {
        return a + b;
    }

}

//Meathod overriding
//run time polymorphism

class Deer extends Animal {
    void eats() {
        System.out.println("eats grass");
    }
}

//Abstraction

abstract class Animal1 {

    String color = "brown";

    Animal1() {
        System.out.println("animal constructor called");
    }

    void eat() {
        System.out.println("animal eats");
    }

    abstract void walk(); //abstract meathod - no implimentation
}

class Horse extends Animal1 {

    Horse() {
        System.out.println("horse constructor called");
    }

    void changeColor(String color) {
        this.color = "black";
    }

    void eat() {
        System.out.println("animal eats");
    }

    void walk() { //must impliment!!! walk meathod
        System.out.println("walks on 4 legs");
    } 
    
}

class Chicken extends Animal1 {

    Chicken() {
        System.out.println("chicken constructor called");
    }

    void changeColor(String color) {
        this.color = "yellow";
    }

    void walk() {
        System.out.println("walks on 2 legs");
    }
}

class Mustang extends Horse {
    Mustang() {
        System.out.println("Mustang constructor called");
    }
}

//interfaces
interface ChessPlayer {
    void moves();
}

class Queen implements ChessPlayer {
    public void moves() {
        System.out.println("up, down, left , right, diagonal (in all 8 directions)");
    }
}

class Rook implements ChessPlayer {
    public void moves() {
        System.out.println("up, down, left , right (in all 4 directions)");
    }
}

class King implements ChessPlayer {
    public void moves() {
        System.out.println("up, down, left , right, diagonal (in all 8 directions by 1 step)");
    }
}

//multiple inheritance 

interface Carnivore {
    void eatsGrass();
}

interface Herbivore {
    void eatsMeat();
}

class Bear implements Carnivore, Herbivore {
    public void eatsGrass() {
        System.out.println("eats grass");
    }

    public void eatsMeat() {
        System.out.println("eats meat");
    }
}


//static
class Student1 {
    
    static int resultPercent(int math, int phy, int chem) {
        return (phy + math + chem) / 3;
    }
    //static here saves meamory rather than creating anew function for each object
    String name;
    int roll;
    
    static String schoolName;

    void setName (String name) {
        this.name = name;
    }

    String getName () {
        return this.name;
    }
}


//super

abstract class Animal2 {
    String color = "brown";
     Animal2() {
        System.out.println("animal constructor called");
    }

}

class Horse2 extends Animal2 {
    String color = "black";
    
    Horse2() {
        super();
        System.out.println("horse constructor called");
        System.out.println(color);
        System.out.println(super.color); //used to call parent variable
    }
    
}

class Hero {
    static {
        System.out.println("hello world"); // how static block works
    }
}

public class ObjectOrientedProgrammingSystem {
    public static void main (String[] args) {
        Complex a = new Complex(34, 50);
        Complex b = new Complex(54, 90);
        Complex ans = Complex.productComplex(a, b);
        Complex.printComplex(ans);
    }
}

class Complex {

    //instance variables
    int real;
    int imaginary;

    //constructor
    Complex (int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    //sum
    public static Complex addComplex (Complex a, Complex b) {
        return new Complex((a.real + b.real), (a.imaginary + b.imaginary));
    }
    
    public static Complex diffComplex (Complex a, Complex b) {
        return new Complex((a.real - b.real), (a.imaginary - b.imaginary));
    }

    public static Complex productComplex (Complex a, Complex b) {
        return new Complex( (a.real * b.real) - (a.imaginary * b.imaginary), (a.real * b.imaginary) + (b.real * a.imaginary));
    }

    //printing function

    public static void printComplex (Complex a) {
        if(a.real == 0 && a.imaginary != 0){
            System.out.println(a.imaginary + "i");
        }
        else if(a.real != 0 && a.imaginary == 0){
            System.out.println(a.real);
        }
        else if(a.real != 0 && a.imaginary > 0){
            System.out.println(a.real + " +" + a.imaginary + "i");
        }
        else{
            System.out.println(a.real + " " + a.imaginary + "i");
        }
    }
}
// We are the sole authors of the work in this repository.
/*
* Recursion.java
*
* Starter code for the recursion lab.
*
*/
import structure5.*;
import java.util.Arrays;

public class RecursionResubmission {

  // Note: Warmup questions are not graded, but you may wish to
  // complete & test them since later, graded questions build
  // on them.

  /***** Warmup 0.1 ********************************************/

  /**
  * Takes a non-negative integer and returns the sum of its digits.
  * @param n a non-negative integer
  * @return the sum of n's digits
  * @pre must take nonnegative int as n
  * @post returns a nonnegative integer that is the sum of n's digits
  */
  public static int digitSum(int n) {
    Assert.pre(n >= 0, "The value of input is nonnegative");
    if (n < 10) { // returns the value of a single digit
      Assert.post(n >= 0, "The value of ouput is nonnegative");
      return n;
    }
    Assert.post(n >= 0, "The value of input is nonnegative");
    return n % 10 + digitSum(n / 10); // lops of a digit by modding by 10, and then calls recursively on what remains (n/10)
  }

  /***** Warmup 0.2 ********************************************/

  /**
  * Given a set of integers and a target number, determines
  * where there is a subset of those numbers that sum to the
  * target number.
  *
  * @param setOfNums a set of numbers that may appear in the subset
  * @param targetSum the target value for the subset
  * @return true if some subset of numbers in setOfNums sums to targetSum
  * @pre setOfNums takes an array of integer values, targetSum takes an integer
  * that is, you guessed it, the target sum. Maybe that's why it's named targetSum.
  * Array entries and targetSum can all be positive or negative.
  * @post Returns a boolean, indicating whether or not any subset of the integers
  * within array setOfNums sum up to targetSum

  * Nothing nontrivial to assert for pre and post conditions here
  */
  public static boolean canMakeSum(int[] setOfNums, int targetSum) {
    if (targetSum == 0) {  // if the target sum has been reached
      return true;
    }
    if (setOfNums.length == 0) { // if there are no more numbers left to add, but targetsum isn't reached
    return false;
  } else {
    // call recursively, slicing off the first digit and adding it to targetSum (well, really subtracting, so that targetSum is "reached" when it's 0)
    boolean a = canMakeSum(Arrays.copyOfRange(setOfNums, 1, setOfNums.length), targetSum - setOfNums[0]);
    // call recursively, slicing of the first digi but not including it as a potential part of targetSum
    boolean b = canMakeSum(Arrays.copyOfRange(setOfNums, 1, setOfNums.length), targetSum);

    // if what comes up from either "path" is true, then there is some subset of entires in setOfNums that can make targetSum
    return (a || b);
  }
}


/*****  1  ***************************************************/

/**
* Return number of cannoballs in pyramid with the given `height`.
*
* @param height the height of the cannonball tower
* @return the number of cannonballs in the entire tower
* @pre height of the pyramid has to be one or greater (cannot be neg)
* @post returns an integer that represents the number of canonballs in the pyramid
*/
public static int countCannonballs(int height) {
  Assert.pre(height >= 1, "The value of input should be at least 1");
  if (height == 1) {    // base case, height 1 has 1 cannonballs
    return 1;

    // adds the current layer, where the number of cannonballs is the square of the height
    // and then calls recursively on the next layer down
  } else {
    int newHeight = (height * height) + countCannonballs(height - 1);
    Assert.post(newHeight >= 1, "The value of output should be greater than 1");
    return newHeight;
  }
}


/*****  2  ***************************************************/

/**
* A method that determines if a string reads the same forwards
* and backwards.
*
* @param str the string to check
* @return true if `str` is a palindrome.
* @pre str needs to be a str, can be null
* @post returns a boolean, indicating whether the string is a palindrome or not
* no nontrivial preconditions/postconditions to assert
*/
public static boolean isPalindrome(String str) {
  //Base case, a 1 character or empty string is a isPalindrome (the latter is a pretty arbitrary decisions.
  //But I guess nothing backwards is the same as nothing forwards.)
  if (str.length() <= 1) {
    return true;
    //Lops of one character from each side of the string, and if they're the same, calls recurisvely.
    //If both ends are different, it's not a palindrome.
  } else if (str.charAt(0) == str.charAt(str.length() - 1)) {
    return isPalindrome(str.substring(1, str.length() - 1));
  } else {
    return false;
  }
}

// helper to check input for isBalanced
public static boolean isInputAllParens(String str) {

  char[] bunchaParens = new char[] {'}', '{', ')', '(', ']', '['};

  for (int i = 0; i < str.length(); i ++) {
    boolean isValid = false;

    for (char paren: bunchaParens) {
      if (str.charAt(i) == paren) {
        isValid = true;
      }
    }
    if (!isValid) {
      return false;
    }

  }
  return true;

}

/*****  3  ***************************************************/

/**
* Checks whether `str` is a string of properly nested and matched
* parens, brackets, and braces.
*
* @param str a string of parens, brackets, and braces
* @return true if str is properly nested and matched
* @pre str must be a string with either parens, brackets, or braces or all of the above
* @post boolean is returned indicating whether the inputted string is balanced in the placement of parens, brackets, and braces
* no nontrivial postcondition to assert
*/
public static boolean isBalanced(String str) {
  Assert.pre(isInputAllParens(str), "string must contain only parens, brackets, and braces");

  // base case, an empty string will technically count as balanced
  //but this is really intended for once all matching pairs have been eliminated
  if (str.length() == 0) {
    return true;
  }
  //if the string starts with a matching pair, replace with empty and call recursively
  if (str.contains("()")) {
    return isBalanced(str.replace("()", ""));
  }
  if (str.contains("[]")) {
    return isBalanced(str.replace("[]", ""));
  }

  if (str.contains("{}")) {
    return isBalanced(str.replace("{}", ""));
  }
  return false;
}

/*****  4  ***************************************************/

/**
* A method to print all subsequences of str (order does not matter).
* @param str string to print all subsequences of
* @pre str must be string, empty is OK, cannot be null
* @post nothing is returned, but string soFar is printed as a result of calling subsequenceHelper
*/
public static void subsequences(String str) {
  Assert.pre(str != null, "The inputted string cannot be null");
  //it's just a wrapper
  subsequenceHelper(str, "");
}

/**
* Helper method for subsequences method
* `soFar` keeps track of the characters currently in the substring
*   being built
* @param str sequence that will be re-scrambled to print all different combinations
* @param soFar string where different sequence combinations are printed
* @pre str must be string, can be empty, cannot be null, checked in subsequences method
* @post nothing is returned, soFar string is printed to terminal with subsequences of str
*/
protected static void subsequenceHelper(String str, String soFar) {
  Assert.pre(str != null, "The inputted string cannot be null");

  // if there's antyhing left in the string, slice of the first character
  if (!str.isEmpty()) {
  char nextChar = str.charAt(0);
  String cutString = str.substring(1, str.length());

  // call recursively, including nextChar
  subsequenceHelper(cutString, soFar + nextChar);
  // call recusively, skipping nextChar
  subsequenceHelper(cutString, soFar);
} else {
  // in base case, spit out soFar. As the recursive sequence goes back up the calls
  // stack, this will print it out all of the possible combinations for soFar
  System.out.println(soFar);

}
}

/*****  5  ***************************************************/

/**
* A method to print the binary digits of a number.
*
* @param number the number to print in binary
* @pre must be nonnegative integer
* @post nothing returned, int printed as a result of BinaryHelper being called
*/
public static void printInBinary(int number) {
  Assert.pre(number >= 0, "The value of input should be nonnegative");
  // Case in the wrapper, because the core function isn't written
  // to deal with being passed 0 (that's its condition to terminate recursive calls)
  if (number == 0) {
  System.out.println(0);
} else {
  // the actual function
  binaryHelper(number);
}
}

/**
* A method to help print the binary digits of a number.
*
* @param number the number to print in binary
* @pre must be nonnegative integer
* @post nothing returned, int printed
*/
public static void binaryHelper(int number) {
  // as long as there's any of the number "left", then
  if (number > 0) {
  // get the last binary digit by modding by 2
  int lastDigit = number % 2;
  // get what remains of the number by dividing by 2
  // (each new binary digit is multiplied by twice as much to convert to decimal, so this is the inverse)
  binaryHelper(number / 2);
  // prints out each binary digit, so that as the function goes back up the call stack
  // the whole binary number will be printed
  System.out.print(lastDigit);
}
}



/*****  6a  ***************************************************/

/**
* Return whether a subset of the numbers in nums add up to sum,
* and print them out.
*
* @param array of ints, can be null
* @param targetSum is an int, can be negative or positive
* @return true if some subset of numbers in setOfNums sums to targetSum, an array of ints is also
* printed that include numbers that can add up to the target sum
* @pre setOfNums takes an array of integer values which can be pos or negative, targetSum takes an integer
* that can be pos or neg.
* @post returns a boolean, indicating whether or not any subset of the integers
* within array setOfNums sum up to targetSum and prints subset
* no nontrivial pre/postconditions to assert
*/
public static boolean printSubsetSum(int[] setOfNums, int targetSum) {
  // base case, if targetSum is reached (it represents the diff between the
  // actual running total, and the targetSum, so 0 is "reached")
  if (targetSum == 0) {
  return true;
}

//if there are not any numbers left to consider, and targetSum isn't reached
if (setOfNums.length == 0) {
return false;
// if there are numbers left to consider
} else {
  // include the front digit towards the running total, call recurisvely
  boolean a = printSubsetSum(Arrays.copyOfRange(setOfNums, 1, setOfNums.length), targetSum - setOfNums[0]);
  boolean b = false;

  // If including the number yelds a solution, then print out the number that was included
  // (all included numbers in this particalar solution will print as the recursive calls unwind)
  if (a) {
    System.out.print(setOfNums[0] + " ");
  }
  // if including the number did not produce any solutions, then call recursively, slicing off the number but not including it
  if (!a) {
    b = printSubsetSum(Arrays.copyOfRange(setOfNums, 1, setOfNums.length), targetSum);
  }
  // if either including or not including the number produced a solution, return true
  return (a || b);
}
}

/*****  6b  ***************************************************/
/**
* Return the number of different ways elements in nums can be
* added together to equal sum (you do not need to print them all).
*
* @param nums an array of ints
* @param targetSum int that represents the sum of possible subset solutions
* such that each subset of ints from nums adds up to targetSum
* @return indicates the number of possible solutions for targetSum within the subset of nums
* @pre setOfNums takes an array of integer values, targetSum takes an integer
* @post returns a nonnegative int
*/
public static int countSubsetSumSolutions(int[] nums, int targetSum) {
  // base case for success, return 1 (corresponds to true in previous implementation)
  // so that it is added to the running total of possible solutions.
  if (targetSum == 0) {
    return 1;
  }
  // failure case, return 0 so that no new solutions are recorded
  if (nums.length == 0) {
    return 0;
  } else {
    // recursive calls. Like previous implementaiton, but instead of checking if including the given number works
    // before trying the alternative other recursive call, tries all possible "paths"
    // Returns the sum of all the returns from each recursive path, which corresponds
    // to the number of possible subsets that satisfy canMakeSumm
    int a = countSubsetSumSolutions(Arrays.copyOfRange(nums, 1, nums.length), targetSum - nums[0]);
    int b = countSubsetSumSolutions(Arrays.copyOfRange(nums, 1, nums.length), targetSum);

    Assert.post(a + b >= 0, "Output should be 0 or more, there can't be a negative number of possible solutions");
    return a + b;
  }
}

/***********************************************************/

/**
* Add testing code to main to demonstrate that each of your
* recursive methods works properly.
*
* Think about the so-called corner cases!
*
* Remember the informal contract we are making: as long as all
* predconditions are met, a method should return with all
* postconditions met.
*/

protected static void testCannonballs() {
  System.out.println("Testing cannonballs: ....");
  System.out.println(countCannonballs(3));
  System.out.println(countCannonballs(10));
}

protected static void testPalindrome() {
  System.out.println("Testing isPalindrome: ....");
  System.out.println(isPalindrome("mom"));
  System.out.println(isPalindrome("deeded"));
  System.out.println(isPalindrome("ablewasIereIsawelba"));
}

protected static void testBalanced() {
  System.out.println("Testing isBalanced: ....");
  System.out.println(isBalanced("[{[()()]}]"));
  System.out.println(isBalanced("[{[()()]}][{[()()]}]"));
  System.out.println(isBalanced("[{[()()]}{]{[()()]}]"));
  System.out.println(isBalanced("{{[(()]}}"));
}

protected static void testSubsequence() {
  System.out.println("Testing subsequences: ....");
  subsequences("abc");
  System.out.println();
  subsequences("CSCI136");
  System.out.println();
  subsequences("a");
  System.out.println();
  subsequences("");
  System.out.println();
}

protected static void testBinary() {
  System.out.println("Testing printInBinary: ....");
  printInBinary(0);
  System.out.println("---");
  System.out.println();
  printInBinary(30);
  System.out.println();
  printInBinary(1);
  System.out.println();
  printInBinary(110);
  System.out.println();
  printInBinary(2048);
  System.out.println();
  printInBinary(43);
  System.out.println();
}

protected static void testCanMakeSum() {
  System.out.println("Testing canMakeSum: ....");
  int[] numSet = {2, 5, 7, 12, 16, 21, 30};
  int[] nummie = {1, 1, 1};
  System.out.println(canMakeSum(numSet, 21));
  System.out.println(canMakeSum(numSet, 22));
  System.out.println(canMakeSum(numSet, 3));
  System.out.println(canMakeSum(numSet, 30));
  System.out.println(canMakeSum(nummie, 2));
}

protected static void testPrintSubsetSum() {
  System.out.println("Testing printSubsetSum: ....");
  int[] numSet = {2, 5, 7, 12, 16, 21, 30};
  System.out.println(printSubsetSum(numSet, 21));
  System.out.println(printSubsetSum(numSet, 22));
  System.out.println(printSubsetSum(numSet, 3));
  System.out.println(printSubsetSum(numSet, 30));
}

protected static void testCountSubsetSum() {
  System.out.println("Testing countSubsetSumSolutions: ....");
  int[] numSet = {2, 5, 7, 12, 16, 21, 30};
  System.out.println(countSubsetSumSolutions(numSet, 21));
  System.out.println(countSubsetSumSolutions(numSet, 22));
  System.out.println(countSubsetSumSolutions(numSet, 3));
  System.out.println(countSubsetSumSolutions(numSet, 30));
}

/**
* Main method that calls testing methods to verify
* the functionality of each lab exercise.
*
* Please supplement the testing code with additional
* correctness tests as needed.
*/
public static void main(String[] args) {
  System.out.println(digitSum(123));
  testCannonballs();
  testPalindrome();
  testBalanced();
  testSubsequence();
  testBinary();
  testCanMakeSum();
  testPrintSubsetSum();
  testCountSubsetSum();
}
}

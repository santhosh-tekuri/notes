# Is Palindrome Number ?

Determine whether given positive number is palindrome

## Approach 1

first reverse the number. if the number is same as its reverse, then it is palindrome.

```java
boolean isPalindrome(int n) {
    return n==0 || n==reverse(n);
}

int reverse(int n) {
    int reverse = 1;
    while(num!=0) {
        int lastDigit = n%10;
        reverse = reverse*10 + lastDigit; // add lastDigit to reverse
        n = n/10;
    }
    return reverse;
}
```

**drawback:** the reverse number might overflow

## Approach 2

chop of one digit from both ends until they are same.
if there are no more digits left then it is palindrome;

```java
boolean isPalindrome(int n) {
    if(n==0)
        return true;

    int div = 1;
    while(n/div>10)
        div *= 10;

    while(n>0) {
        int firstDigit = x/div;
        int lastDigit = x%10;
        if(firstDigit!=lastDigit)
            return false;
        x = (x%div)/10;
        div = div/100;
    }
    return true;
}
```

### References

* <http://www.leetcode.com/2012/01/palindrome-number.html>

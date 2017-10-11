# Arrage for Largest Number

<http://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/>

Given array of non-negative numbers, arrange them to form largest possible number.

```bash
[3, 1, 4, 2] => [4, 3, 2, 1] => 43211  
[54, 546, 548, 60] => [60, 548, 546, 54] => 6054854654
[1, 34, 3, 98, 9, 76, 45, 4] => [9, 98, 76, 45, 4, 34, 3, 1] => 998764543431
[123, 124, 56, 90] => [90, 56, 124, 123] => 9056124123
```

---

Arranging numbers in decreasing order:
* does not work always
* `[12, 3]` does not give `312`
* it works if all numbers have same number of digits

Sort numbers by their first digit:
* `[12, 3]` => `[3, 12]` => `312`

What if first digits are same:
* example: `[126, 14]`
* sort them by second digit
* `[126, 14]` => `[14, 126]` => `14126`

What if all digits are same:
* example: `[11, 112]` or `[33, 332]`

Why not arrange in two possible ways and see:

```java
int compare(int n1, int n2) {
    String s1 = String.valueOf(n1);
    String s2 = String.valueOf(n2);
    return Strings.compare(s1+s2, s2+s1);
}
```

use the above comparator to sort array in decreasing order

---

## Similar Problem

<http://abhayavachat.blogspot.in/2011/01/lexicographically-lowest-possible.html>

Concat given strings to get lexicographically smallest string

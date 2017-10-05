# ABCD*4=DCBA

Find the `4`-digit number `ABCD`, such that when multipled by `4` gives `DCBA`?

---

:bulb: If the last two digits of a number are divisible by 4, then the entire number is divisible by 4

10000/4 = 2500  
so **1**000 <= ABCD <= **2**499

A can be 1 or 2
* if A is 1, then DCBA is not divisible by 4
* so A must be 2

now we have `2BCD*4=DCB2`
* `D*4` must end with 2. so D can be 3 or 8
* `4*A+carry=8+carry=D`
    * result is 4 digits
    * so carray must be 0 or 1
    * so D must be 8 and carry must be 1

now we have `2BC8*4=8CB2`
* 1**0**00 <= ABCD <= 2**4**99
* B can be 0 or 1 or 2 or 3 or 4
* by 4 divisibility rule, `B2` must be divisible by 4
* so B can't be 0, 2, 4
* so B can be 1 or 3

4C+3 = XB  
4C = XB-3

If B=1 then C is 2 or 7  ==> ABCD can be 2128 or 2178  
If B=4 then C is 0 or 5  ==> ABCD can be 2408 or 2448

multiply the above 4 numbers by 4 to find ABCD

it is 2178 because 2178*4 = 8712

---

### References

* <http://answers.yahoo.com/question/index?qid=20100225023431AAaG2be>

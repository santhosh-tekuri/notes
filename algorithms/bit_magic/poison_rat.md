# Poison and Rat

There are 1000 wine bottles. One of the bottles contains poisoned wine. A rat dies after one hour of drinking the poisoned wine. How many minimum rats are needed to figure out which bottle contains poison in hour ?

---

number of binary digits needed to represented `1000` = $\lceil\log_2(1000)\rceil$ = `10`

so we need minimum `10` rats

### how it works

* take bottle `42`
* `42` in binary is `0000101010`
* we has `1`s in positions `5, 7, 9`
* so it is consumed by 5th, 7th and 9th rats

using this approach, based on which rats died: 
* construct 10 digit binray number with 1's in those positions
* the resulting number tells which bottle is poisoned

---

### References

* <https://www.geeksforgeeks.org/puzzle-19-poison-and-rat/>
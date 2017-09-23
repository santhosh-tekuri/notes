# 2 Powers

## Compute `$2^x$` ?

`1<<x`

## Is `x`, power of `2` ?

`x&(x-1)==0`

if `x` is power of `2` then:
* `$x = 100\dots 0$`
* `$x-1 = 011\dots 1$`

so *and*-ing them together gives zero

### References

* <http://courses.csail.mit.edu/iap/interview/Hacking_a_Google_Interview_Handout_3.pdf>

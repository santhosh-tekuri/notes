# Permutations & Combinations

## Permutations

* order matters
* treated as list
* #permutations of `$n$` items
    * we have `$n$` choices for first item, `$n-1$` choices for second item, and so on
    * `$n . (n-1) . (n-2)\dots 1$`
    * `$n!$`
* #permutations of `$r$` items from `$n$` items
    * we have `$n$` choices for first item, `$n-1$` choices for second item, and so on
    * `$n . (n-1) . (n-2) \dots (n-r-1)$`
    * `$P(n,r) = {n! \over (n-r)!}$`


## Combinations

* order doesn't matter
* treated as group
* #combinations of `$r$` items from `$n$` items
    * #permutations divided by all the redundants
    * `$C(n,r) = {P(n,r) \over r!} = {n! \over (n-r)!r!}$`

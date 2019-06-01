# expr

* evalutations expression
* works only for integers
* space separated tokens
* Beware that many operators need to be escaped or quoted for shells
* operators
    * comparision: arithmetic if both ARGs are numbers, else lexicographical
        * `expr ARG1 \| ARG2` ARG1 if it is neither null nor 0, otherwise ARG2
        * `expr ARG1 \& ARG2` ARG1 if neither argument is null or 0, otherwise 0
        * `expr ARG1 \< ARG2`
        * `expr ARG1 \<= ARG2`
        * `expr ARG1 = ARG2`
        * `expr ARG1 != ARG2`
        * `expr ARG1 \>= ARG2`
        * `expr ARG1 \> ARG2`
    * arithmetic
        * `expr ARG1 + ARG2`
        * `expr ARG1 - ARG2`
        * `expr ARG1 \* ARG2`
        * `expr ARG1 / ARG2`
        * `expr ARG1 % ARG2`
    * `expr match STRING REGEXP`
    * `expr substr STRING POS LENGTH` POS counted from 1
    * `expr length STRING`
    * `expr ( EXPRESSION )`
    * `+ TOKEN` interpret TOKEN as a string, even if it is a keyword like `match` or an operator like `/`


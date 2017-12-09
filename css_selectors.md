# css selectors

::: note box
selectors only match elements
:::

---

### Basic Selectors

```go
     * ➜  if true                        // universal selector
   div ➜  if name=='div'                 // type selector
.green ➜  if classList.contains('green') // class selector
#green ➜  if id=='green'                 // id selector
```

---

### Attribute Selectors

```go
       [href] ➜  if @href
 [href="val"] ➜  if @href=="val"
[href^="val"] ➜  if @href.startsWith("val")
[href$="val"] ➜  if @href.endsWith("val")
[href*="val"] ➜  if @href.contains("val")
[href~="val"] ➜  if @href.containsWord("val")
```

---

### Combinators

`𝝰` and `𝝱` are selectors and read `≡` as `satisfies`

```go
 𝝰𝝱 ➜  if 𝝰 & 𝝱
𝝰,𝝱 ➜  if 𝝰 | 𝝱
𝝰>𝝱 ➜  if has(parent≡𝝰) & 𝝱
𝝰 𝝱 ➜  if has(ancestor≡𝝰) & 𝝱
𝝰+𝝱 ➜  if has(previousSibling≡𝝰) & 𝝱
𝝰~𝝱 ➜  if has(precedingSibling≡𝝰) & 𝝱
```

---

### Pseudo Classes

::: note box
allowed only after type selector or universal selector
:::

```go
consider:
              XXX     | arr
              --------|--------------
              child   | parent.children
              of-type | parent.children.filter(child.name=name)
then:
       div:first-XXX ➜  if name=='div' & arr[0]==this
      div:nth-XXX(5) ➜  if name=='div' & arr[4]==this
     div:nth-XXX(5n) ➜  if name=='div' & arr.filter((index+1)%5==0).contains(this)

        div:last-XXX ➜  if name=='div' & arr.reverse()[0]==this
 div:nth-last-XXX(5) ➜  if name=='div' & arr.reverse()[4]==this
div:nth-last-XXX(5n) ➜  if name=='div' & arr.reverse().filter((index+1)%5==0).contains(this)

        div:only-XXX ➜  if name=='div' & arr.length==1 && arr.contains(this)
```

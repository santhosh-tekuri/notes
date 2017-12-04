# css selectors

|         |            |
|--------:|:-----------|
|`*`       | → all elements |
|`div`     | → all `<div>` |
|`.green`  | → all elements with `green` class | 
| `#green` | → element with `green` identifier i.e. `id` attribute |
| `div,p`  | → all `<div>` and `<p>` |
| `li>a`  | → all `<a>` which are children of `<li>` |
| `div>li>a`  | → all `<a>` which are children of `<li>` which are again children of `<div>` |
| `div a` | → all `<a>` inside `<div>` i.e. descendants |
| `h1+p`   | → all `<p>` immediate next to `<h1>` |
| `h1~p`   | → all `<p>` preceded by `<h1>` inside same parent |
|    &nbsp;      |   &nbsp;       |
| `[href]` | → all elements that have `href` attribute |
| `[href="val"]` | → all elements that have `href` attribute value `val` |
| `[href^="val"]` | → all elements that have `href` attribute value starting with `val` |
| `[href$="val"]` | → all elements that have `href` attribute value ending with `val` |
| `[href*="val"]` | → all elements that have `href` attribute value containing `val` |
| `[href~="val"]` | → all elements that have `href` attribute value containing word `val` |
| `div[<attr-selector>]` | → all `<div>` matching the attibute selector |
|    &nbsp;      |   &nbsp;       |
| `div:first-child` | → first child of `<div>` |
| `div:nth-child(5)` | → `5`th child div `<div>` |
| `div:nth-child(5n)` | → every `5`th child of `<div>` i.e. `5th, 10th, 15th etc` |
| `div:last-child` | → last child of `<div>` |
| `div:nth-last-child(5)` | → `5`th last child div of `<div>` i.e. counting backwards |
| `div:nth-last-child(5n)` | → every `5`th last child of `<div>` |
| `div:only-child` | → `<div>` with single child |
|    &nbsp;      |   &nbsp;       |
| `div:first-of-type` | → first child `<div>` |
| `div:nth-of-type(5)` | → `5`th child `<div>` |
| `div:nth-of-type(5n)` | → every `5`th child `<div>` i.e. `5th, 10th, 15th etc` |
| `div:last-of-type` | → last child `<div>` |
| `div:nth-last-of-type(5)` | → `5`th last child div `<div>` i.e. counting backwards |
| `div:nth-last-of-type(5n)` | → every `5`th last child `<div>` |
| `div:only-of-type` | → only child `<div>` |
|    &nbsp;      |   &nbsp;       |
| `div:empty` | → `<div>` with no children |
| `div:not(<selector>)` | → `<div>` that does not match the selector |
|    &nbsp;      |   &nbsp;       |
| `:checked` | → input fields that are checked |
| `:enabled` | → input fields that are enabled |
| `:disabled` | → input fields that are disabled |


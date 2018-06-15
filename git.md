# Git

## stash

saves uncommitted changes to local repo for later use

```shell
$ git stash    # saves both staged and unstaged
$ git stash -u # include untracked files
$ git stash -a # include ignored files
$ git stash save "add style to our site" # annonate with description

$ git stash list
stash@{0}: On master: add style to our site
stash@{1}: WIP on master: 5002d47 our new homepage
stash@{2}: WIP on master: 5002d47 our new homepage

$ git stash show    # show files summary
$ git stash show -p # show diff patch

$ git stash pop # reapply previously stashed changes
$ git stash pop stash@{2}
$ git stash apply # keep them in stash, useful to apply to multiple branches

$ git stash drop stash@{1}
$ git stash clear
```
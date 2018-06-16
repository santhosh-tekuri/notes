# Git

## branch

```shell
$ git branch    # list only local branches
* draft07
  master
$ git branch -a # list remote branches also
* draft07
  master
  remotes/origin/HEAD -> origin/master
  remotes/origin/draft07

$ git checkout master     # switch to master branch
$ git checkout -b draft6  # create & switch to draft6 branch
$ git branch -d hotfix    # delete if merged to upstream
$ git branch -D hotfix    # force delete branch
$ git push origin :hotfix # delete remote branch

$ # create branch without history
$ git checkout --orphan gh-pages
$ git rm -rf .
```

---

## diff

```shell
$ git diff          # show unstaged changes
$ git diff --cached # show staged changes
```

---

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
